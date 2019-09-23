package com.smk.form.local.req.accTra;

import com.smk.form.local.req.BaseReqForm;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Version 1.0
 * @Since JDK1.8
 * @Author HYK
 * @Date 2019/1/29 0029 17:36
 */
@Data
@ToString(callSuper = true)
public class ConCanReqForm extends BaseReqForm {

	@NotBlank(message = "结算商户号不能为空")
	public String mchntid;

	@NotBlank(message = "订单号不能为空")
	public String orderid;

}
