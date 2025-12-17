package com.kh.maproot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "cusrom.websoket")
public class WebSocketProperties {
	private int strSize;
}
