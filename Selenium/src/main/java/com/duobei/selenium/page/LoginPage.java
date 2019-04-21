package com.duobei.selenium.page;

import com.duobei.selenium.base.DriverBase;
import com.duobei.selenium.utils.getByLocator;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    public LoginPage(DriverBase driver) {
        super(driver);
    }

    /**
     *获取用户名输入框
     */
    public WebElement getUserElement() {
        return element(getByLocator.getLocator("loginPage_Username"));
    }

    /**
     * 获取密码输入框
     * @return
     */
    public WebElement getPasswordElement(){
        return element(getByLocator.getLocator("loginPage_Password"));
    }

    /**
     * 获取登录按钮
     */
    public WebElement getLoginButtonElement(){
        return element(getByLocator.getLocator("loginPage_LoginButton"));
    }

//    /**
//     * 获取用户信息
//     * @return
//     */
//    public WebElement getUsernameElement(){
//        return element(getByLocator.getLocator("userNameInfo"));
//    }




}
