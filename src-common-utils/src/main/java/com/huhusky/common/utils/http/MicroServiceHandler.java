package com.huhusky.common.utils.http;

import java.util.Iterator;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huhusky.common.utils.exception.HttpRequestAbortedException;
import com.huhusky.common.utils.http.domain.RestResult;

public class MicroServiceHandler {

	private JSONObject payloadJO;

	public MicroServiceHandler() {
		payloadJO = new JSONObject();
	}

	public MicroServiceHandler addParams(String key, Object value) {
		payloadJO.put(key, value);
		return this;
	}

	public RestResult request(String uri) {
		String payload = payloadJO.toJSONString();
		return request(uri, payload);
	}

	public RestResult request(String uri, String payload) {
		RestResult rr = null;
		try {
			String result = HttpProxy.post(uri, payload);
			JSONObject rrjo = JSON.parseObject(result);
			rr = new RestResult(rrjo.getIntValue("errcode"), rrjo.getString("errmsg"));
			Set<String> keys = rrjo.keySet();
			for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
				String key = iterator.next();
				rr.addData(key, rrjo.get(key));
			}
		} catch (Exception e) {
			if (e instanceof HttpRequestAbortedException) {
				rr = new RestResult(502, "micro service request aborted =>> request status code : " + e.getMessage());
			} else {
				rr = new RestResult(500, "unknow exception");
			}
		}
		return rr;
	}

	public JSONObject getPayloadJO() {
		return payloadJO;
	}

	public void setPayloadJO(JSONObject payloadJO) {
		this.payloadJO = payloadJO;
	}

}
