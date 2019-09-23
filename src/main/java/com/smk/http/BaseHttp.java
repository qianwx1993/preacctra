package com.smk.http;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.smk.http.config.MyMappingJackson2HttpMessageConverter;
import com.smk.http.config.SslClientHttpRequestFactory;
import com.smk.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Version 1.0
 * @Since JDK1.8
 * @Author HYK
 * @Company bangsun
 * @Date 2018年11月22日 11:12:07
 */
@Slf4j
@Component
public class BaseHttp {

	// 连接池的最大连接数
	private static final int maxTotalConnect = 0;
	// 单个主机的最大连接数
	private static final int maxConnectPerRoute = 200;
	// 连接超时时间
	private static final int connectTimeout = 2000;
	// 响应超时时间
	private static final int readTimeout = 60000;

	@Value("${center.acc.version}")
	private String HTTP_HEAD_VERSION;
	@Value("${center.acc.url}")
	private String ACCOUNT_URL;

	public JSONObject doBackPost(String url, JSONObject sendContent,String reqSeq) {
		JSONObject sendJson = new JSONObject();

		JSONObject headJson = new JSONObject();
		headJson.put("txndate", DateUtil.getYearMonthDay());
		headJson.put("txntime", DateUtil.getHourMinuteSecond());
		headJson.put("version", HTTP_HEAD_VERSION);
		headJson.put("reqseq", reqSeq);

		sendJson.put("head", headJson);
		sendJson.put("txn", sendContent);

		url = ACCOUNT_URL + url;

		log.debug("发送http请求-----");
		log.debug("地址:{},内容:{}", url, sendJson);
		JSONObject result = null;
		try {
			RestTemplate restTemplate = getRestTemplate();
			result = restTemplate.postForObject(url, sendJson, JSONObject.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			log.error("后台请求失败:" + e.getMessage());
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("errcode", "EE");
			jsonObject.put("errdisp", "后台请求失败");
			result.put("head", jsonObject);

			JSONObject txnJson = new JSONObject();
			txnJson.put("centseq", "");
			txnJson.put("settdate", "");
			result.put("txn", txnJson);
		}
		log.debug("http请求结束-----返回值:{}", result);
		return result;
	}

	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate(this.createFactory());
		List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();

		//重新设置StringHttpMessageConverter字符集为UTF-8，解决中文乱码问题
		HttpMessageConverter<?> converterTarget = null;
		for (HttpMessageConverter<?> item : converterList) {
			if (StringHttpMessageConverter.class == item.getClass()) {
				converterTarget = item;
				break;
			}
		}
		if (null != converterTarget) {
			converterList.remove(converterTarget);
		}
		converterList.add(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

		restTemplate.getMessageConverters().add(new MyMappingJackson2HttpMessageConverter());

		//加入FastJson转换器
		converterList.add(new FastJsonHttpMessageConverter());
		return restTemplate;
	}

	//创建HTTP客户端工厂
	private ClientHttpRequestFactory createFactory() {
		if (this.maxTotalConnect <= 0) {
			SslClientHttpRequestFactory factory = new SslClientHttpRequestFactory();
			factory.setConnectTimeout(this.connectTimeout);
			factory.setReadTimeout(this.readTimeout);
			return factory;
		}
		HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(this.maxTotalConnect).setMaxConnPerRoute(this.maxConnectPerRoute).build();
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
		factory.setConnectTimeout(this.connectTimeout);
		factory.setReadTimeout(this.readTimeout);
		return factory;
	}

}
