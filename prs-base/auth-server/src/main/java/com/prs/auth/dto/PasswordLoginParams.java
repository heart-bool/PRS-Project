package com.prs.auth.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("密码登陆接口参数")
public class PasswordLoginParams {

	@ApiModelProperty("登录名")
	private String loginName;
	@ApiModelProperty("密码")
	private String password;

}
