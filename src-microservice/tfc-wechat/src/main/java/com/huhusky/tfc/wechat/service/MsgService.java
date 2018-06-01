package com.huhusky.tfc.wechat.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huhusky.common.utils.util.BigDecimalUtils;
import com.huhusky.tfc.wechat.dao.MsgDao;
import com.huhusky.tfc.wechat.msg.Keyword;
import com.huhusky.tfc.wechat.msg.KeywordRet;
import com.huhusky.tfc.wechat.msg.KeywordRetMap;
import com.huhusky.tfc.wechat.msg.MsgConfig;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MsgService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private MsgDao msgDao;

	static String BitNoahUrl = "https://www.bitnoah.com/dwr/call/plaincall/HomeHq.GetHomePrice.dwr";
	
	private static MultiValueMap<String, Object> params;
	static {
		params= new LinkedMultiValueMap<String, Object>();
		params.add("callCount", 1);
		params.add("batchId", 1);
		params.add("instanceId", 0);
		params.add("scriptSessionId", "kZ3L!TTr!GneOCDVIVLzzXoZBbbaHDqK8em/Nh*69em-4DO5cBEHj");
		params.add("page", "%2Fbsc%2Findex.jsp");
		params.add("c0-id", 0);
		params.add("nextReverseAjaxIndex", 0);
		params.add("c0-scriptName", "HomeHq");
		params.add("c0-methodName", "GetHomePrice");
		params.add("c0-param0", "string:");
		params.add("c0-param1", "string:");
	}

	public String getTfprice() {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.TEXT_PLAIN);
			//  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(params);
			long start = System.currentTimeMillis();
			ResponseEntity<String> result = restTemplate.postForEntity(BitNoahUrl, 
					requestEntity, String.class);
			String body = result.getBody();
			long start1 = System.currentTimeMillis();
			String ret = parseTfc(body);
			long end = System.currentTimeMillis();
			log.debug(String.format("#### %s, 网络请求耗时 %s, 解析耗时 %s", Thread.currentThread().getName(), start1-start, end-start1));
			return ret;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			return null;
		}

	}

	private static String parseTfc(String result) {
		String[] strs = result.split("\r\n"); // System.getProperty("line.separator")
		String line2 = strs[2].split(";")[1];
		int firstL = line2.indexOf("(");
		int startL = firstL + 10;
		int endL = line2.length() - 2;
		String data = line2.substring(startL, endL);
		data = data.replace("\\\"", "\"").replace("\\\\", "\\");
		JSONObject jo = JSONObject.parseObject(data);
		JSONArray ja = jo.getJSONArray("data");
		Iterator<Object> it = ja.iterator();
		while(it.hasNext()) {
			JSONObject j1 = JSONObject.parseObject(it.next().toString());
			if("tf".equals(j1.getString("coincode"))) {
				String price = j1.getString("newest");
				String uppecent = j1.getString("uppercent");
				double p = BigDecimalUtils.mul(Double.valueOf(price), Double.valueOf(5)).doubleValue();
				String pattern = "【比特诺亚】\n当前 TF 价格： %s AUC \n约合 %s CNY, 涨幅 %s%% \n【%s】\n";

				String ret = String.format(pattern, price, p, uppecent, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				/*if(StringUtils.isNotBlank(KeywordArr.KeywordShow)) {
					ret += "-----------------------------\n";
					ret += "关键词：" + KeywordArr.KeywordShow;
				}*/
				return ret;
			}
		}
		return null;
		//		dwr.engine.remote.handleCallback("1","0","{\"code\":\"0\",\"msg\":\"\",\"extdata\":\"\",\"data\":[\"{\\\"coincode\\\":\\\"act\\\",\\\"newest\\\":\\\"0.3253\\\",\\\"uppercent\\\":\\\"-1.51\\\",\\\"amt\\\":\\\"110372279.5150\\\",\\\"num\\\":\\\"63951768.0580\\\"}\",\"{\\\"coincode\\\":\\\"bsc\\\",\\\"newest\\\":\\\"0.0451\\\",\\\"uppercent\\\":\\\"1.58\\\",\\\"amt\\\":\\\"13133608.1205\\\",\\\"num\\\":\\\"59155323.2104\\\"}\",\"{\\\"coincode\\\":\\\"btc\\\",\\\"newest\\\":\\\"10600.0000\\\",\\\"uppercent\\\":\\\"-4.42\\\",\\\"amt\\\":\\\"2650710.6000\\\",\\\"num\\\":\\\"50.0149\\\"}\",\"{\\\"coincode\\\":\\\"can\\\",\\\"newest\\\":\\\"0.0304\\\",\\\"uppercent\\\":\\\"0.00\\\",\\\"amt\\\":\\\"12673005.5330\\\",\\\"num\\\":\\\"73571528.5955\\\"}\",\"{\\\"coincode\\\":\\\"eth\\\",\\\"newest\\\":\\\"1499.0000\\\",\\\"uppercent\\\":\\\"71.51\\\",\\\"amt\\\":\\\"875126.1175\\\",\\\"num\\\":\\\"200.0265\\\"}\",\"{\\\"coincode\\\":\\\"kcash\\\",\\\"newest\\\":\\\"0.1970\\\",\\\"uppercent\\\":\\\"-4.46\\\",\\\"amt\\\":\\\"71623190.8420\\\",\\\"num\\\":\\\"71249434.5057\\\"}\",\"{\\\"coincode\\\":\\\"let\\\",\\\"newest\\\":\\\"0.0758\\\",\\\"uppercent\\\":\\\"5.13\\\",\\\"amt\\\":\\\"27247848.3870\\\",\\\"num\\\":\\\"72899200.1713\\\"}\",\"{\\\"coincode\\\":\\\"ltc\\\",\\\"newest\\\":\\\"172.0000\\\",\\\"uppercent\\\":\\\"-1.15\\\",\\\"amt\\\":\\\"761100.0000\\\",\\\"num\\\":\\\"885.0000\\\"}\",\"{\\\"coincode\\\":\\\"swtc\\\",\\\"newest\\\":\\\"0.0096\\\",\\\"uppercent\\\":\\\"-11.93\\\",\\\"amt\\\":\\\"1871335.5700\\\",\\\"num\\\":\\\"36262810.0284\\\"}\",\"{\\\"coincode\\\":\\\"tf\\\",\\\"newest\\\":\\\"0.0091\\\",\\\"uppercent\\\":\\\"30.00\\\",\\\"amt\\\":\\\"1630411.4785\\\",\\\"num\\\":\\\"43026798.2275\\\"}\",\"{\\\"coincode\\\":\\\"vcat\\\",\\\"newest\\\":\\\"0.0001\\\",\\\"uppercent\\\":\\\"0.00\\\",\\\"amt\\\":\\\"3701.6800\\\",\\\"num\\\":\\\"7150679.8802\\\"}\"]}");
	}
	
	public MsgConfig buildMsgConfig() {
		log.info("###### 开始构造消息配置  ########");
		long start = System.currentTimeMillis();
		List<KeywordRetMap> retMap = msgDao.listKeywordMap();
		List<Keyword> keywords = msgDao.listKeywords();
		List<KeywordRet> keywordRets = msgDao.listKeywordRet();
		
		Map<Long, String> keywordsmap = keywords.stream().collect(Collectors.toMap(Keyword::getKeywordId, Keyword::getKeyword));
		Map<Long, KeywordRet> retmaps = keywordRets.stream().collect(Collectors.toMap(KeywordRet::getKeywordRetId, KeywordRet -> KeywordRet)); // Function.identity()
		
		MsgConfig mc = new MsgConfig();
		mc.setKeywords(keywords.stream().map(Keyword::getKeyword).collect(Collectors.toList()));
		
		Map<String, Long> map = new HashMap<>();
		
		for(KeywordRetMap krm : retMap) {
			map.put(keywordsmap.get(krm.getKeywordId()), krm.getKeywordRetId());
		}
		
		mc.setKeywordRetMapping(map);
		mc.setKeywordRet(retmaps);
		log.info("###### 消息配置构造完成，共花时间%s  ########", System.currentTimeMillis() - start);
		log.info("\n{}", JSON.toJSONString(mc));
		return mc;
	}

}
