package com.smk.http.back;

import com.smk.form.local.req.accTra.ConsumeReqForm;
import com.smk.form.local.res.BaseResForm;
import com.smk.http.BaseHttp;
import com.smk.http.back.util.BackHttpUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Version 1.0
 * @Since JDK1.8
 * @Author HYK
 * @Date 2019/1/29 0029 18:04
 */
@Component
public class HospitalConsume extends BaseHttp{

	@Resource
	private BackHttpUtil backHttpUtil;

	public BaseResForm execute(ConsumeReqForm consumeReqForm) {
		return null;
	}

}
