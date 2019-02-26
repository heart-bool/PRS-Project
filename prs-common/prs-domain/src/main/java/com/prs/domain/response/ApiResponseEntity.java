package com.prs.domain.response;

import com.prs.domain.enu.ApiResponseEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ApiResponseEntity implements Serializable {


	private int code;

	private String message;

	private Object data;

	public ApiResponseEntity() {
		this(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getDes());
		this.data = null;
	}

	public ApiResponseEntity(Object data) {
		this(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getDes());
		this.data = data;
	}

	public ApiResponseEntity(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public ApiResponseEntity(String message, Object data) {
		this(ApiResponseEnum.SUCCESS.getCode(), message);
		this.data = data;
	}

	public ApiResponseEntity(ApiResponseEnum responseEnum) {
		this(responseEnum.getCode(), responseEnum.getDes());
	}

	public ApiResponseEntity(ApiResponseEnum responseEnum, Object data) {
		this(responseEnum.getCode(), responseEnum.getDes());
		this.data = data;
	}

	private static final ApiResponseEntity defaultSuccess = new ApiResponseEntity();
	private static final ApiResponseEntity defaultError = new ApiResponseEntity();

	public static ApiResponseEntity success() {
		return defaultSuccess;
	}

	public static ApiResponseEntity error() {
		return defaultError;
	}
}
