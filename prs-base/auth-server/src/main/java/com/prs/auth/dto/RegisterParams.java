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
@ApiModel("注册接口参数")
public class RegisterParams {

	@ApiModelProperty("用户名")
	private String userName;
	@ApiModelProperty("昵称")
	private String nikeName;
	@ApiModelProperty("密码")
	private String password;
	@ApiModelProperty("头像")
	private String headImg;
	@ApiModelProperty("邮箱")
	private String email;
	@ApiModelProperty("手机号")
	private String phone;
	@ApiModelProperty("年龄")
	private int age;
	@ApiModelProperty("性别 1：男，2：女，0：未填写")
	private int gender;
}
