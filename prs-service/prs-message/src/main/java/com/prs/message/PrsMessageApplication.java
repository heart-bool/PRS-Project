package com.prs.message;

import com.prs.swagger.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger
//@EnableRedis
public class PrsMessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrsMessageApplication.class, args);
	}
}
