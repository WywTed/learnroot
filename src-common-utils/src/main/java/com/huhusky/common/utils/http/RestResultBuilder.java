package com.huhusky.common.utils.http;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by shawn on 2017/3/31.
 */
public class RestResultBuilder {

    private int errcode;
    private String errmsg;
    private JSONObject data;

    public static final int ERR_CODE_MISS_REQUEST_PARAMS = 1000;

    public RestResultBuilder(int errcode, String errmsg) {
        data = new JSONObject();
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public RestResultBuilder(int errcode) {
        data = new JSONObject();
        this.errcode = errcode;
    }

    public RestResultBuilder addParam(String key, Object object) {
        data.put(key, object);
        return this;
    }

    public JSONObject build() {
        data.put("errcode", errcode);
        data.put("errmsg", errmsg);
        return data;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public JSONObject getData(){
        return data;
    }
}
