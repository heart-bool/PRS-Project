package com.prs.user;

import com.prs.swagger.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger
//@EnableRedis
@EnableFeignClients
public class PrsUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrsUserApplication.class, args);
	}
}
