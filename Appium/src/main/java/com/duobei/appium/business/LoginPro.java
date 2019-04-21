package com.duobei.appium.business;


import com.duobei.appium.base.DriverBase;
import com.duobei.appium.handle.LoginPageHandle;

public class LoginPro {
    public LoginPageHandle loginPageHandle;
    public LoginPro(DriverBase driverBase){
        this.loginPageHandle = new LoginPageHandle(driverBase);
    }

    public void login(String username,String password){
        if (loginPageHandle.assertLoginPage()) {
            loginPageHandle.sendKeysUser(username);
            loginPageHandle.sendKeysPassword(password);
            loginPageHandle.clickLoginButton();
        }else {
            System.out.println("页面不存在，或者状态不正确！！！");
        }

    }

}
