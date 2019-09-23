package com.smk.form.local.req.accTra;

import com.smk.form.local.req.BaseReqForm;
import lombok.Data;
import lombok.ToString;

/**
 * @Version 1.0
 * @Since JDK1.8
 * @Author HYK
 * @Date 2019/1/29 0029 17:37
 */
@Data
@ToString(callSuper = true)
public class QueryBalReqForm extends BaseReqForm {

	// 卡号(9位)
	public String cardnumber;

	// 芯片号
	public String csn;

	// 卡类型
	public String cardkind;

}
