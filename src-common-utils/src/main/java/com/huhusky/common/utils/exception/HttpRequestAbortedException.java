package com.huhusky.common.utils.exception;

public class HttpRequestAbortedException extends RuntimeException {

	private static final long serialVersionUID = 2599998234730337652L;

	public HttpRequestAbortedException() {
		super();
	}

	public HttpRequestAbortedException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public HttpRequestAbortedException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public HttpRequestAbortedException(String arg0) {
		super(arg0);
	}

	public HttpRequestAbortedException(Throwable arg0) {
		super(arg0);
	}

}
