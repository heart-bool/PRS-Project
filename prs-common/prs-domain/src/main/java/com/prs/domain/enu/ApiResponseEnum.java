package com.prs.domain.enu;

import java.io.Serializable;


public enum ApiResponseEnum implements Serializable {
	SUCCESS(0, "success"),
	ERROR(99999, "server error"),
	TIMEOUT(503, "timeout"),
	UNAUTHORIZED(400, "UNAUTHORIZED"),
	USER_NOT_FOUND(1000, "user not found"),
	LOGIN_ERROR(1001, "用户名不存在或密码错误"),
	LOGIN_REPEAT_ERROR(1002, "不允许重复登录"),
	LOGIN_NOT_ERROR(1003, "登录token不存在"),
	REGISTER_USERNAME_ERROR(1004, "用户名已注册"),
	REGISTER_EMAIL_ERROR(1004, "邮箱已注册"),
	REGISTER_PHONE_ERROR(1004, "手机号已注册"),
	PARAM_ERROR(6000, "参数不能为空"),
	;

	/***
	 * 响应代码
	 */
	private int code;

	/***
	 * 响应代码注释
	 */
	private String des;

	private ApiResponseEnum(int code, String des) {
		this.code = code;
		this.des = des;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
}
