package com.duobei.selenium.base;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;


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







}

