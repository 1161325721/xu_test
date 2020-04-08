package com.xu;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class test_3 {


    private int resultCount;
    private Map data = new HashMap();

    private String uri;

//    private int maxPrice;
//    private int minPrice;
//
//    private int maxArea;
//    private int minArea;
//
//    private int maxPage;

    public static void main(String[] args) {


        new test_3().conditionByArea();
    }


    public void conditionByArea(){
        String baseUri = "http://xm.maitian.cn/esfall/";

        for (int i = 0; i <= 18; i++) {

            if (i==18){
                uri = baseUri + "A8";
            }else {
                uri = baseUri + "AB" + (i * 10) + "AE" + (i + 1) * 10;
                System.out.println(i*10+"-"+(i+1)*10);
            }
            findInfo();
        }


    }



    public void findInfo(){

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);
        try {

            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String html = EntityUtils.toString(entity,"GBK");
            searchResultCount(html);

            int page = resultCount%10 > 0 ? (resultCount/10)+1 : resultCount/10;
            System.out.println("页数："+page);

            for (int i = 1; i <= page; i++) {

                httpGet.setURI(URI.create(uri+"PG"+i));
                response = httpClient.execute(httpGet);
                entity = response.getEntity();
                html = EntityUtils.toString(entity,"GBK");
                selectInfo(html);
            }

            System.out.println("data大小"+data.size());


//            selectInfo(html);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void searchResultCount(String html){
        Document document = Jsoup.parse(html);
        Element p = document.getElementsByClass("search_result").first();
        String count = p.children().first().text();
        resultCount = Integer.valueOf(count);
        System.out.println("房产信息数："+resultCount);
    }

    public void selectInfo(String html){

        Document document = Jsoup.parse(html);
        Element div = document.getElementsByClass("list_wrap").first();
        Element div_ul =  div.children().first();
        Elements div_ul_lis = div_ul.children();

        for (int i = 0; i < div_ul_lis.size(); i++) {

            Map house = new HashMap();
            Element div_ul_li = div_ul_lis.get(i);
            String id = findId(div_ul_li);
            String title = findTitle(div_ul_li);
            String houseInfo = findHouseInfo(div_ul_li);
            String houseHot = findHouseHot(div_ul_li);
            String dl = findDl(div_ul_li);
            String price = findPrice(div_ul_li);
            String area = findArea(div_ul_li);

            house.put("id",id);
            house.put("title",title);
            house.put("houseInfo",houseInfo);
            house.put("houseHot",houseHot);
            house.put("dl",dl);
            house.put("price",price);
            house.put("area",area);

            data.put(id,house);

        }

    }

    public String findId(Element element){
        String text = element.getElementsByTag("a").first().attr("href");
        String id = text.substring(text.lastIndexOf("H")+1);
        return id;
    }

    public String findTitle(Element element){
        String title = element.getElementsByClass("list_title").first().getElementsByTag("h1").first().getElementsByTag("a").first().text();
        return title;
    }

    public String findHouseInfo(Element element){
        String houseInfo = element.getElementsByClass("list_title").first().getElementsByClass("house_info").first().text();
        return houseInfo;
    }

    public String findHouseHot(Element element){
        String househot = element.getElementsByClass("list_title").first().getElementsByClass("house_hot").first().text();
        return househot;
    }

    public String findDl(Element element){
        String dl = element.getElementsByTag("dl").first().text();
        return dl;
    }

    public String findPrice(Element element){
        String price = element.getElementsByClass("the_price").first().text();
        return price;
    }

    public String findArea(Element element){
        String area = element.getElementsByClass("the_area").first().text();
        return area;
    }
}
