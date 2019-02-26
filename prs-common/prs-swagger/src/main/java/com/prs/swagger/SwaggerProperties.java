package com.prs.swagger;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "prs.swagger")
@Getter
@Setter
@ToString
public class SwaggerProperties {
	private String title;
	private String description;
	private String termsOfServiceUrl;
	private String version;
	private Boolean enable;
	private String host;
}
