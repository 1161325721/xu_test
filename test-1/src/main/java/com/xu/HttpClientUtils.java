package com.xu;

import com.sun.net.httpserver.Headers;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HttpClientUtils {

    private CloseableHttpClient httpClient ;

    public HttpClientUtils(){
        httpClient = HttpClients.createDefault();
    }

    public String doGet(String url){
        String res = "";
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("User-Agent","Mozilla/5.0 (Linux; U; Android 8.1.0; zh-cn; BLA-AL00 Build/HUAWEIBLA-AL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/8.9 Mobile Safari/537.36");
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            res = EntityUtils.toString(entity,"GBK");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public String doGet(String url, Map<String,String> headers,Map<String,String> parameters){
        String res = "";
        CloseableHttpResponse response = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);

            //设置参数
            List<NameValuePair> list = new LinkedList<>();
            for(Map.Entry<String,String> entry : parameters.entrySet()){
                System.out.println(entry.getKey()+":"+entry.getValue());
                list.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
            }
            uriBuilder.setParameters(list);

            HttpGet httpGet = new HttpGet(uriBuilder.build());

            System.out.println(uriBuilder.build());

            //设置header
            for (Map.Entry<String,String> entry : headers.entrySet()) {
                System.out.println(entry.getKey()+":"+entry.getValue());
                httpGet.setHeader(entry.getKey(),entry.getValue());
            }

            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            res = EntityUtils.toString(entity,"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public String doPost(String url){
        String res = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("User-Agent","Mozilla/5.0 (Linux; U; Android 8.1.0; zh-cn; BLA-AL00 Build/HUAWEIBLA-AL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/8.9 Mobile Safari/537.36");
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            res = EntityUtils.toString(entity,"GBK");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }
    public String doPost(String url, Map<String,String> headers){
        String res = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("User-Agent","Mozilla/5.0 (Linux; U; Android 8.1.0; zh-cn; BLA-AL00 Build/HUAWEIBLA-AL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/8.9 Mobile Safari/537.36");
            for (Map.Entry<String,String> entry : headers.entrySet()) {
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());
                httpPost.setHeader(entry.getKey(),entry.getValue());
            }
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            res = EntityUtils.toString(entity,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

}
