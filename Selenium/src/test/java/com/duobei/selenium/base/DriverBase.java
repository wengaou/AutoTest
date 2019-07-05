package com.duobei.selenium.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;


/**
 * 封装所有selenium提供的操作方法，并生成driver对象
 */
public class DriverBase {

    public WebDriver driver;

    public static Logger log = Logger.getLogger(DriverBase.class);

    private int TimeOut = 30;

    /**
     * 构造方法：
     *          创建对象时实例化driver
     * @param browser
     */
    public DriverBase(String browser){
        SelectDriver selectDriver = new SelectDriver();
        this.driver = selectDriver.driverName(browser);
    }

    /**
     * 获取driver
     * @return
     */
    public WebDriver getDriver(){
        return driver;
    }

    /**
     * 获取Url
     * @param urlText
     * @param url
     */
    public void getUrl(String urlText, String url) {
        driver.get(url);
        log.info("--->>" +urlText+ " " +url);
    }


    public WebElement findElement(By by){

        return driver.findElement(by);

    }



    public void getUrl(String url){

        driver.get(url);

    }


    public void quit(){

        driver.quit();

    }







}

