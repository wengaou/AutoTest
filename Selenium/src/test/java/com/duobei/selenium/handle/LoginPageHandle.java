package com.duobei.selenium.handle;

import com.duobei.selenium.base.DriverBase;
import com.duobei.selenium.pages.LoginPage;

public class LoginPageHandle {

    public DriverBase driverBase;

    public LoginPage loginPage;

    /**
     * 构造方法
     * @param driverBase
     */
    public LoginPageHandle(DriverBase driverBase){
        this.driverBase = driverBase;
        this.loginPage = new LoginPage(driverBase);
    }


    /**
     * 输入用户名
     * @param username
     */
    public void sendKeysUserName(String username){

        loginPage.sendKeys(loginPage.getUserInputBoxElement(),username);

    }

    /**
     * 输入密码
     * @param password
     */
    public void sendKeysPassword(String password){

        loginPage.sendKeys(loginPage.getPasswordInputBoxElement(),password);

    }


    /**
     * 点击登录按钮
     */
    public void clickLoginButton(){

        loginPage.clickElement(loginPage.getLoginButton());
    }






}
