package com.prs.auth.properties;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "prs.token")
public class TokenProperties {

	private long expire;
	private long refresh;
	private String keyPrefix;
}
