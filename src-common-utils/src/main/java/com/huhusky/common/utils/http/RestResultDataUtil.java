package com.huhusky.common.utils.http;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huhusky.common.utils.http.domain.RestResult;

public class RestResultDataUtil {

	public static <T> List<T> getList(RestResult rr, String key, Class<T> clazz) {
		Object obj = rr.getData(key);
		if (obj != null) {
			String jsonStr = ((JSONArray) obj).toJSONString();
			List<T> list = JSON.parseArray(jsonStr, clazz);
			return list;
		}
		return null;
	}

	public static <T> T getObject(RestResult rr, String key, Class<T> clazz) {
		Object obj = rr.getData(key);
		if (obj != null) {
			return JSON.toJavaObject((JSONObject) obj, clazz);
		}
		return null;
	}

}
