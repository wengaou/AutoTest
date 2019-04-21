package com.duobei.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Demo {

    WebDriver driver;

    @Test
    public void test(){
        driver.get("http://tea1.gxzjy.com:8088/?q=custom_user_login");

    }




    @BeforeClass
    public void beforeClass(){
        //获取当前的工作目录
        String path = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver",path+"/driver/chromedriver");

        driver = new ChromeDriver();

    }


    @AfterClass
    public void afterClass(){

        driver.close();
    }


}
