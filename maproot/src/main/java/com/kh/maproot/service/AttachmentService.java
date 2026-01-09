package com.kh.maproot.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.maproot.dao.AttachmentDao;
import com.kh.maproot.dto.AttachmentDto;
import com.kh.maproot.error.TargetNotfoundException;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AttachmentService {
	
	@Autowired
	private AttachmentDao attachmentDao;
	
	@Value("${attachment.base-dir}")
	private String baseDir;
	
	private File upload;
	
	@PostConstruct
	public void init() {
	    upload = new File(baseDir);
	}
	
//	private File home = new File(System.getProperty("user.home"));
//	private File upload = new File(home, "upload");
	
	@Transactional
	public Long save(MultipartFile attach) throws IllegalStateException, IOException {
		Long attachmentNo = attachmentDao.sequence();
		if(upload.exists() == false) {
			upload.mkdirs();
		}
		File target = new File(upload, String.valueOf(attachmentNo));
		attach.transferTo(target);
		
		//DB에 저장된 파일의 정보를 기록
		AttachmentDto attachmentDto = AttachmentDto.builder()
					.attachmentNo(attachmentNo)
					.attachmentName(attach.getOriginalFilename())
					.attachmentType(attach.getContentType())
					.attachmentSize(attach.getSize())
				.build();
		attachmentDao.insert(attachmentDto);
		
		return attachmentNo;//생성한 파일의 번호를 반환
	}
	
	@Transactional
	public ByteArrayResource load(Long attachmentNo) throws IOException {
		// 파일 탐색
		File target = new File(upload, String.valueOf(attachmentNo));
		if(target.isFile() == false) throw new TargetNotfoundException();
		
		// 파일의 내용을 읽어옴
		byte[] data = Files.readAllBytes(target.toPath());
		ByteArrayResource resource = new ByteArrayResource(data);
		
		return resource;
	}
	
	public void delete(Long attachmentNo) {
		AttachmentDto attachmentDto = attachmentDao.selectOne(attachmentNo);
		if(attachmentDto == null) throw new TargetNotfoundException();
		
		// 실제 파일 삭제
		File target = new File(upload, String.valueOf(attachmentNo));
		target.delete();
		
		// DB 정보 삭제
		attachmentDao.delete(attachmentNo);
	}

}
