package com.duobei.selenium.business;


import com.duobei.selenium.base.DriverBase;
import com.duobei.selenium.handle.LoginPageHandle;

public class LoginProcess {

    public LoginPageHandle loginPageHandle;


    /**
     * 构造方法
     * @param driverBase
     */
    public LoginProcess(DriverBase driverBase){
        this.loginPageHandle = new LoginPageHandle(driverBase);
    }

    /**
     * 登录流程
     * @param usernmae
     * @param password
     */
    public void login(String usernmae,String password){

        loginPageHandle.sendKeysUserName(usernmae);
        loginPageHandle.sendKeysPassword(password);
        loginPageHandle.clickLoginButton();

    }



}
