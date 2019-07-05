package com.duobei.seleniums.cases;

import com.duobei.seleniums.base.DriverBase;
import com.duobei.seleniums.business.HomePagePro;
import com.duobei.seleniums.business.LoginPro;
import com.duobei.seleniums.utils.PropertiesUtil;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SuiteTestLogin extends CaseBase {
    public DriverBase driverBase;
    public LoginPro loginPro;
    public PropertiesUtil proUtil;
    public HomePagePro homePagePro;

    @BeforeClass
    public void BeforeClass() {
        this.driverBase = InitDriver("firefox");
        proUtil = new PropertiesUtil("logintest.properties");
        loginPro = new LoginPro(driverBase);
        homePagePro = new HomePagePro(driverBase);
        driverBase.getUrl(proUtil.getProperties("e_LoginPageUrl"));

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
        driverBase.closeBrowser();
    }


}
