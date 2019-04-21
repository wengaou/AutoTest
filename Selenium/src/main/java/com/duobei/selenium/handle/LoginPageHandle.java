package com.duobei.selenium.handle;

import com.duobei.selenium.base.DriverBase;
import com.duobei.selenium.page.LoginPage;

public class LoginPageHandle {

    public DriverBase driverBase;

    public LoginPage loginPage;

    public LoginPageHandle(DriverBase driverBase){
        this.driverBase = driverBase;
        loginPage = new LoginPage(driverBase);
    }

    /**
     * 输入用户名
     * @param username
     */
    public void sendKeysUser(String username){
        loginPage.sendKeys(loginPage.getUserElement(),username);
    }

    /**
     * 输入密码
     */
    public void sendKeysPassword(String password){
        loginPage.sendKeys(loginPage.getPasswordElement(),password);
    }

    /**
     * 点击登录按钮
     */
    public void clickLoginButton(){
        loginPage.clickElement(loginPage.getLoginButtonElement());
    }



    /**
     * 判断是否在登录页面
     * @return
     */
    public boolean assertLoginPage(){
        return loginPage.assertElement(loginPage.getUserElement());
    }


}
