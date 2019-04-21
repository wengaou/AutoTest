package com.duobei.appium.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestContext;

import java.net.URL;

public class SelectDriver {

    //声明DesiredCapabilities
    public static DesiredCapabilities capabilities;
    //声明ITestContext，用于获取testng配置文件内容
    public static ITestContext testContext;
    //appium server地址
    public static String serverURL;
    //测试引擎名称
    public static String automationName;
    //测试平台名称
    public static String platformName;
    //测试平台版本号
    public static String platformVersion;
    //设备名称
    public static String deviceName;
    //ios App的路径
    public static String iosAppPath;
    //android App路径
    public static String androidAppPath;
    //android App的 package
    public static String appPackage;
    //android App的activity
    public static String appActivity;
    //安卓独有 - 是否使用unicode键盘，使用此键盘可以输入中文字符
    public static boolean unicodeKeyboard;
    //android独有 - 是否重置键盘，如果设置了unicodeKeyboard键盘，可以将此参数设置为true，然后键盘会重置为系统默认的
    public static boolean resetKeyboard;
    //是否覆盖已有的session，这个用于多用例执行，如果不设置的话，会提示前一个session还没有结束，用例就不能继续执行了
    public static boolean sessionOverride;
    //暂停的等待时间
    public int sleepTime;
    //元素等待超时时间
    public int elementTimeOut;
    //App文件路径，主要存储的是App的名称
    public String appFilePath;
    //webview的名字或者叫标识符,一般以WEBVIEW开头，例如WEBVIEW_com.microsoft.bing
    public final static String WEBVIEW_NAME = null;
    //原生app的名字或者标识符，一般是NATIVE_APP
    public final static String NATIVEAPP_NAME = null;
    //实例化本类的日志输出对象
    //public static Logger log = Logger.getLogger(SelectDriver.class);

    public AppiumDriver<WebElement> driverName(String selectDevice) throws Exception{
        AppiumDriver<WebElement> driver = null;
        //将服务器的URL的参数值赋值给serverURL变量
        serverURL = "http://localhost:4723/wd/hub";
        //将平台名称的参数值赋值给platformName变量
        platformName = "android";
        //将平台版本号的参数值赋值给platformVersion变量
        platformVersion = "8.0";
        //将设备名称的参数值赋值给deviceName变量
        //deviceName = "X2P5T15C12005910";
        deviceName = "8BN0217705008522";
        //将App的包名称的参数值赋值给appPackage变量
        appPackage = "cc.wzk";
        //将App的Activity名称的参数值赋值给appActivity变量
        appActivity = "cc.wzk.SplashAct";
        //将sessionOverride的参数值赋值给sessionOverride变量
        sessionOverride = true;
        //将unicodeKeyboard的参数值赋值给unicodeKeyboard变量
        unicodeKeyboard = true;
        //将resetKeyboard的参数值赋值给resetKeyboard变量
        resetKeyboard = true;

        capabilities = new DesiredCapabilities();
        //告诉测试程序，当前项目目录在哪里
        //File classpathRoot = new File(System.getProperty("user.dir"));
        //设置capability，以便和appium创建session
        capabilities.setCapability("platformName",platformName);
        capabilities.setCapability("platformVersion",platformVersion);
        capabilities.setCapability("deviceName",deviceName);
        capabilities.setCapability("sessionOverride",sessionOverride);

        //如果测试平台是android的话，执行下面这个if语句内容
        if (platformName.equalsIgnoreCase(selectDevice)){
            //设置和android  测试相关的capability并实例化driver对象
            //File app = new File(classpathRoot,androidAppPath); Android-App路径
            capabilities.setCapability("appPackage",appPackage);
            capabilities.setCapability("appActivity",appActivity);
            capabilities.setCapability("unicodeKeyboard",unicodeKeyboard);
            capabilities.setCapability("resetKeyboard",resetKeyboard);

            driver = new AndroidDriver<WebElement>(new URL(serverURL),capabilities);
            System.out.println("---->>已启动："+deviceName);
            //log.info("---->>已启动："+deviceName);
            return driver;

            //如果测试平台是ios的话，执行下面这个if语句内容
        }else if (platformName.equalsIgnoreCase(selectDevice)){
            //设置和ios测试相关的capability并实例化driver对象
            //File app = new File(classpathRoot,iosAppPath); ios-App路径
            //capabilities.setCapability("app", app.getAbsolutePath());
            //ios设置自动接收系统alert，注意IOS弹出的alert，APPIUM可以自动处理掉，支持ios8以上系
            //capabilities.setCapability("autoAcceptAlerts", true);
            //driver = AppiumBase.getDriver(serverURL, capabilities,platformName);
            //log.info("---->>已启动："+deviceName);
        }else {
            //log.error("---->>初始化driver失败！！！");
            Assert.fail("---->>初始化driver失败！！！");
        }
        return driver;
    }






//    public static void main(String[] args) throws MalformedURLException {
//
//        //设置启动参数
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        //手机类型或模拟器类型
//        capabilities.setCapability("deviceName","X2P5T15C12005910");
//        //设备系统版本
//        capabilities.setCapability("platformVersion", "6.0");
//        //指定平台，为安卓
//        capabilities.setCapability("platformName","android");
//        //获取绝对路径
//        //capabilities.setCapability("app", app.getAbsolutePath());
//        //app包名
//        capabilities.setCapability("appPackage", "cc.wzk");
//        //app中启动的 Activity名称
//        capabilities.setCapability("appActivity", "cc.wzk.SplashAct");
//        //每次启动时覆盖session，否则第二次后运行会报错不能新建session
//        capabilities.setCapability("sessionOverride", true);
//        //指定键盘支持中文输入
//        capabilities.setCapability("unicodeKeyboard", "True");
//        capabilities.setCapability("resetKeyboard", "True");
//
//        //解决每次启动APP需要确认权限问题--即不重复安装
//        //capabilities.setCapability("noReset", true);
//        //capabilities.setCapability("noSign", true);
//
//        //capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);		  //设置代码服务器
//        driver = new AndroidDriver<AndroidElement>(new URL("http://localhost:4723/wd/hub"), capabilities);
//
//    }



}
