package com.duobei.selenium.cases;


import com.duobei.selenium.base.DriverBase;
import com.duobei.selenium.business.LoginProcess;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class SuiteTestLogin extends CaseBase {

    public DriverBase driverBase;

    public LoginProcess loginProcess;

    @BeforeClass
    public void BeforeClass(){

        this.driverBase = InitDriver("chrome");
        loginProcess = new LoginProcess(driverBase);

    }


    @Test
    public void loginTest_001(){

        driverBase.getUrl("http://www.duobeiyun.com/login");
        loginProcess.login("13501331103","zhang@888");

    }


    @AfterSuite
    public void afterSuite(){
        driverBase.quit();

    }


}
