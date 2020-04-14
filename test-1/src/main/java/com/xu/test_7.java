package com.xu;

import java.util.HashMap;
import java.util.Map;

public class test_7 {

    public static void main(String[] args) {

//        String url = "https://gs.getui.com/geshu/smartStatistics/upe";
        String url = "https://soufunapp.3g.fang.com/http/sfpgservice.jsp";

        /**
         *
         *
         *
         *GET https://soufunapp.3g.fang.com/http/sfpgservice.jsp?
         *
         * city=%E5%8E%A6%E9%97%A8&
         * gettype=android&
         * jkVersion=2&
         * maptype=baidu&
         * messagename=lplist&
         * page=1&
         * pagesize=20&
         * tongji=2&
         * wirelesscode=838CCEE88BA6A186DFC864DCA042988B HTTP/1.1
         *
         * Connection: keep-alive
         * pagesc: fjhomepage
         * city: %E5%8E%A6%E9%97%A8
         * company: 32057
         * user-agent: Android_UnMap%7EMuMu%7E6.0.1
         * app-name: Android_UnMap
         * imei: 300000000117850
         * iscard: 0
         * posmode: gps%2Cwifi
         * version: 9.0.5
         * osVersion: 6.0.1
         * connmode: Wifi
         * unique_deviceID: 4331b39037be6876fc7626723c8166f5
         * fixState: 0
         * X1: 116.403699
         * android_id: b053d31571b97e13
         * shop-project: fang-app-android
         * serial_number: ZX1G42CPJD
         * mac: 08%3A00%3A27%3A94%3A44%3AEE
         * screenheight: 683
         * simInfo: 1%2C0
         * wirelessCheckCode: c3a8f5212f69db78d31c18d1062989fcf04ab8a4a56510ee
         * User-Agent: Android_UnMap%7EMuMu%7E6.0.1
         * networktype: wifi
         * sessionid: fb23bf96-20d8-4e94-bedb-77100238dd64
         * screenwidth: 384
         * intention: 1
         * ispos: 1
         * buildversion: 20033124
         * Y1: 39.914938
         * language: zh_CN
         * uniquePsuedoID: ffffffff-c95c-3015-ffff-ffffc2e834d9
         * model: MuMu
         * Host: soufunapp.3g.fang.com
         * Accept-Encoding: gzip
         *
         *
         * HTTP/1.1 200 OK
         * Server: nginx
         * Date: Tue, 14 Apr 2020 06:55:35 GMT
         * Content-Type: text/xml; charset=utf-8
         * Connection: keep-alive
         * Vary: Accept-Encoding
         * X-UA-Compatible: IE=EmulateIE7
         * Keep-Alive: timeout=60, max=100
         * X-Cache: MISS from sjhl-wn-mhc05.light.soufun.com
         * Content-Length: 89051
         *
         */

        Map<String,String> headers = new HashMap<>();



//        headers.put("Connection","keep-alive");
//        headers.put("pagesc","fjhomepage");
//        headers.put("imei","fb23bf96-20d8-4e94-bedb-77100238dd64");
//        headers.put("posmode","300000000117850");
//        headers.put("sessionid","gps%2Cwifi");
//        headers.put("sessionid","fb23bf96-20d8-4e94-bedb-77100238dd64");
        headers.put("User-Agent","Android_UnMap%7EMuMu%7E6.0.1");
//        headers.put("wirelessCheckCode","c3a8f5212f69db78d31c18d1062989fcf04ab8a4a56510ee");
//        headers.put("X1","116.403699");
//        headers.put("Y1","39.914938");
//        headers.put("uniquePsuedoID","ffffffff-c95c-3015-ffff-ffffc2e834d9");
//        headers.put("Host","soufunapp.3g.fang.com");
//        headers.put("Accept-Encoding","gzip");

        Map<String,String> parameters = new HashMap<>();
        parameters.put("city","厦门");
        parameters.put("gettype","android");
        parameters.put("jkVersion","2");
        parameters.put("maptype","baidu");
        parameters.put("messagename","lplist");
        parameters.put("page","1");
        parameters.put("pagesize","20");
        parameters.put("tongji","2");
        parameters.put("wirelesscode","838CCEE88BA6A186DFC864DCA042988B");

        String html = new HttpClientUtils().doGet(url,headers,parameters);
        System.out.println(html);

    }


}
