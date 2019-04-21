package com.duobei.appium.cases;


import com.duobei.appium.base.DriverBase;
import com.duobei.appium.business.LoginPro;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;


public class LoginCase extends CaseBase {
    public DriverBase driverBase;
    public LoginPro loginPro;
    //public TestNGListener testngListener;
    static Logger log = Logger.getLogger(LoginCase.class);

    @BeforeClass
    public void BeforeClass() throws Exception {
        log.info("启动浏览器。。。。");
        this.driverBase = InitDriver("firefox");
        loginPro = new LoginPro(driverBase);
        //testngListener = new TestNGListener();


    }



    @Test//(dependsOnMethods = {"getLoginHome"})
    public void testLogin(){

        //driverBase.getUrl("http://tea1.gxzjy.com:8088/?q=custom_user_login");
        loginPro.login("13501331103","zhang@888");
        //Assertion.equals(1,3);
        log.info("测试用例执行成功。。。。");

    }


    @AfterClass
    public void AfterClass(){

        log.info("关闭浏览器。。。。");
    }






}
