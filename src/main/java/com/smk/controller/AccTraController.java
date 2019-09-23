package com.smk.controller;

import com.smk.common.ErrorCode;
import com.smk.form.local.req.accTra.ConsumeReqForm;
import com.smk.form.local.res.BaseResForm;
import com.smk.service.AccTraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Version 1.0
 * @Since JDK1.8
 * @Author HYK
 * @Date 2019/1/29 0029 16:52
 */
@Slf4j
@RestController
@RequestMapping("accTra")
public class AccTraController extends BaseController {

	@Resource
	private AccTraService accTraService;

	/**
	 * 渠道消费
	 *
	 * @param reqForm       请求信息
	 * @param bindingResult 参数校验
	 * @return 返回信息
	 */
	@PostMapping("consume")
	public BaseResForm consume(@Valid @RequestBody ConsumeReqForm reqForm, BindingResult bindingResult) {
		// 检查传递参数
		if (bindingResult.hasErrors()) {
			String errMsg = bindingResult.getFieldError().getDefaultMessage();
			log.info("req params error:reqsn={},msg={}", reqForm.getAppSeq(), errMsg);
			return BaseResForm.back(ErrorCode.PARAM_WRONG.getCode(), errMsg);
		}

		// 保存接收日志
		String id = saveReceiveLog(reqForm);


		BaseResForm backForm = BaseResForm.success();

		// 检查商户信息
		checkAppId(reqForm);

		backForm = accTraService.consume(reqForm);

		// 保存返回日志
		saveReturnLog(id, backForm);
		return backForm;
	}

}
