package com.duobei.appium.cases;


import com.duobei.appium.base.DriverBase;

public class CaseBase {
    public DriverBase InitDriver(String browser) throws Exception {
        return new DriverBase(browser);
    }

}
