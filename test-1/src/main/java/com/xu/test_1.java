package com.xu;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;

public class test_1 {

    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = "http://xmhouse.com/";
//        String url = "http://xms.4846.com/";

        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            System.out.println(EntityUtils.toString(entity,"GBK"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
