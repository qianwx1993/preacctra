package com.smk.form.local.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Version 1.0
 * @Since JDK1.8
 * @Author HYK
 * @Date 2019/1/29 0029 17:21
 */
@Data
public class BaseReqForm {

	@NotBlank(message = "渠道号不能为空")
	public String appId;

	@NotBlank(message = "渠道流水号不能为空")
	public String appSeq;

	@NotBlank(message = "请求时间不能为空")
	public String reqDateTime;

}
