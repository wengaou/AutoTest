package com.duobei.selenium.cases;

import com.duobei.selenium.base.DriverBase;
import com.duobei.selenium.business.HomePagePro;
import com.duobei.selenium.utils.HandleCookie;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SuiteTestBuy extends CaseBase {
    public DriverBase driverBase;
    public HomePagePro homePagePro;
    public HandleCookie handleCookie;

    @BeforeClass
    public void BeforeClass() throws InterruptedException {
        this.driverBase = InitDriver("firefox");
        this.homePagePro = new HomePagePro(driverBase);
        this.handleCookie = new HandleCookie(driverBase);
        driverBase.getUrl("http://tea1.gxzjy.com:8088/");
        handleCookie.setCookie();
        driverBase.getUrl("http://tea1.gxzjy.com:8088/");

        Thread.sleep(2000);

    }


    @Test
    public void test(){
        homePagePro.assertUserName("");
    }




    @AfterClass
    public void AfterClass(){
        //driverBase.stop();
    }

}
