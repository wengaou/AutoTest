package com.duobei.seleniums.handle;

import com.duobei.seleniums.base.DriverBase;
import com.duobei.seleniums.page.HomePage;

public class HomePageHandle {
    public DriverBase driverBase;
    public HomePage homePage;
    public HomePageHandle(DriverBase driverBase){
        this.driverBase = driverBase;
        homePage = new HomePage(driverBase);
    }


    /**
     * 点击首页的登录按钮
     */
    public void clickLoginButton(){
        homePage.getLoginButtonElement();
    }

    /**
     * 获取登录状态提示信息
     * @return
     */
    public String getLoginInfoText(){
        return homePage.getLoginInfoElement().getText();
    }

    /**
     * 获取登录成功的用户名
     * @return
     */
    public String getLoginUserName(){
        return homePage.getUserNameElement().getText();
    }


}
