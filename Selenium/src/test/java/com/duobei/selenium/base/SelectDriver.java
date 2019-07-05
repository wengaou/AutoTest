package com.duobei.selenium.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SelectDriver {


    /**
     * 选择浏览器并实例化driver对象：
     *      默认只有firefox和chrome浏览器
     * @param browserName  浏览器名称
     * @return
     */
    public WebDriver driverName(String browserName){
        WebDriver driver = null;
        //获取当前的工作目录
        String path = System.getProperty("user.dir");

        if (browserName.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", path+"/driver/geckodriver");
            driver = new FirefoxDriver();
        }else if (browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver",path+"/driver/chromedriver");
            driver = new ChromeDriver();
        }
        return driver;
    }

}
