package com.duobei.appium.cases;


import com.duobei.appium.base.DriverBase;
import com.duobei.appium.business.HomePagePro;
import com.duobei.appium.business.LoginPro;
import com.duobei.appium.utils.PropertiesUtil;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SuiteTestLogin extends CaseBase {
    public DriverBase driverBase;
    public LoginPro loginPro;
    public PropertiesUtil proUtil;
    public HomePagePro homePagePro;

    @BeforeClass
    public void BeforeClass() throws Exception {
        this.driverBase = InitDriver("firefox");
        proUtil = new PropertiesUtil("logintest.properties");
        loginPro = new LoginPro(driverBase);
        homePagePro = new HomePagePro(driverBase);
        //driverBase.getUrl(proUtil.getProperties("e_LoginPageUrl"));

    }


    @Test
    public void testLogin(){
        String username = proUtil.getProperties("e_username");
        String password = proUtil.getProperties("e_password");
        loginPro.login(username,password);
        if (homePagePro.assertUserName(proUtil.getProperties("expectedUserName"))){
            System.out.println("登录成功"+username);
        }

    }



    @AfterClass
    public void AfterClass(){
        //driverBase.closeBrowser();
    }


}
