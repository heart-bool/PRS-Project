package com.prs.user.execption;


import com.prs.domain.enu.ApiResponseEnum;
import com.prs.domain.exception.BaseException;

public class UserException extends BaseException {

	public UserException() {
		super();
	}

	public UserException(ApiResponseEnum apiResponseEnum) {
		super(apiResponseEnum);
	}
}
