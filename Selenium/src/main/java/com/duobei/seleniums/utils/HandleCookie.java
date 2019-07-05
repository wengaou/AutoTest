package com.duobei.seleniums.utils;

import com.duobei.seleniums.base.DriverBase;
import org.openqa.selenium.Cookie;

import java.util.Set;

public class HandleCookie {
    public DriverBase driverBase;
    public PropertiesUtil proUtil;
    public HandleCookie(DriverBase driverBase){
        this.driverBase = driverBase;
        this.proUtil = new PropertiesUtil("cookie.properties");
    }

    /**
     * 设置cookie
     */
    public void setCookie(){
        String sid = proUtil.getProperties("SESSc44de621d3f720a8020693d6bf99b37b");
        Cookie cookie = new Cookie("SESSc44de621d3f720a8020693d6bf99b37b",sid,"tea1.gxzjy.com","/",null);
        driverBase.setCookie(cookie);

    }

    /**
     * 获取cookie
     */
    public void writeCookie(){
        Set<Cookie> cookies = driverBase.getCookie();
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("SESSc44de621d3f720a8020693d6bf99b37b")){
                //proUtil.getProperties(cookie.getName(),cookie.getValue());
            }
        }
    }




}
