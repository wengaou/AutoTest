package com.duobei.selenium.business;

import com.duobei.selenium.base.DriverBase;
import com.duobei.selenium.handle.LoginPageHandle;

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
