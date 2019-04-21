package com.duobei.appium;

import com.duobei.appium.base.SelectDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;

public class Demo {

    AndroidDriver<AndroidElement> driver;


    @BeforeClass
    public void beforeClass() throws Exception{

//        SelectDriver selectDriver = new SelectDriver();
//        selectDriver.driverName("android");

        //设置启动参数
        DesiredCapabilities cap = new DesiredCapabilities();
        //设置手机类型或模拟器类型
        //cap.setCapability("deviceName","X2P5T15C12005910");
        cap.setCapability("deviceName","8BN0217705008522");
        //设置设备系统版本
        cap.setCapability("platformVersion","8.0");
        //设置测试平台
        cap.setCapability("platformName","android");
        //设置app包名
        cap.setCapability("appPackage","cc.wzk");
        //设置启动app的Activity名称
        cap.setCapability("appActivity","cc.wzk.SplashAct");
        //设置每次启动时覆盖session，否则第二次运行会报错不能新建session
        cap.setCapability("sessionOverride",true);
        //设置键盘支持中文输入
        cap.setCapability("unicodeKeyboard","True");
        cap.setCapability("resetKeyboard","True");

        this.driver = new AndroidDriver<AndroidElement>(new URL("http://localhost:4723/wd/hub"),cap);





    }


    @AfterClass
    public void afterClass(){
        driver.quit();

    }


    @Test
    public void test(){
        System.out.println("启动成功");
    }

}
