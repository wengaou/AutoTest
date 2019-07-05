package com.duobei.selenium.pages;

import com.duobei.selenium.base.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage {

    private int timeout = 30;

    public DriverBase driverBase;

    public BasePage(DriverBase driverBase){
        this.driverBase = driverBase;
    }

    /**
     * 显示等待封装：
     *      在给定的时间内查找元素是否存在，如果不存在则超时
     * @param by
     * @return
     */
    public WebElement isElementExist(By by) {
        WebElement element = null;
        WebDriverWait webDriverWait = new WebDriverWait(driverBase.getDriver(), timeout);
        try {
            element = webDriverWait.until(new ExpectedCondition<WebElement>() {
                @Override
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(by);
                }
            });
        } catch (TimeoutException e) {
            driverBase.log.error("--->>查找元素超时!!! " + timeout + " 秒之后仍未找到元素 ：[" + by.toString() + "]");
            Assert.fail("--->>查找元素超时!!! " + timeout + " 秒之后仍未找到元素 ：[" + by.toString() + "]");
        }
        return element;
    }


    /**
     * 显示等待封装：
     *      在给定的时间内判断元素是否显示，如果不显示则超时
     * @param by
     * @return
     */
    public boolean isElementDisplay(By by){
        boolean flag = true;
        WebDriverWait webDriverWait = new WebDriverWait(driverBase.getDriver(), timeout);
        try {
            flag = webDriverWait.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    return driver.findElement(by).isDisplayed();
                }
            });
        } catch (TimeoutException e) {
            driverBase.log.error("--->>判断元素显示超时!!! 等待" + timeout + " 秒之后  [" + by.toString() + "] 仍不显示！！！");
            Assert.fail("--->>判断元素显示超时!!! 等待" + timeout + " 秒之后  [" + by.toString() + "] 仍不显示！！！");
        }
        return flag;
    }








    /**
     * 定位element
     * @param by
     * @return
     */
    public WebElement element(By by){
        WebElement element = null;
        if (isElementDisplay(by)){
            element = isElementExist(by);
        }
        return element;

    }






    /**
     * 封装点击
     * @param element
     */
    public void clickElement(WebElement element){


        if (assertElement(element)){
            element.click();
        }else{
            System.out.println("元素没有定位到，点击失败！！！");
        }


    }

    /**
     * 封装输入
     * @param element
     * @param value
     */
    public void sendKeys(WebElement element,String value){
        if (element != null){
            element.sendKeys(value);
        }else {
            System.out.println(element+"元素没有定位到，输入失败！！！"+value);
        }
    }

    /**
     * 判断元素是否显示
     * @param element
     * @return
     */
    public boolean assertElement(WebElement element){





        return element.isDisplayed();
    }

}
