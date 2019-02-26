package com.prs.auth.controller;

import com.prs.auth.dto.RegisterParams;
import com.prs.auth.entity.User;
import com.prs.auth.execption.AuthException;
import com.prs.auth.feign.MessageService;
import com.prs.auth.service.UserService;
import com.prs.domain.response.ApiResponseEntity;
import io.micrometer.core.instrument.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.prs.domain.response.ApiResponseEntity.success;

@RestController
@Api(value = "/register", description = "用户注册相关接口", basePath = "/", tags = "注册")
public class RegisterController {

	@Autowired
	private UserService userService;

	@Autowired
	private MessageService messageService;

	@PostMapping("/register")
	@ApiOperation(value = "注册接口", notes = "年龄必须大于-1", httpMethod = "POST", response = ApiResponseEntity.class)
	public ApiResponseEntity register(@RequestBody RegisterParams params) throws AuthException {
//		ApiResponseEntity apiResponseEntity = messageService.sendSort("11111111");
//		if (apiResponseEntity.getCode() != 0) {
//			return apiResponseEntity;
//		}
		userService.hasUserName(params.getUserName());
		userService.hasEmail(params.getEmail());
		userService.hasPhone(params.getPhone());

		User user = new User();
		user.setAge(params.getAge());
		user.setEmail(params.getEmail());
		user.setGender(params.getGender());
		user.setPhone(params.getPhone());
		user.setUserName(params.getUserName());
		user.setHeadImg(params.getHeadImg());
		user.setPassword(params.getPassword());
		userService.saveUserByRegister(user);
		return success();
	}


}
