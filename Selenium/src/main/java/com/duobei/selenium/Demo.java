package com.duobei.selenium;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.duobei.selenium.base.DriverBase;
import com.duobei.selenium.utils.MD5Utils;
import com.duobei.selenium.utils.Request;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class Demo {

    WebDriver driver;

    public static Logger log = Logger.getLogger(DriverBase.class);



    @BeforeClass
    public void beforeClass() {

        //String driverPath=System.getProperty("user.dir")+"/geckodriver-v0.21.0-win64/geckodriver.exe";
//        //获取当前的工作目录
        String path = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", path + "/driver/chromedriver");
        driver = new ChromeDriver();


//        Map<String, Object> prefs = new HashMap<>();
//        prefs.put("profile.default_content_setting_values.media_stream_camera",1);
//
//        prefs.put("profile.default_content_setting_values.media_stream_mic",1);
//        prefs.put("profile.default_content_setting_values.notifications",1);
//        prefs.put("profile.default_content_setting_values.geolocation",1);
//
//        prefs.put("profile.managed_default_content_settings.images",1);
//        prefs.put("profile.content_settings.plugin_whitelist.adobe-flash-player",1);
//        prefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player",1);


//        ChromeOptions options = new ChromeOptions();
        //关闭使用ChromeDriver打开浏览器时上部提示语"Chrome正在受到自动软件的控制"
//        options.addArguments("disable-infobars");
//        options.addArguments("user-data-dir=/Users/zhangwengao/Library/Application Support/Google/Chrome/Default");
        //options.addArguments("--start-maximized");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-features=EnableEphemeralFlashPermission");
//        Map<String, Object> prefs = new HashMap<>();
//        prefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player",1);
//        options.setExperimentalOption("prefs", prefs);
//        driver = new ChromeDriver(options);



//        options.setExperimentalOption("prefs", prefs);

//        driver = new ChromeDriver(options);






    }


    @Test
    public void test() {
//        log.debug("debug---------");
        log.info("info---------");
        log.warn("warn---------");
        log.error("error---------");




        driver.get("https://xue.duobeiyun.com?inviteCode=jz34ardnuz");
//        driver.findElement(By.id("nickname")).sendKeys("测试教师");
//        driver.findElement(By.className("submit_btn")).click();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//        if (driver.findElement(By.className("btn_enable_flash")).isDisplayed()) {
//            System.out.println("flash提示已出现");
//            driver.findElement(By.className("btn_enable_flash")).click();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        } else {
//            System.out.println("错误");
//        }
//
//
//
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }











//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }


    public  void getTeacherLink(){

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
        String response = request.doPost(url,param);

        JSONObject jsonObject = JSON.parseObject(response);

        JSONObject room = jsonObject.getJSONObject("room");

        //遍历JSONObject对象取出对应的Value
        Set<Map.Entry<String,Object>> entrySet = room.entrySet();
        for (Map.Entry<String,Object> entry : entrySet){
            if (entry.getKey().equals("roomId")){
                System.out.println(entry.getValue());
            }
           // System.out.println(entry);
        }








        //System.out.println(response);

    }





    @AfterClass
    public void afterClass(){
//        log.debug("debug---------");
        log.info("info---------");
        log.warn("warn---------");
        log.error("error---------");

        driver.close();
    }


}
