package com.prs.cache.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "prs.redis")
public class PrsCacheProperties {
	private String acceptPackage;

	public String getAcceptPackage() {
		return acceptPackage;
	}

	public void setAcceptPackage(String acceptPackage) {
		this.acceptPackage = acceptPackage;
	}
}
