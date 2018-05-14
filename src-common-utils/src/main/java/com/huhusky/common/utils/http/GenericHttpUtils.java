package com.huhusky.common.utils.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 泛型http工具
 * @author Ted-wuhuhu
 * @Time 2016年12月20日 下午3:01:16
 *
 */
public class GenericHttpUtils {

	private static HttpSession session;
	
	/**
	 * 根据 attrKey 获取存放在session中的对象
	 * @author Ted-wuhuhu
	 * @Time 2016年12月20日 下午3:02:12
	 * @param request
	 * @param attrKey
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T getSessionAttr(HttpServletRequest request, String attrKey){
		session = request.getSession();
		T t = (T) session.getAttribute(attrKey);
		return t;
	}
	
	/**
	 * 存放 对象 到 session中
	 * @author Ted-wuhuhu
	 * @Time 2016年12月20日 下午3:04:51
	 * @param request
	 * @param attrKey
	 * @param t
	 */
	public static <T extends Object> void setSessionAttr(HttpServletRequest request, String attrKey, T t){
		session = request.getSession();
		session.setAttribute(attrKey, t);
	}
	
	/**
	 * 从 request中获取对象
	 * @author Ted-wuhuhu
	 * @Time 2016年12月20日 下午5:14:05
	 * @param request
	 * @param paramKey
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T getReqParam(HttpServletRequest request, String paramKey){
		T t = (T) request.getParameter(paramKey);
		return t;
	} 
}
