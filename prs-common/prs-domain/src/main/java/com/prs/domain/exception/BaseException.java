package com.prs.domain.exception;

import com.prs.domain.enu.ApiResponseEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@ToString
public class BaseException extends Exception {
	private ApiResponseEnum apiResponseEnum;

	public BaseException() {
		super();
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	protected BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BaseException(ApiResponseEnum apiResponseEnum) {
		super(apiResponseEnum.getDes());
		this.apiResponseEnum = apiResponseEnum;
	}

	public BaseException(ApiResponseEnum apiResponseEnum, String message) {
		super(message);
		this.apiResponseEnum = apiResponseEnum;
	}
}
