package com.smk.service.impl;

import com.smk.form.local.req.accTra.ConsumeReqForm;
import com.smk.form.local.res.BaseResForm;
import com.smk.http.back.HospitalConsume;
import com.smk.service.AccTraService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Version 1.0
 * @Since JDK1.8
 * @Author HYK
 * @Date 2019/1/29 0029 18:01
 */
@Service
public class AccTraServiceImpl implements AccTraService {

	@Resource
	private HospitalConsume hospitalConsume;

	@Override
	public BaseResForm consume(ConsumeReqForm reqForm) {
		return hospitalConsume.execute(reqForm);
	}

}
