package com.smk.service;

import com.smk.form.local.req.accTra.ConsumeReqForm;
import com.smk.form.local.res.BaseResForm;

public interface AccTraService {

	/**
	 * 医院消费
	 *
	 * @param reqForm 请求信息
	 * @return 返回信息
	 */
	BaseResForm consume(ConsumeReqForm reqForm);

}
