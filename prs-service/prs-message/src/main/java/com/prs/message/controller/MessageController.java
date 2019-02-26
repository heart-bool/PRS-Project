package com.prs.message.controller;

import com.prs.domain.response.ApiResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.prs.domain.response.ApiResponseEntity.success;

@RestController
@Api(tags = "发送短信/邮件相关接口")
public class MessageController {

	@RequestMapping("/sendSortVerifyCode/{mobile}")
	@ApiOperation(value = "发送短信验证码接口", httpMethod = "GET", response = ApiResponseEntity.class)
	public ApiResponseEntity sendSort(@ApiParam(value = "手机号", name = "mobile", required = true) @PathVariable("mobile") String mobile) {
		System.out.println(mobile);
		int i = 1/0;
		return success();
	}
}
