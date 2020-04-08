package com.xu;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class test_2 {

    public static void main(String[] args) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("phone", "13800000021"));
        formparams.add(new BasicNameValuePair("userPwd", "sup.787"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        String url = "http://xms.4846.com/ajax/login.do";
//        String url2 = "http://xms.4846.com/main.do";
//        HttpGet httpGet = new HttpGet(url);
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entity);

        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            String json = EntityUtils.toString(httpEntity,"GBK");
            System.out.println(json);
            JSONObject jsonObject = JSONObject.parseObject(json);
            String name = "trueName";
            System.out.println("账号真实名:"+new test_2().searchKey(jsonObject,name));

//            httpPost.setURI(URI.create(url2));
//            CloseableHttpResponse response2 = httpClient.execute(httpPost);
//            System.out.println(EntityUtils.toString(response2.getEntity(),"GBK"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //遍历json查找
    public Object searchKey(JSONObject jsonObject,String keyName){

        if (jsonObject.containsKey(keyName)){
            return jsonObject.get(keyName);
        }
        Iterator keys = jsonObject.keySet().iterator();
        while (keys.hasNext()){
            String key = String.valueOf(keys.next());
            if (key.equals(keyName)){
                return jsonObject.get(keyName);
            }else {
                return searchKey(jsonObject.getJSONObject(key),keyName);
            }
        }
        return null;
    }

}
