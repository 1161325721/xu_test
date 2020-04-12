package com.xu;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class test_4 {

    private String baseUri;
    private CloseableHttpClient CloseableHttpClient;
    private List<String> list;

    public static void main(String[] args) {
        new test_4().doMain();
//        new test_4().doCondition();
    }

    public test_4(){
        CloseableHttpClient = HttpClients.createDefault();
        baseUri = "https://xm.esf.fang.com/";
    }

    public void doCondition(){

        baseUri = baseUri + "/house/c20-d2300-l3100";

        for (int i = 0; i < page(); i++) {

        }




    }

    public int page(){
        try {
            CloseableHttpResponse response = CloseableHttpClient.execute(new HttpGet(baseUri));
            HttpEntity entity = response.getEntity();
            String html = EntityUtils.toString(entity,"GBK");
            Document document = Jsoup.parse(html);
            String realUrl = document.select(".redirect a").first().attr("href");
            response = CloseableHttpClient.execute(new HttpGet(realUrl));
            html = EntityUtils.toString(response.getEntity(),"GBK");
            document = Jsoup.parse(html);
            Element element = document.select(".page_al p").last();
            System.out.println(element.text());
            String pageStr = element.text();

            return Integer.valueOf(pageStr.substring(1,pageStr.length()-1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void doMain(){
        HttpGet httpGet = new HttpGet(baseUri);
        try {
            CloseableHttpResponse response = CloseableHttpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            String html = EntityUtils.toString(httpEntity);
            htmlAnalysis(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void htmlAnalysis(String html){
        Document document = Jsoup.parse(html);
        Elements element = document.select(".shop_list h4 a");
        Map<String,List<String>> housedata = new HashMap<String, List<String>>();
        for (int i = 0; i <1; i++) {
            String href = element.get(i).attr("href");
            String id  = href.substring(href.lastIndexOf("/")+1,href.lastIndexOf(".htm"));
            String title = element.get(i).attr("title");
            System.out.println(id);
            System.out.println(title);
            list = new ArrayList();
            list.add("房源编号："+id);
            list.add("房源标题："+title);
            housedata.put(id,list);
            String hourseUri = "https://xm.esf.fang.com/"+href;
            skip(hourseUri);
        }
        outTxt(housedata);
    }

    public void houseInfo(String houseUri){
        HttpGet httpGet = new HttpGet(houseUri);
        try {
            CloseableHttpResponse response = CloseableHttpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode()!=200){
                return;
            }
            HttpEntity httpEntity = response.getEntity();

            String html = EntityUtils.toString(httpEntity);
            Document document = Jsoup.parse(html);
            System.out.println(html);
            String name = document.select(".zf_jjname a").first().text();
            System.out.println(name);
            String company = document.select(".tjcont_list_cline4 .gray6").first().text();
            System.out.println(company);
            list.add("经纪人："+name);
            list.add("所在公司："+company);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void skip(String houseUri){
        HttpGet httpGet = new HttpGet(houseUri);
        try {
            CloseableHttpResponse response = CloseableHttpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            System.out.println(response.getHeaders("location"));
            Header header = response.getFirstHeader("location");
            System.out.println("locationHeader:"+header);

//            String html = EntityUtils.toString(httpEntity);
//            Document document = Jsoup.parse(html);
//            String realUrl = document.select(".redirect a").first().attr("href");
//            System.out.println(realUrl);
//            houseInfo(realUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void outTxt(Map data){
        try {
            FileWriter fileWriter = new FileWriter("D:/house2.txt");
            StringBuffer stringBuffer = new StringBuffer();
            Iterator iterator = data.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry entity = (Map.Entry) iterator.next();
                List<String> l = (List) entity.getValue();
                for (String s : l) {
                    stringBuffer.append(s+"\r\n");
                }
                stringBuffer.append("\r\n");
            }
            fileWriter.write(stringBuffer.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
