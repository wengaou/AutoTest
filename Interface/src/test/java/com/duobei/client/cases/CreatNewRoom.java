package com.duobei.api.cases;

import com.duobei.api.utils.MD5Utils;
import com.duobei.api.utils.Request;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.TreeMap;

public class CreatNewRoom {



    @Test
    public void test(){

        long currentTime = System.currentTimeMillis();
        String strTime = String.valueOf(currentTime);
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(currentTime);

        String url = "https://api.duobeiyun.net/api/v3/room/create";
        TreeMap<String, String> param = new TreeMap<String, String>();
        param.put("duration", "5");
        param.put("partner", "20190222034303461");
        param.put("roomType", "5");
        param.put("startTime", time);
        param.put("timestamp", strTime);
        param.put("title", "新版1vn教室mac");
        param.put("video", "1");
        param.put("sign", MD5Utils.getDigest(param));

        Request request = new Request();
        String response = request.doPost(url, param);
        System.out.println(response);

    }



}
