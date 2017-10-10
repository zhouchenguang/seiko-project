/*
 * Copyright (C),2016-2016. 华住酒店管理有限公司
 * FileName: HttpClientHandler.java
 * Author:   CHENGANMIN001
 * Date:     2016-06-14 17:18:35
 * Description: //模块目的、功能描述
 * History: //修改记录 修改人姓名 修改时间 版本号 描述
 * <CHENGANMIN001><2016-06-14 17:18:35><version><desc>
 *
 */

package com.seiko.handler;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpClientHandler {
    private static Logger log = LogManager.getLogger(HttpClientHandler.class);

    /**
     * @param url     传入url
     * @param nvps    传入参数
     * @param isSSL   是否需要ssl验证
     * @param headMap post head 参数
     * @return
     */
    public static JSONObject postDataByHeader(String url, List<BasicNameValuePair> nvps, boolean isSSL, Map<String, String> headMap) {
        HttpClient client = new DefaultHttpClient();
        if (isSSL) {
            doSSLSocket(client);
        }
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        try {
            if (null != headMap) {
                Iterator<Map.Entry<String, String>> it = headMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> entry = it.next();
                    post.addHeader(entry.getKey(), entry.getValue());
                }
            }

            post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8)); // 设置参数给Post

            HttpResponse res = client.execute(post);
            log.info("call getJsonDataByHeader===================>({})", res);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.parseObject(result);
                return response;
            }
        } catch (Exception e) {
            log.error("===================>" + e);
        }
        return new JSONObject();
    }

    /**
     * @param url   传入url
     * @param nvps  传入参数
     * @param isSSL 是否需要ssl验证
     * @return
     */
    public static JSONObject postData(String url, List<BasicNameValuePair> nvps, boolean isSSL) {
        HttpClient client = new DefaultHttpClient();
        if (isSSL) {
            doSSLSocket(client);
        }
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        try {
            post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8)); // 设置参数给Post

            HttpResponse res = client.execute(post);
            log.info("call getJsonDataByHeader===================>({})", res);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.parseObject(result);
                return response;
            }
        } catch (Exception e) {
            log.error("===================>" + e);
            e.printStackTrace();
        }
        return new JSONObject();
    }


    /**
     * @param url       传入url
     * @param jsonParam 传入参数
     * @param isSSL     是否需要ssl验证
     * @param headMap   post head 参数
     * @return
     */
    public static JSONObject postJsonDataByHeader(String url, JSONObject jsonParam, boolean isSSL, Map<String, String> headMap) {
        HttpClient client = new DefaultHttpClient();
        if (isSSL) {
            doSSLSocket(client);
        }
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        try {
            if (null != headMap) {
                Iterator<Map.Entry<String, String>> it = headMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> entry = it.next();
                    post.addHeader(entry.getKey(), entry.getValue());
                }
            }

            StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");//解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            post.setEntity(entity);

            HttpResponse res = client.execute(post);
            log.info("call postJsonDataByHeader===================>({})", res);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.parseObject(result);
                return response;
            }
        } catch (Exception e) {
            log.error("===================>" + e);
        }
        return new JSONObject();
    }


    /**
     * @param url       传入url
     * @param jsonParam 传入参数
     * @param isSSL     是否需要ssl验证
     * @param headMap   post head 参数
     * @return
     */
    public static JSONObject getJsonDataByHeader(String url, JSONObject jsonParam, boolean isSSL, Map<String, String> headMap) {
        HttpClient client = new DefaultHttpClient();
        if (isSSL) {
            doSSLSocket(client);
        }
        HttpGet httpGet = new HttpGet(url);
        JSONObject response = null;
        try {
            if (null != headMap) {
                Iterator<Map.Entry<String, String>> it = headMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> entry = it.next();
                    httpGet.addHeader(entry.getKey(), entry.getValue());
                }
            }

            HttpResponse res = client.execute(httpGet);
            log.info("call getJsonDataByHeader===================>({})", res);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.parseObject(result);
                log.info("===================>({})", response);
                return response;
            }
        } catch (Exception e) {
            log.error("===================>" + e);
        }
        return new JSONObject();
    }

    //功能: postBody形式发送数据
    //@param urlPath 对方地址
    //@param json 要传送的数据
    //@return
    //@throws Exception
    public static String postBody(String urlPath, String json) throws Exception {
        try {
            // Configure and open a connection to the site you will send the request
            URL url = new URL(urlPath);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            // 设置doOutput属性为true表示将使用此urlConnection写入数据
            urlConnection.setDoOutput(true);
            // 定义待写入数据的内容类型，我们设置为application/x-www-form-urlencoded类型
            urlConnection.setRequestProperty("content-type", "application/json");
            // 得到请求的输出流对象
            OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream(), "utf-8");
            // 把数据写入请求的Body
            out.write(json);
            out.flush();
            out.close();

            // 从服务器读取响应
            InputStream inputStream = urlConnection.getInputStream();
            String encoding = urlConnection.getContentEncoding();
            String body = IOUtils.toString(inputStream, encoding);
            if (urlConnection.getResponseCode() == 200) {
                return body;
            } else {
                throw new Exception(body);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 通过ssl访问
     *
     * @param httpclient
     */
    private static void doSSLSocket(HttpClient httpclient) {
        try {
            SSLContext ctx = SSLContext.getInstance("SSL");
            X509TrustManager tm = getX509TrustManager();
            ctx.init(null, new TrustManager[]{tm}, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
            ClientConnectionManager ccm = httpclient.getConnectionManager();
            // register https protocol in httpclient's scheme registry
            SchemeRegistry sr = ccm.getSchemeRegistry();
            sr.register(new Scheme("https", 443, ssf));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    private static X509TrustManager getX509TrustManager() {
        X509TrustManager tm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        return tm;
    }
}
