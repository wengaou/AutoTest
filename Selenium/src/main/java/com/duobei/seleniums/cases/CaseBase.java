package com.duobei.seleniums.cases;

import com.duobei.seleniums.base.DriverBase;

public class CaseBase {
    public DriverBase InitDriver(String browser){
        return new DriverBase(browser);
    }

}
