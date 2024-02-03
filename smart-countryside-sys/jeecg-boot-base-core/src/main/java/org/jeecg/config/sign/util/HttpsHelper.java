package org.jeecg.config.sign.util;/**
 * @title: HttpUtils2
 * @projectName qfy-project-task-system
 * @description: TODO
 * @author 马强
 * @date 2022/10/12 16:54
 */

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author     ：马强
 * @date       ：Created in 2022/10/12 16:54
 * @description：
 * @modified By：
 * @version:
 */
public class HttpsHelper {
    private static Charset DEF_CHARSET = Charset.forName("UTF-8");

    //Http协议GET请求
    public static String httpGet(String url,Map<String,String> headers) throws Exception{
        //初始化HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建HttpGet
        HttpGet httpGet = new HttpGet(url);

        if(headers != null ){
            for (Map.Entry<String,String> entity: headers.entrySet()) {
                httpGet.setHeader(entity.getKey(),entity.getValue());
            }
        }

        //发起请求，获取response对象
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //获取请求状态码
        //response.getStatusLine().getStatusCode();
        //获取返回数据实体对象
        HttpEntity entity = response.getEntity();
        //转为字符串
        String result = EntityUtils.toString(entity,"UTF-8");
        return result;

    }

    //Http协议Post请求
    public static String httpPost(String url,String json) throws Exception{
        //初始HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建Post对象
        HttpPost httpPost = new HttpPost(url);
        //设置Content-Type
        httpPost.setHeader("Content-Type", String.format("application/json; charset=%s", DEF_CHARSET.name()));
        //写入JSON数据
        httpPost.setEntity(new StringEntity(json,DEF_CHARSET.name()));
        //发起请求，获取response对象
        CloseableHttpResponse response = httpClient.execute(httpPost);
        //获取请求码
        //response.getStatusLine().getStatusCode();
        //获取返回数据实体对象
        HttpEntity entity = response.getEntity();
        //转为字符串
        String result = EntityUtils.toString(entity,"UTF-8");
        return result;
    }

    //Http协议Post请求
    public static String httpPut(String url,String json,Map<String,String> headers) throws Exception{
        //初始HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建Post对象
        HttpPut httpput = new HttpPut(url);
        //设置Content-Type
        httpput.setHeader("Content-Type", String.format("application/json; charset=%s", DEF_CHARSET.name()));
        if(headers != null ){
            for (Map.Entry<String,String> entity: headers.entrySet()) {
                httpput.setHeader(entity.getKey(),entity.getValue());
            }
        }

        //写入JSON数据
        httpput.setEntity(new StringEntity(json,DEF_CHARSET.name()));
        //发起请求，获取response对象
        CloseableHttpResponse response = httpClient.execute(httpput);
        //获取返回数据实体对象
        HttpEntity entity = response.getEntity();
        //转为字符串
        String result = EntityUtils.toString(entity,"UTF-8");
        return result;
    }

    //Https协议Get请求
    public static String httpsGet(String url, Map<String,String> headers) throws Exception{
        CloseableHttpClient hp = createSSLClientDefault();
        HttpGet hg = new HttpGet(url);
        if(headers != null ){
            for (Map.Entry<String,String> entity: headers.entrySet()) {
                hg.setHeader(entity.getKey(),entity.getValue());
            }
        }

        CloseableHttpResponse response = hp.execute(hg);
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity,"UTF-8");
        hp.close();
        return content;
    }
    //Https协议Post请求
    public static String httpsPost(String url, String json, Map<String,String> headers) throws Exception{

        CloseableHttpClient hp = createSSLClientDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", String.format("application/json; charset=%s", DEF_CHARSET.name()));

        if(headers != null ){
            for (Map.Entry<String,String> entity: headers.entrySet()) {
                httpPost.setHeader(entity.getKey(),entity.getValue());
            }
        }

        if(StringUtils.isNotEmpty(json)){
            httpPost.setEntity(new StringEntity(json,DEF_CHARSET.name()));
        }

        CloseableHttpResponse response = hp.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity,"UTF-8");
        hp.close();
        return content;
    }



    //Https协议Post请求
    public static String httpsPut(String url, String json, Map<String,String> headers) throws Exception{

        CloseableHttpClient hp = createSSLClientDefault();
        HttpPut httpPut = new HttpPut(url);
//      httpPut.setHeader("Content-Type", String.format("application/json; charset=%s", DEF_CHARSET.name()));


        if(headers != null ){
            for (Map.Entry<String,String> entity: headers.entrySet()) {
                httpPut.setHeader(entity.getKey(),entity.getValue());
            }
        }

        if(StringUtils.isNotEmpty(json)){
            httpPut.setEntity(new StringEntity(json,DEF_CHARSET.name()));
        }

        CloseableHttpResponse response = hp.execute(httpPut);
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity,"UTF-8");
        hp.close();
        return content;
    }


    //Https协议Post请求
    public static String httpsPostMap(String url,  Map<String, String> params, Map<String,String> headers) throws Exception{

        CloseableHttpClient hp = createSSLClientDefault();
        HttpPost httpPost = new HttpPost(url);
//        httpPost.setHeader("Content-Type", String.format("application/json; charset=%s", DEF_CHARSET.name()));

        if(headers != null ){
            for (Map.Entry<String,String> entity: headers.entrySet()) {
                httpPost.setHeader(entity.getKey(),entity.getValue());
            }
        }

        //设置参数
        List<NameValuePair> list = new ArrayList<>();
        if(params != null){
            for (Map.Entry<String,String> entity : params.entrySet()){
                list.add(new BasicNameValuePair(entity.getKey(), entity.getValue()));
            }
        }

        if (list.size() > 0) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, DEF_CHARSET);
            httpPost.setEntity(entity);
        }

        CloseableHttpResponse response = hp.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity,"UTF-8");
        hp.close();
        return content;
    }



    public static CloseableHttpClient createSSLClientDefault() throws Exception{
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build(),
                NoopHostnameVerifier.INSTANCE);
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();

    }







}
