package com.prs.auth.controller;

import com.prs.auth.dto.PasswordLoginParams;
import com.prs.auth.dto.RefreshTokenParams;
import com.prs.auth.execption.AuthException;
import com.prs.auth.service.UserService;
import com.prs.domain.enu.ApiResponseEnum;
import com.prs.domain.response.ApiResponseEntity;
import io.micrometer.core.instrument.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.prs.domain.response.ApiResponseEntity.success;

@RestController
@Api(description = "Token相关接口", basePath = "/", tags = "登录")
public class LoginController {

	@Autowired
	private UserService userService;

	@PostMapping("passwordLogin")
	@ApiOperation(value = "密码登录接口/获取Token接口", httpMethod = "POST", response = ApiResponseEntity.class)
	public ApiResponseEntity passwordLogin(@RequestBody PasswordLoginParams params, HttpServletResponse response) throws AuthException {
		Map<String, Object> stringObjectMap = userService.passwordLogin(params.getLoginName(), params.getPassword());
		return new ApiResponseEntity(ApiResponseEnum.SUCCESS, stringObjectMap);
	}

	@PostMapping("shortLogin")
	@ApiOperation(value = "短信登录接口/获取Token接口", httpMethod = "POST", response = ApiResponseEntity.class)
	public ApiResponseEntity shortLogin() {
		return success();
	}

	@PostMapping
	@ApiOperation(value = "token刷新接口", httpMethod = "POST", response = ApiResponseEntity.class)
	public ApiResponseEntity refreshToken(@RequestBody RefreshTokenParams params, HttpServletRequest request) throws AuthException {
		String token = request.getHeader("token");
		if (StringUtils.isEmpty(token)) {
			throw new AuthException(ApiResponseEnum.PARAM_ERROR, "token");
		}
		userService.refreshToken(token, params.getLongTime());
		return success();
	}
}
