package com.duobei.appium.cases;

import com.duobei.appium.base.DriverBase;
import com.duobei.appium.business.HomePagePro;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SuiteTestBuy extends CaseBase {
    public DriverBase driverBase;
    public HomePagePro homePagePro;

    @BeforeClass
    public void BeforeClass() throws Exception {
        this.driverBase = InitDriver("firefox");

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
