package com.prs.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseEntity {
	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 姓名
	 */
	private String userName;

	/**
	 * 昵称
	 */
	private String nikeName;

	/**
	 * 头像
	 */
	private String headImg;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 手机号
	 */
	private String phone;

	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 1男 2女 0未填写
	 */
	private Integer gender;

	/**
	 * 创建时间
	 */
	private Date created;

	/**
	 * 更新时间
	 */
	private Date updated;

	/**
	 * token过期时间
	 */
	private long expire;

	/**
	 * token自动刷新时间
	 */
	private long refresh;
}
