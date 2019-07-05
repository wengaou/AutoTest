package com.duobei.seleniums.cases;

import com.duobei.seleniums.base.DriverBase;
import com.duobei.seleniums.business.LoginPro;
import com.duobei.seleniums.utils.Assertion;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class LoginCase extends CaseBase {
    public DriverBase driverBase;
    public LoginPro loginPro;
    //public TestNGListener testngListener;
    static Logger log = Logger.getLogger(LoginCase.class);

    @BeforeClass
    public void BeforeClass(){
        log.info("启动浏览器。。。。");
        this.driverBase = InitDriver("firefox");
        loginPro = new LoginPro(driverBase);
        //testngListener = new TestNGListener();


    }



    @Test//(dependsOnMethods = {"getLoginHome"})
    public void testLogin(){

        driverBase.getUrl("http://tea1.gxzjy.com:8088/?q=custom_user_login");
        loginPro.login("13501331103","zhang@888");
        Assertion.equals(1,3);
        log.info("测试用例执行成功。。。。");

    }


    @AfterClass
    public void AfterClass(){

        driverBase.closeBrowser();
        log.info("关闭浏览器。。。。");
    }






}
