package com.duobei.seleniums.utils;

import org.openqa.selenium.By;

public class getByLocator {

    public static By getLocator(String key) {
        PropertiesUtil proUtil = new PropertiesUtil("element.properties");
        String locator = proUtil.getProperties(key);
        String locatorType = locator.split(">")[0];
        String locatorValue = locator.split(">")[1];
        if (locatorType.equals("id")){
            return By.id(locatorValue);
        }else if (locatorType.equals("name")){
            return By.name(locatorValue);
        }else if (locatorType.equals("className")){
            return By.className(locatorValue);
        }else {
            return By.xpath(locatorValue);
        }

    }
}
