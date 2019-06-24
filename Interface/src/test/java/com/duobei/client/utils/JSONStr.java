package com.duobei.api.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.Set;

public class JSONStr {


    public static void test(String response) {

        JSONObject jsonObject = JSON.parseObject(response);

//        JSONObject room = jsonObject.getJSONObject("room");
//        String roomId = room.getString("roomId");
//        System.out.println(roomId);
//
//        String startTime = room.getString("startTime");
//        System.out.println(startTime);
//
//        System.out.println(jsonObject.size());

        Set<Map.Entry<String,Object>> entrySet = jsonObject.entrySet();
        for (Map.Entry<String,Object> entry : entrySet){
            if (entry.getKey().equals("roomId")){
                System.out.println("++"+entry.getValue());
            }
        }
    }



}
