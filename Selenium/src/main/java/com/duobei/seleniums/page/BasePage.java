package com.duobei.seleniums.page;

import com.duobei.seleniums.base.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BasePage {

    public DriverBase driverBase;

    public BasePage(DriverBase driverBase){
        this.driverBase = driverBase;
    }

    /**
     * 定位element
     * @param by
     * @return
     */
    public WebElement element(By by){
        return driverBase.isElementExist(by);
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
