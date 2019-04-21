package com.duobei.appium.page;

import com.duobei.appium.base.DriverBase;
import com.duobei.appium.utils.getByLocator;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    public HomePage(DriverBase driver) {
        super(driver);
    }

    /**
     * 获取首页登录按钮
     * @return
     */
    public WebElement getLoginButtonElement(){
        return element(getByLocator.getLocator("homePage_LoginButton"));
    }

    /**
     * 获取首页登录状态信息
     * @return
     */
    public WebElement getLoginInfoElement(){
        return element(getByLocator.getLocator("homePage_LoginInfo"));
    }

    /**
     * 获取首页登录的用户名
     * @return
     */
    public WebElement getUserNameElement(){
        return element(getByLocator.getLocator("homePage_UserName"));
    }

}
