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
@ApiModel("刷新Token接口参数")
public class RefreshTokenParams {

	@ApiModelProperty("刷新多长时间")
	private long longTime;

}
