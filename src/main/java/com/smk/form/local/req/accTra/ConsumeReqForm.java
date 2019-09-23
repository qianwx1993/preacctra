package com.smk.form.local.req.accTra;

import com.smk.form.local.req.BaseReqForm;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Version 1.0
 * @Since JDK1.8
 * @Author HYK
 * @Date 2019/1/29 0029 17:24
 */
@Data
@ToString(callSuper = true)
public class ConsumeReqForm extends BaseReqForm {

	@NotBlank(message = "结算商户号不能为空")
	public String mchntid;

	// 卡号(9位)
	public String cardnumber;

	// 芯片号
	public String csn;

	// 卡类型
	public String cardkind;

	@NotBlank(message = "交易金额不能为空")
	public String txnamt;

	@NotBlank(message = "场景不能为空")
	public String apptype;

	@NotBlank(message = "订单号不能为空")
	public String orderid;

}
