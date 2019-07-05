package com.duobei.seleniums.utils;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.Iterator;

/**
 * 发现失败的用例在报告里生成了多份,改进办法：TestNGListener监听器类中重写onFinish 方法
 */
public class TestNGListener extends TestListenerAdapter {


    @Override
    public void onFinish(ITestContext testContext) {
        System.out.println("Test Finish");
        Iterator<ITestResult> listOfFailedTests = testContext.getFailedTests().getAllResults().iterator();
        while (listOfFailedTests.hasNext()) {
            ITestResult failedTest = listOfFailedTests.next();
            ITestNGMethod method = failedTest.getMethod();
            if (testContext.getFailedTests().getResults(method).size() > 1) {
                listOfFailedTests.remove();
            } else {
                if (testContext.getPassedTests().getResults(method).size() > 0) {
                    listOfFailedTests.remove();
                }
            }
        }

    }


//    public static DriverBase driver;
//    public void onTestFailure(ITestResult tr) {
//        super.onTestFailure(tr);
//        try {
//            takeScreenShot(tr);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 获取用例失败截图
//     * @param tr
//     * @throws IOException
//     */
//    public void takeScreenShot(ITestResult tr) throws IOException{
//        String fileName = tr.getName()+".png";
//        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(srcFile, new File("E:\\workspace\\AutoTest\\FailureScreenShot/"+fileName));
//    }
//
//
//
//    public void onFinish(ITestContext testContext) {
//        super.onFinish(testContext);
//        // List of test results which we will delete later
//        ArrayList<ITestResult> testsToBeRemoved = new ArrayList<ITestResult>();
//        // collect all id's from passed test
//        Set<Integer> passedTestIds = new HashSet<Integer>();
//        for (ITestResult passedTest : testContext.getPassedTests().getAllResults()){
//            //logger.info("PassedTests = " + passedTest.getName());
//            passedTestIds.add(getId(passedTest));
//        }
//
//
//        Set<Integer> failedTestIds = new HashSet<Integer>();
//        for (ITestResult failedTest : testContext.getFailedTests().getAllResults()){
//            //logger.info("failedTest = " + failedTest.getName());
//            int failedTestId = getId(failedTest);
//
//            // if we saw this test as a failed test before we mark as to be
//            // deleted
//            // or delete this failed test if there is at least one passed
//            // version
//            if (failedTestIds.contains(failedTestId) || passedTestIds.contains(failedTestId)) {
//                testsToBeRemoved.add(failedTest);
//            }else {
//                failedTestIds.add(failedTestId);
//            }
//        }
//
//        // finally delete all tests that are marked
//        for (Iterator<ITestResult> iterator = testContext.getFailedTests().getAllResults().iterator(); iterator.hasNext();) {
//            ITestResult testResult = iterator.next();
//            if (testsToBeRemoved.contains(testResult)) {
//                //logger.info("Remove repeat Fail Test: " + testResult.getName());
//                iterator.remove();
//            }
//        }
//    }
//
//
//    private int getId(ITestResult result) {
//        int id = result.getTestClass().getName().hashCode();
//        id = id + result.getMethod().getMethodName().hashCode();
//        id = id+ (result.getParameters() != null ? Arrays.hashCode(result.getParameters()) : 0);
//        return id;
//    }


    }
