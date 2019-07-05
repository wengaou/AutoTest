package com.duobei.seleniums.business;

import com.duobei.seleniums.base.DriverBase;
import com.duobei.seleniums.handle.HomePageHandle;

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
