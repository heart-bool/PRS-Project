package com.prs.message.execption;


import com.prs.domain.enu.ApiResponseEnum;
import com.prs.domain.exception.BaseException;

public class MessageException extends BaseException {

	public MessageException() {
		super();
	}

	public MessageException(ApiResponseEnum apiResponseEnum) {
		super(apiResponseEnum);
	}
}
