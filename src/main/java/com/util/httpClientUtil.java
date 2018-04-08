package com.util;

import net.sf.json.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.Header;

import org.apache.http.impl.client.DefaultHttpClient;

import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlType;


public class httpClientUtil {

    //define utf-8 encoding
    public static final String CHARSET_UTF_8 = "utf-8";

    //http content type. json type
    public static final String CONTENT_TYPE_JSON ="application/json";

    //connect to the connection manager
    private static PoolingHttpClientConnectionManager pool;

    //request config
    private static RequestConfig requestConfig;

    /**
     * httpGet,to send a http get request and get the response
     * @param url
     *
     * @param headers
     * @return CloseableHttpResponse
     */
    public static CloseableHttpResponse httpDoGet(String url,Map<String,String> headers) throws IOException{

        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        String requestUrl = url.trim(); // in case there is space in the url prefix or suffix
        HttpGet request = new HttpGet(requestUrl);
        //给request添加header.如果header参数传入null, 则略过
        if(headers != null){

            Set<String> keys = headers.keySet();

            for(Iterator<String> i = keys.iterator();i.hasNext();){
                String key = (String) i.next();
                request.addHeader(key,headers.get(key));
            }
        }

        CloseableHttpResponse httpResponse = closeableHttpClient.execute(request);

        return httpResponse; //返回执行结果
    }

    public static CloseableHttpResponse httpDoPost(String url, String postData, Map<String, String> headers) throws IOException{

        JSONObject jsonResult = null;

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        if(headers != null){

            Set<String> keys = headers.keySet();

            for(Iterator<String> i = keys.iterator();i.hasNext();){
                String key = (String) i.next();
                httpPost.addHeader(key,headers.get(key));
            }
        }

        //StringEntity entity = new StringEntity(postData.toString(),"utf-8");
        StringEntity entity = new StringEntity(postData,"utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        CloseableHttpResponse httpPostResponse = httpClient.execute(httpPost);
        //System.out.println(httpPostResponse.getStatusLine().getStatusCode());
        //HttpEntity postResultEntity = httpPostResponse.getEntity();
        //if (postResultEntity != null){
        //String strResult = EntityUtils.toString(postResultEntity);
        //jsonResult = JSONObject.fromObject(strResult);
        //System.out.println(strResult);
        //}
        return httpPostResponse;


    }

    /* to get the response code from the response*/
    public static int getResponseStatusCode(CloseableHttpResponse httpResponse){

        int statusCode = httpResponse.getStatusLine().getStatusCode();

        return statusCode;

    }



    /**
     * to get the key value from the response
     * @param httpResponse
     *
     * @param key
     * @return String keyvalue from the http response
     */

    public static String getJsonResponseData(CloseableHttpResponse httpResponse,String key) throws IOException{

        HttpEntity resultEntity = httpResponse.getEntity();
        String strResult = EntityUtils.toString(resultEntity);
        JSONObject jsonResult = JSONObject.fromObject(strResult);
        String keyValue = jsonResult.getString(key);

        return keyValue;
    }










}
