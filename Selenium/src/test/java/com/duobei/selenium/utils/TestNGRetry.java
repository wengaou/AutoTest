package com.duobei.selenium.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 *
 * 用例失败自动重跑逻辑
 */
public class TestNGRetry implements IRetryAnalyzer {

    private int retryCount = 1; // 定义重跑次数
    private int maxRetryCount=2; // 定义最大重跑次数


    public boolean retry(ITestResult result) {
        //System.out.println("1111111"+result);
        if (retryCount <= maxRetryCount) {
            String message = "[ "+result.getName()+" ] 用例运行失败，" + "正进行第：" + retryCount + " 次重试！Assertion";
            System.out.println(message);
            //DriverBase.log.error(message);
            retryCount++;
            return true;//代表重跑
        }
        return false;//关闭重跑
    }
}
