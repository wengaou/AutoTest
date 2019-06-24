package com.duobei.selenium.page;

import com.duobei.selenium.base.DriverBase;
import com.duobei.selenium.utils.getByLocator;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    public HomePage(DriverBase driver) {
        super(driver);
    }

    /**
     * 获取用户名输入框
     * @return
     */
    public WebElement getLoginButtonElement(){
        return element(getByLocator.getLocator("loginPage_Username"));
    }

    /**
     * 获取密码输入框
     * @return
     */
    public WebElement getLoginInfoElement(){
        return element(getByLocator.getLocator("loginPage_Password"));
    }

    /**
     * 获取登录按钮
     * @return
     */
    public WebElement getUserNameElement(){
        return element(getByLocator.getLocator("loginPage_LoginButton"));
    }

}
