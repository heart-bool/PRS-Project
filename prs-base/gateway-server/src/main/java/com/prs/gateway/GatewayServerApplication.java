package com.prs.gateway;

import com.prs.cache.EnableRedis;
import com.prs.domain.enu.ApiResponseEnum;
import com.prs.domain.response.ApiResponseEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableHystrix
@EnableEurekaClient
@EnableRedis
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}

	@RestController
	public class FallbackController {

		@RequestMapping("/fallback")
		public ApiResponseEntity fallback() {
			return new ApiResponseEntity(ApiResponseEnum.TIMEOUT);
		}
	}
}
