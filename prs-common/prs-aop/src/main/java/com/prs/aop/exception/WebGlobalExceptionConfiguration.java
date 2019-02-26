package com.prs.aop.exception;

import com.prs.domain.enu.ApiResponseEnum;
import com.prs.domain.exception.BaseException;
import com.prs.domain.response.ApiResponseEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Log4j2
@Configuration
public class WebGlobalExceptionConfiguration {

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Object jsonErrorHandler(HttpServletRequest req, Exception e) {
		log.error("request exception: {}, code line: {}", e.getMessage(), e.getStackTrace()[0]);
		if (e instanceof BaseException) {
			BaseException baseException = (BaseException) e;
			ApiResponseEnum apiResponseEnum = baseException.getApiResponseEnum();
			return new ApiResponseEntity(apiResponseEnum);
		} else {
			return new ApiResponseEntity(ApiResponseEnum.ERROR.getCode(), e.getMessage());
		}
	}
}
