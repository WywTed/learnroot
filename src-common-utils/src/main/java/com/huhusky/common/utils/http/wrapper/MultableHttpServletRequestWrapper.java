package com.huhusky.common.utils.http.wrapper;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author wuhuhu
 * @create 2017/4/26 14:47
 */
public class MultableHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public String newRequestInfo = "";
    public int contentLength = 0;

    @Override
    public int getContentLength() {
        return this.contentLength;
    }

    public long getContentLengthLong() {
        return this.contentLength;
    }

    private final Map<String, String> customHeaders;

    public MultableHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.customHeaders = new HashMap<>();
    }

    public void putHeader(String name, String value) {
        this.customHeaders.put(name, value);
    }

    public String getHeader(String name) {
        if (name.equals("content-length")) {
            return contentLength + "";
        }

        String headerValue = customHeaders.get(name);
        if (headerValue != null) {
            return headerValue;
        }
        return ((HttpServletRequest) getRequest()).getHeader(name);
    }

    public Enumeration<String> getHeaderNames() {
        Set<String> set = new HashSet<String>(customHeaders.keySet());
        @SuppressWarnings("unchecked")
        Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaderNames();
        while (e.hasMoreElements()) {
            String n = e.nextElement();
            set.add(n);
        }
        return Collections.enumeration(set);
    }

    public ServletInputStream getInputStream() {
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(newRequestInfo.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new WrapperServletInputStream(bais);
    }
}
