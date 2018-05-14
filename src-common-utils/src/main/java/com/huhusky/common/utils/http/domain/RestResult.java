package com.huhusky.common.utils.http.domain;

import com.alibaba.fastjson.JSONObject;

/**
 * SC_CONTINUE， 状态码是100，表示客户端无法连接。<br>
 * SC_SWITHING_PROTOCOLS,状态码是101，表示服务器正向报头中注明的协议切换。<br>
 * SC_OK，状态码是200.表示请求被成功处理。<br>
 * SC_CREATED，状态码是201，表示请求被成功处理，并在服务器方创建了一个新的资源。<br>
 * SC_ACCEPTED，状态码是202，表示请求正在被处理，但尚未完成。<br>
 * SC_NON_AUTHORITATIVE_INFORMATION，状态码是203，表示客户端所表达的mate信息并非来自服务器。<br>
 * SC_NO_CONTENT，状态码是204，表示请求被成功处理，但没有新的信息返回。<br>
 * SC_RESET_CONTENT,状态码是205，表示导致请求被发送的文档视图应该重置。<br>
 * SC_PARTIAL_CONTENT,状态码是206，表示服务器已经完成对资源的GET请求。<br>
 * SC_MULTI_CHOICES，状态码是300，表示对应于一系列表述的被请求资源都有明确的位置。<br>
 * SC_MOVED_PERMANENTLY，状态码是301，表示请求所申请的资源已经被移到一个新的地方，并且将来的参考点在请求中应当使用一个新的URL.<br>
 * SC_MOVED_TEMPORARILY,状态码是302，表示请求所申请的资源已经被移到一个新的地方，并且将来的参考点在请求中仍使用原来的URL.<br>
 * SC_SEE_OTHER,状态码是303，表示请求的响应可以在一个不同的URL中找到。<br>
 * SC_NOT_MODIFIED，状态码是304，表示一个有条件的GET操作发现资源可以利用，且没有被改变。<br>
 * SC_USE_PROXY，状态码是305，表示被请求的资源必须通过特定位置的代理来访问。<br>
 * SC_BAD_REQUEST，状态码是400，表示客户发出的请求句法不正确。<br>
 * SC_UNAUTHORIZED,状态码是401，表示请求HTTP认证。<br>
 * SC_PAYMENT_REQUIRED，状态码是402，表示为以后的使用保留。<br>
 * SC_FORBIDDEN，状态码是403，表示服务器明白客户的请求，但拒绝响应。<br>
 * SC_NOT_FAND，状态码是404，表示所请求的资源不可用。<br>
 * SC_METHOD_NOT_ALLOWED,状态码是405，表示在请求行中标示的方法不允许对请求URL所标明的资源使用。<br>
 * SC_NOT_ACCEPTTABLE,状态码是406，表示被请求的资源只能响应实体，而且此符合请求所发送的可接受头部域的实体的确包含不可接受的内容。<br>
 * SC_PHOXY_AUTHENTICATION_REQUIRED,状态码是407，表示客户端必须先向代理验证。<br>
 * SC_PARAMS_NOT_ALLOWED,状态码是408，表示客户端请求参数有误。<br>
 * SC_SERVICE_EXCEPTION,状态码是500，表示controller层发生不可预期错误。<br>
 * SC_SERVICE_EXCEPTION,状态码是501，表示service层发生不可预期错误。<br>
 * SC_SERVICE_EXCEPTION,状态码是502，表示微服务间通讯异常<br>
 */
public class RestResult {

	private final static String ERR_CODE = "errcode";
	private final static String ERR_MSG = "errmsg";
	private JSONObject result;

	public RestResult() {
		result = new JSONObject();
	}

	public RestResult(int errcode) {
		result = new JSONObject();
		result.put(ERR_CODE, errcode);
	}

	public RestResult(int errcode, String errmsg) {
		result = new JSONObject();
		result.put(ERR_CODE, errcode);
		result.put(ERR_MSG, errmsg);
	}

	public int getErrcode() {
		return result.getIntValue(ERR_CODE);
	}

	public void setErrcode(int errcode) {
		result.put(ERR_CODE, errcode);
	}

	public String getErrmsg() {
		return result.getString(ERR_MSG);
	}

	public void setErrmsg(String errmsg) {
		result.put(ERR_MSG, errmsg);
	}

	public Object getData(String key) {
		return result.get(key);
	}

	public void addData(String key, Object value) {
		result.put(key, value);
	}

	@Override
	public String toString() {
		return result.toJSONString();
	}

}
