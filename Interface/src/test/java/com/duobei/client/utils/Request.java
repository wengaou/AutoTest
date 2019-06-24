package com.duobei.api.utils;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request {

    /**
     * 创建有参数的Get请求
     * @param url
     * @param param
     * @return
     */
    public String doGet(String url, Map<String,String> param){
        //创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        CloseableHttpResponse response = null;
        try {
            //创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null){
                for (String key : param.keySet()){
                    builder.addParameter(key,param.get(key));
                }
            }
            URI uri = builder.build();
            //创建http get请求
            HttpGet get = new HttpGet(uri);
            //执行get请求
            response = httpClient.execute(get);
            //判断返回状态码是否为 200
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            } else {
                System.out.println("服务器响应错误："+ statusCode);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (response != null){
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 创建没有参数的Get请求
     * @param url
     * @return
     */
    public String doGet(String url){
        return doGet(url,null);
    }




    /**
     * 创建有参数的Post请求
     * @param url
     * @param param
     * @return
     */
    public String doPost(String url, Map<String,String> param){
        //创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "";
        try {
            //创建http post请求
            HttpPost post = new HttpPost(url);
            //创建参数列表
            if (param != null){
                List<NameValuePair> pairs = new ArrayList<NameValuePair>();
                for (String key : param.keySet()){
                    pairs.add(new BasicNameValuePair(key, param.get(key)));
                }
                //模拟表单提交
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs,"UTF-8");
                post.setEntity(entity);
            }
            //执行http请求
            response = httpClient.execute(post);
            //判断返回状态码是否为 200
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            } else {
                System.out.println("服务器响应错误："+ statusCode);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 创建没有参数的post请求
     * @param url
     * @return
     */
    public String doPost(String url){
        return doPost(url,null);
    }


    /**
     * 创建参数为Json格式的请求
     * @param url
     * @param json
     * @return
     */
    public String doPostJson(String url,String json){
        //创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "";
        try {
            //创建http post请求
            HttpPost post = new HttpPost(url);
            //创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            post.setEntity(entity);
            //执行http post请求
            response = httpClient.execute(post);
            //判断返回状态码是否为 200
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            } else {
                System.out.println("服务器响应错误："+ statusCode);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                if (response != null){
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 发送正常格式的数据
     */
    public void Post() {
        String url = "https://xue.duobeiyun.com?inviteCode=jz5aynysnf";
        Map<String, String> param = new HashMap<String, String>();
        param.put("", "");
        param.put("", "");
        param.put("", "");
        Request request = new Request();
        String response = request.doPost(url, param);
        System.out.println(response);
    }

    /**
     * 发送Josn格式的数据
     */
    public void postJson(){
        String url = "https://xue.duobeiyun.com?inviteCode=jz5aynysnf";
        Map<String,String> param = new HashMap<String, String>();
        param.put("","");
        param.put("","");
        param.put("","");
        //String postParam = JSON.toJSONString(param);
        //Request request = new Request();
        //String response = request.doPostJson(url,postParam);
        //System.out.println(response);
    }








}
