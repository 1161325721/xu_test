package com.xu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.script.ScriptException;

public class test_6 {

    public static void main(String[] args) {

        String url = "http://www.52jiaozhou.com/m/classad/1140780.aspx";
        String html = new HttpClientUtils().doGet(url);
        Document document = Jsoup.parse(html);
        String script = document.select("article script").first().data();
        String newScript = script.substring(0, script.indexOf("$('tel')"));
        ScriptEngineManagerUtils s = new ScriptEngineManagerUtils();
        s.eval(newScript);
        System.out.println(s.getVar("str"));

    }


}
