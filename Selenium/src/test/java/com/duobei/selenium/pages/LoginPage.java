package com.duobei.selenium.pages;


import com.duobei.selenium.base.DriverBase;
import com.duobei.selenium.utils.getByLocator;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{

    //继承父类的driver
    public LoginPage(DriverBase driver) {
        super(driver);
    }

    /**
     * 获取用户名输入框
     * @return
     */
    public WebElement getUserInputBoxElement(){

        return element(getByLocator.getLocator("loginPage_Username"));

    }

    /**
     * 获取密码输入框
     * @return
     */
    public WebElement getPasswordInputBoxElement(){

        return element(getByLocator.getLocator("loginPage_Password"));

    }

    /**
     * 获取登录按钮
     * @return
     */
    public WebElement getLoginButton(){

        return element(getByLocator.getLocator("loginPage_LoginButton"));
    }






}
