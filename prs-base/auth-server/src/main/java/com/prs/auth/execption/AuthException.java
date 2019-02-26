package com.prs.auth.execption;


import com.prs.domain.enu.ApiResponseEnum;
import com.prs.domain.exception.BaseException;

public class AuthException extends BaseException {

	public AuthException() {
		super();
	}

	public AuthException(ApiResponseEnum apiResponseEnum) {
		super(apiResponseEnum);
	}

	public AuthException(ApiResponseEnum apiResponseEnum, String message) {
		super(apiResponseEnum, message);
	}
}
