package com.duobei.selenium.cases;

import com.duobei.selenium.base.DriverBase;

public class CaseBase {

    public DriverBase InitDriver(String browser){
        return new DriverBase(browser);
    }

}
