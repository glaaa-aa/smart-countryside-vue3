package com.qfy.modules.smartform.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @Title: Controller
 * @Description: 二维码表
 * @author onlineGenerator
 * @date 2019-04-17 15:53:21
 * @version V1.0
 *
 */
@RestController
@RequestMapping("/test/bigScreen")
public class TestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	private static Charset DEF_CHARSET = Charset.forName("UTF-8");
	private String domain = "http://10.103.0.137/external/OACallbackExamine";

	@GetMapping(value = "test")
	@ResponseBody
	public String todayPlan(HttpServletRequest request) throws Exception {
//		{"yw_id":"162","yw_type":"zlsqd","state":"10"}
		JSONObject params = new JSONObject();
		params.put("yw_id","162");
		params.put("yw_type","zlsq");
		params.put("state","10");


		LOGGER.info("requerst api  req ==>"+ JSON.toJSONString(params) );
		String result3 = httpsPost(domain, JSON.toJSONString(params),null);
		LOGGER.info("requerst api rep==>"+ result3);
		return "success";
	}

	public static String httpsPost(String url, String json, Map<String,String> headers) throws Exception{

		CloseableHttpClient hp = createSSLClientDefault();
		HttpPost httpPost = new HttpPost(url);

		if(headers != null ){
			for (Map.Entry<String,String> entity: headers.entrySet()) {
				httpPost.setHeader(entity.getKey(),entity.getValue());
			}
		}

		if(StringUtils.isNotEmpty(json)){
			httpPost.setEntity(new StringEntity(json,DEF_CHARSET.name()));
		}

		CloseableHttpResponse response = hp.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String content = EntityUtils.toString(entity,"UTF-8");
		hp.close();
		return content;
	}

	public static CloseableHttpClient createSSLClientDefault() throws Exception{
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build(),
				NoopHostnameVerifier.INSTANCE);
		return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	}


}
