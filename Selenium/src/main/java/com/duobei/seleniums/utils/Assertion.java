package com.duobei.seleniums.utils;

import org.testng.Assert;

public class Assertion {

    /**
     * 断言期望值与实际值是否相等
     * @param actual 实际值
     * @param expected 预期值
     */
    public static void equals(Object actual,Object expected){
        try {
            Assert.assertEquals(actual,expected);
        } catch (AssertionError e) {
            Assert.fail("-->>断言失败：预期值是[ "+expected+" ],但是实际值是[ "+actual+" ]" );
        }
    }

    /**
     * 判断实际值中是否包含预期值
     * @param actualText 实际值
     * @param expectText 预期值
     */
    public static void Contains(String actualText,String expectText){
        try {
            Assert.assertTrue(actualText.contains(expectText));
        } catch (AssertionError e) {
            Assert.fail("-->>断言失败：实际值[ "+actualText+" ]中,未包含预期值[ "+expectText+" ]");
        }
    }





}
