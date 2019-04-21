package com.duobei.selenium.business;

import com.duobei.selenium.base.DriverBase;
import com.duobei.selenium.handle.HomePageHandle;

public class HomePagePro {
    HomePageHandle homePageHandle;
    public HomePagePro(DriverBase driverBase) {
         homePageHandle = new HomePageHandle(driverBase);
    }

    /**
     * 点击首页的登录按钮
     */
    public void clickLoginButton(){
        homePageHandle.clickLoginButton();
    }

    /**
     * 根据用户名，判断登录是否成功
     * @param expectUserName
     * @return
     */
    public Boolean assertUserName(String expectUserName){
        if (homePageHandle.getLoginUserName().contains(expectUserName)){
            return true;
        }
        return false;
    }

}
