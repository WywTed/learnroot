package com.huhusky.common.utils.http;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.huhusky.common.utils.exception.HttpRequestAbortedException;

public class HttpProxy {

    private final static Logger logger = Logger.getLogger(HttpProxy.class);

    public static String post(String url, String rawContent) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json");
            StringEntity reqEntity = new StringEntity(rawContent, "UTF-8");
            httpPost.setEntity(reqEntity);
            CloseableHttpResponse response = httpclient.execute(httpPost);

            try {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != 200) {
                    throw new HttpRequestAbortedException(statusCode + "");
                }
                HttpEntity entity = response.getEntity();
                String responseContent = EntityUtils.toString(entity, "UTF-8");
                return responseContent;
            } finally {
                try {
                    if (response != null) {
                        response.close();
                    }
                } catch (IOException e) {
                    logger.error("http关闭CloseableHttpResponse失败");
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (httpclient != null) {
                    httpclient.close();
                }

            } catch (IOException e) {
                logger.error("http关闭CloseableHttpClient失败");
                e.printStackTrace();
            }
        }
    }

    public static String httpsPost(String url, String rawContent) throws Exception {

        CloseableHttpClient httpclient = null;

        try {
            httpclient = buildHttpsClient();
            HttpPost httpPost = new HttpPost(url);
            StringEntity reqEntity = new StringEntity(rawContent, "UTF-8");
            httpPost.setEntity(reqEntity);
            CloseableHttpResponse response = httpclient.execute(httpPost);

            try {
                HttpEntity entity = response.getEntity();
                String responseContent = EntityUtils.toString(entity, "UTF-8");
                return responseContent;
            } finally {
                try {
                    if (response != null) {
                        response.close();
                    }
                } catch (IOException e) {
                    logger.error("http关闭CloseableHttpResponse失败");
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (httpclient != null) {
                    httpclient.close();
                }

            } catch (IOException e) {
                logger.error("http关闭CloseableHttpClient失败");
                e.printStackTrace();
            }
        }
    }

    public static String httpsGet(String uri) throws Exception {

        CloseableHttpClient httpclient = null;

        try {
            httpclient = buildHttpsClient();
            HttpGet httpGet = new HttpGet(uri);
            CloseableHttpResponse response = httpclient.execute(httpGet);

            try {
                HttpEntity entity = response.getEntity();
                String responseContent = EntityUtils.toString(entity, "UTF-8");
                return responseContent;
            } finally {
                try {
                    if (response != null) {
                        response.close();
                    }
                } catch (IOException e) {
                    logger.error("http关闭CloseableHttpResponse失败");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (httpclient != null) {
                    httpclient.close();
                }

            } catch (IOException e) {
                logger.error("http关闭CloseableHttpClient失败");
                e.printStackTrace();
            }
        }
    }

    private static CloseableHttpClient buildHttpsClient() throws KeyManagementException, NoSuchAlgorithmException {
        X509TrustManager x509mgr = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] xcs, String string) {
            }

            public void checkServerTrusted(X509Certificate[] xcs, String string) {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[]{x509mgr}, null);
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        return httpclient;
    }

}
