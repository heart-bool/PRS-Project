package com.prs.user.feign;

import com.prs.domain.response.ApiResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("message")
public interface MessageService {

	@RequestMapping("/sendSortVerifyCode/{mobile}")
	ApiResponseEntity sendSort(@PathVariable("mobile") String mobile);
}
