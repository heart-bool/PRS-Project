package com.prs.auth;

import com.prs.cache.EnableRedis;
import com.prs.swagger.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger
@EnableRedis
@EnableFeignClients
public class PrsAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrsAuthApplication.class, args);
	}

}
