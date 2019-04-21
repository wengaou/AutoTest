package com.duobei.appium.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.NoSuchContextException;
import io.appium.java_client.TouchAction;
import io.appium.java_client.functions.ExpectedCondition;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class DriverBase {

    //声明AppiumDriver
    public AppiumDriver<WebElement> driver;

    //使用Log4j记录日志信息
    public static Logger log = Logger.getLogger(DriverBase.class);



    /**
     * 构造方法：
     *          创建对象时实例化driver
     * @param platformName
     */
    public DriverBase(String platformName) throws Exception{
        SelectDriver selectDriver = new SelectDriver();
        this.driver = selectDriver.driverName(platformName);
    }



    /**
     * 单击操作
     * @param element
     */
    public void clickElement(WebElement element){
        element.click();
    }

    /**
     * 清空输入框
     * @param element
     */
    public void clearInput(WebElement element){
        element.clear();
    }

    /**
     * 输入内容
     * @param element
     * @param expectText 预期文本
     */
    public void inputText(WebElement element,String expectText){
        element.sendKeys(expectText);
    }

    /**
     * 提交
     * @param element
     */
    public void submitElement(WebElement element){
        element.submit();
    }

    /**
     * 获取单个元素
     * @param by
     * @return
     */
    public WebElement findElement(By by){
        return driver.findElement(by);
    }

    /**
     * 判断元素是否是一组元素
     * @param by
     * @return
     */
    public boolean isElementList(By by){
        if (driver.findElements(by).size()>0){
            return true;
        }
        log.error("-->> ["+by.toString()+"] 不是一组数据，请重新验证！！！");
        return false;
    }

    /**
     * 获取一组元素
     * @param bys
     * @return
     */
    public List<WebElement> findElements(By bys){
        return driver.findElements(bys);
    }

    /**
     * 按角标获取获取elements中的某个元素
     * @param bys
     * @param index
     * @return
     */
    public WebElement getElementIndex(By bys,int index){
        WebElement element = null;
        if (isElementList(bys)==true){
            element = driver.findElements(bys).get(index);
        }
        return element;
    }

    /**
     * 获取一组元素的长度
     * @param bys
     * @return
     */
    public int getElementsLength(By bys) {
        int Length = 0;
        if (isElementList(bys) == true) {
            Length = findElements(bys).size();
        }
        return Length;
    }

    /**
     * 查找单个元素，Appium新增的查找元素方法
     * @param locateWay 查找方法
     * @param locateValue 查找元素
     * @return
     */
    public WebElement findElement(String locateWay,String locateValue){
        WebElement element = null;
        switch (locateWay){
            case "AccessibilityId":
                element = driver.findElementByAccessibilityId(locateValue);
                break;
            case "ClassName":
                element = driver.findElementByClassName(locateValue);
                break;
            case "ID":
                element = driver.findElementById(locateValue);
                break;
            case "Name":
                element = driver.findElementByName(locateValue);
                break;
            case "LinkText":
                element = driver.findElementByLinkText(locateValue);
                break;
            case "PartialLinkText":
                element = driver.findElementByPartialLinkText(locateValue);
                break;
            case "TagName":
                element = driver.findElementByTagName(locateValue);
                break;
            case "Xpath":
                element = driver.findElementByXPath(locateValue);
                break;
            default:
                log.error("-->>定位方式:"+locateWay+"不被支持！！！");
                Assert.fail("-->>定位方式:"+locateWay+"不被支持！！！");
        }
        return element;
    }

    /**
     * 查找一组元素，Appium新增的查找元素方法
     * @param locateWay 查找方法
     * @param locateValue 查找元素
     * @return
     */
    public List<?> findElements(String locateWay,String locateValue){
        List<?> element = null;
        switch (locateWay){
            case "AccessibilityId":
                element = driver.findElementsByAccessibilityId(locateValue);
                break;
            case "ClassName":
                element = driver.findElementsByClassName(locateValue);
                break;
            case "ID":
                element = driver.findElementsById(locateValue);
                break;
            case "Name":
                element = driver.findElementsByName(locateValue);
                break;
            case "LinkText":
                element = driver.findElementsByLinkText(locateValue);
                break;
            case "PartialLinkText":
                element = driver.findElementsByPartialLinkText(locateValue);
                break;
            case "TagName":
                element = driver.findElementsByTagName(locateValue);
                break;
            case "Xpath":
                element = driver.findElementsByXPath(locateValue);
                break;
            default:
                log.error("-->>定位方式:"+locateWay+"不被支持！！！");
                Assert.fail("-->>定位方式:"+locateWay+"不被支持！！！");
        }
        return element;
    }

    /**
     * 获取文本方法1
     * @param element
     * @return
     */
    public static String getElementText(WebElement element){
        return element.getText().trim();
    }

    /**
     * 获取文本方法2
     * @param locateWay 查找方法
     * @param locateValue 查找元素
     * @return
     */
    public String getElementText(String locateWay,String locateValue){
        String str = "";
        switch (locateWay) {
            case "AccessibilityId":
                str = driver.findElementByAccessibilityId(locateValue).getText();
                break;
            case "ClassName":
                str = driver.findElementByClassName(locateValue).getText();
                break;
            case "ID":
                str = driver.findElementById(locateValue).getText();
                break;
            case "Name":
                str = driver.findElementByName(locateValue).getText();
                break;
            case "LinkText":
                str = driver.findElementByLinkText(locateValue).getText();
                break;
            case "PartialLinkText":
                str = driver.findElementByPartialLinkText(locateValue).getText();
                break;
            case "TagName":
                str = driver.findElementByTagName(locateValue).getText();
                break;
            case "Xpath":
                str = driver.findElementByXPath(locateValue).getText();
                break;
            default:
                log.error("-->>定位方式:" + locateWay + "不被支持！！！");
                Assert.fail("-->>定位方式:" + locateWay + "不被支持！！！");
        }
        return str;
    }

    /**
     * 获取元素属性值方法1
     * @param element
     * @param attributeName 属性名称
     * @return
     */
    public static String getAttributeText(WebElement element,String attributeName){
        return element.getAttribute(attributeName);
    }

    /**
     * 获取元素属性值方法2
     * @param locateWay 查找方法
     * @param locateValue 查找元素
     * @param attributeName 属性名称
     * @return
     */
    public String getAttributeText(String locateWay,String locateValue,String attributeName){
        String str = "";
        switch (locateWay) {
            case "AccessibilityId":
                str = driver.findElementByAccessibilityId(locateValue).getAttribute(attributeName);
                break;
            case "ClassName":
                str = driver.findElementByClassName(locateValue).getAttribute(attributeName);
                break;
            case "ID":
                str = driver.findElementById(locateValue).getAttribute(attributeName);
                break;
            case "Name":
                str = driver.findElementByName(locateValue).getAttribute(attributeName);
                break;
            case "LinkText":
                str = driver.findElementByLinkText(locateValue).getAttribute(attributeName);
                break;
            case "PartialLinkText":
                str = driver.findElementByPartialLinkText(locateValue).getAttribute(attributeName);
                break;
            case "TagName":
                str = driver.findElementByTagName(locateValue).getAttribute(attributeName);
                break;
            case "Xpath":
                str = driver.findElementByXPath(locateValue).getAttribute(attributeName);
                break;
            default:
                log.error("-->>定位方式:" + locateWay + "不被支持！！！");
                Assert.fail("-->>定位方式:" + locateWay + "不被支持！！！");
        }
        return str;
    }








    /**
     * 显示等待封装：
     *      在给定的时间内查找元素是否存在，如果不存在则超时
     * @param by
     * @param elementTimeOut 超时时间
     * @return
     */
    public WebElement isElementExist(By by,int elementTimeOut){
        log.info("--->>开始查找元素：["+by.toString()+"]");
        WebElement element = null;
        WebDriverWait wait = new WebDriverWait(driver,elementTimeOut);
        try {
            element = wait.until(new ExpectedCondition<WebElement>() {
                @Override
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(by);
                }
            });
        } catch (Exception e) {
            log.error("--->>查找元素超时!!! " + elementTimeOut + " 秒之后仍未找到元素 ：[" + by.toString() + "]");
            Assert.fail("--->>查找元素超时!!! " + elementTimeOut + " 秒之后仍未找到元素 ：[" + by.toString() + "]");
        }
        log.info("--->>已找到元素：["+by.toString()+"]");
        return element;
    }


//    /**
//     * 显示等待封装：
//     *      在给定的时间内查找元素是否存在，如果不存在则超时
//     * @param by
//     * @param time 超时时间
//     * @return
//     */
//    public static AndroidElement isElementExist(By by,int time){
//        AndroidElement element = null;
//        AndroidDriverWait wait = new AndroidDriverWait(driver,time);
//        try {
//            element = wait.until(new ExpectedCondition<AndroidElement>() {
//                @Override
//                public AndroidElement apply(AndroidDriver androidDriver) {
//                    return (AndroidElement) driver.findElement(by);
//                }
//            });
//        } catch (Exception e) {
//            log.error("--->>查找元素超时!!! " + time + " 秒之后仍未找到元素 ：[" + by.toString() + "]");
//            Assert.fail("--->>查找元素超时!!! " + time + " 秒之后仍未找到元素 ：[" + by.toString() + "]");
//        }
//        return element;
//    }

    /**
     * 显示等待封装：
     *      在给定的时间内判断元素是否显示，如果不显示则超时
     * @param by
     * @param time 超时时间
     * @return
     */
    public boolean isElementDisplay(By by, int time){
        log.info("--->>开始查找元素：["+by.toString()+"]");
        boolean flag = true;
        WebDriverWait wait = new WebDriverWait(driver,time);
        try {
            flag = wait.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    return driver.findElement(by).isDisplayed();
                }
            });
        } catch (Exception e) {
            log.error("--->>判断元素显示超时!!! 等待" + time + " 秒之后  [" + by.toString() + "] 仍不显示！！！");
            Assert.fail("--->>判断元素显示超时!!! 等待" + time + " 秒之后  [" + by.toString() + "] 仍不显示！！！");
        }
        log.info("--->>元素显示正常：["+by.toString()+"]");
        return flag;
    }

    /**
     * 线程休眠
     * @param millis 毫秒
     */
    public void threadWait(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断toast提示信息是否出现
     * @param toastMsg toast文本信息
     * @param time  超时时间
     * @return
     * @throws Exception
     */
    public boolean isToastPresent(String toastMsg,int time)  {
        boolean flag = false;//定义boolean类型
        try {
            WebElement toast= isElementExist(By.xpath("//*[contains(@text,'"+ toastMsg + "')]") , time);
            if(toast!=null) {//如果toast不为空，打印toast的值，返回真
                String toastContent = toast.getText();
                log.info("--->>toast出现,全部内容:"+toastContent);
                flag=true;
            }
        }catch(Exception e) {
            log.error("--->>toast未出现");
            Assert.fail("--->>toast未出现");
        }
        return flag;//toast未出现返回假
    }

    /**
     * 判断当前页面是否存在预期字符串
     * @param exceptText 预期字符串
     * @return
     */
    public boolean isTextExist(String exceptText) {
        String str = driver.getPageSource();
        return str.contains(exceptText);
    }





    /*----------------------------------webview操作-----------------------------------------*/
    /**
     * 获取webview页面的标题
     * @return
     */
    public String getTitle(){
        return driver.getTitle();
    }

    /**
     * 跳转到webview页面
     * @param index
     */
    public void switchWebView(int index){
        Set<String> contexts = driver.getContextHandles();
        for (String context : contexts){
            // 打印出所有的context
            System.out.println(context);
        }
        driver.context((String) contexts.toArray()[index]);
    }

    /**
     * 跳转到webview页面
     */
    public void switchWebView(String contextName) {
        try {
            Set<String> contexts = driver.getContextHandles();
            for (String context : contexts) {
                // 打印出所有的context
                System.out.println(context);
            }
            driver.context(contextName);
        } catch (NoSuchContextException e) {
            Assert.fail("---->>没有这个context：" + contextName + ";" + e);
        }
    }

    /*----------------------------------处理屏幕操作-----------------------------------------*/
    /**
     * 以屏幕坐标滑动屏幕：
     * @param beginX 起始坐标x轴
     * @param beginY 起始坐标y轴
     * @param endX 结束坐标x轴
     * @param endY 结束坐标y轴
     */
    public void swipe(int beginX, int beginY, int endX, int endY) {
        TouchAction touchAction = new TouchAction(driver);
        try {
            touchAction.press(beginX, beginY).moveTo(endX, endY).perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从某个元素到某个元素之间滑动屏幕
     * @param beginElement
     * @param endElement
     */
    public void swipe(By beginElement, By endElement) {
        TouchAction touchAction = new TouchAction(driver);
        try {
            touchAction.press(findElement(beginElement)).moveTo(findElement(endElement)).perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 拖拽操作:
     *      把某个元素拖拽到某个元素后释放
     * @param dragElement
     * @param dropElement
     */
    public void DragAndDrop(By dragElement, By dropElement) {
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(findElement(dragElement)).perform();
        touchAction.moveTo(findElement(dropElement)).release().perform();
    }

    /**
     * 获取触摸实例
     * @return
     */
    public TouchAction getTouch(){
        TouchAction touchAction = new TouchAction(driver);
        if(driver==null){
            log.info("--->>单点触摸的时候driver为空！！！");
        }else{
            if(touchAction==null){
                return new TouchAction(driver);
            }else{
                return touchAction;
            }
        }
        return touchAction;
    }

    /**
     * 获取多点触摸实例
     * @return
     */
    public MultiTouchAction getMultiTouch(){
        MultiTouchAction multiTouchAction = new MultiTouchAction(driver);
        if(driver==null){
            log.info("--->>多点触摸的时候driver为空！！！");
        }else{
            if(multiTouchAction==null){
                return new MultiTouchAction(driver);
            }else{
                return multiTouchAction;
            }
        }
        return multiTouchAction;
    }

    /**
     * 点击某个控件
     * @param element 要点击的控件
     */
    public void pressElement(WebElement element){
        try {
            getTouch().tap(element).perform();
        } catch (Exception e) {
            log.error("--->>tap点击元素错误！！！"+e.getMessage());
            Assert.fail("--->>tap点击元素错误！！！"+e.getMessage());
        }
    }

    /**
     * 获取屏幕宽度
     * @return
     */
    public int getScreenWidth(){
        return driver.manage().window().getSize().width;
    }

    /**
     * 获取屏幕高度
     * @return
     */
    public int getScreenHeight(){
        return driver.manage().window().getSize().height;
    }


    /**
     * 点击某个坐标
     * @param x 坐标轴
     * @param y 坐标轴
     */
    public void pressCoordinate(int x, int y) {
        try {
            getTouch().tap(x, y).perform();
            log.info("--->>tab点击位置(" + x + "," + y + ")");
        } catch (Exception e) {
            log.error("--->>tab点击元素位置异常！！！" + e.getMessage());
            Assert.fail("--->>tab点击元素位置异常！！！" + e.getMessage());
        }
    }

    /**
     * 点击屏幕中间
     */
    public void press() {
        pressCoordinate(getScreenWidth() / 2, getScreenHeight() / 2);
    }

    /**
     * 长按某个元素n秒后，释放该操作
     * @param element 要长按的元素
     * @param controlTime 按压时间
     */
    public void longPress(WebElement element,int controlTime) {
        try {
            getTouch().longPress(element,Duration.ofSeconds(controlTime)).release().perform();
        } catch (Exception e) {
            log.error("--->>长按控件出现异常！！！" + e.getMessage());
            Assert.fail("--->>长按控件出现异常！！！" + e.getMessage());
        }
    }

    /**
     * 长按某个坐标
     * @param x 坐标轴
     * @param y 坐标轴
     */
    public void longPress(int x, int y) {
        try {
            getTouch().longPress(x, y).release().perform();
        } catch (Exception e) {
            log.error("--->>长按控件出现异常！！！" + e.getMessage());
            Assert.fail("--->>长按控件出现异常！！！" + e.getMessage());
        }
    }

    /**
     * 上滑屏幕
     * @param continueTime 滑动持续的秒数
     */
    public void swipeToUp(int continueTime){
        int width = getScreenWidth();// 获取当前屏幕宽度
        int height = getScreenHeight();// 获取当前屏幕高度
        TouchAction touchAction=new TouchAction(driver);
        touchAction.press(width / 2, height * 3 / 4).waitAction(Duration.ofSeconds(continueTime)).moveTo(width / 2, height / 4).release();
        touchAction.perform();
        System.out.println();
        log.info("--->>屏幕向上滑动");
        System.out.println();
    }

    /**
     * 下滑屏幕
     * @param continueTime 滑动持续的秒数
     */
    public void swipeToDown(int continueTime){
        int width = getScreenWidth();// 获取当前屏幕宽度
        int height = getScreenHeight();// 获取当前屏幕高度
        TouchAction touchAction=new TouchAction(driver);
        touchAction.press(width / 2, height / 4).waitAction(Duration.ofSeconds(continueTime)).moveTo(width / 2, height * 3 / 4).release();
        touchAction.perform();
        System.out.println();
        log.info("--->>屏幕向下滑动");
        System.out.println();
    }

    /**
     * 左滑屏幕
     * @param continueTime 滑动持续的秒数
     */
    public void swipeToLeft(int continueTime){
        int width = getScreenWidth();// 获取当前屏幕宽度
        int height = getScreenHeight();// 获取当前屏幕高度
        TouchAction touchAction=new TouchAction(driver);
        touchAction.press(width * 9 / 10, height / 2).waitAction(Duration.ofSeconds(continueTime)).moveTo(width / 10, height / 2).release();
        touchAction.perform();
        System.out.println();
        log.info("--->>屏幕向左滑动");
        System.out.println();
    }

    /**
     * 右滑屏幕
     * @param continueTime 滑动持续的秒数
     */
    public void swipeToRight(int continueTime){
        int width = getScreenWidth();// 获取当前屏幕宽度
        int height = getScreenHeight();// 获取当前屏幕高度
        TouchAction touchAction=new TouchAction(driver);
        touchAction.press(width / 10, height / 2).waitAction(Duration.ofSeconds(continueTime)).moveTo(width * 9 / 10, height / 2).release();
        touchAction.perform();
        System.out.println();
        log.info("--->>屏幕向右滑动");
        System.out.println();
    }

    /**
     * 选择滑动方向
     * @param direction 滑动方向：ToUp ToDown ToLeft ToRight
     * @param continueTime  持续时间
     */
    public void SelectSwipe(String direction,int continueTime) {
        switch (direction) {
            case "ToUp":
                swipeToUp(continueTime);
                break;
            case "ToDown":
                swipeToDown(continueTime);
                break;
            case "ToLeft":
                swipeToLeft(continueTime);
                break;
            case "ToRight":
                swipeToRight(continueTime);
                break;
        }
    }


    /**
     * 在指定次数的条件下，某个方向滑动，直到这个元素出现
     * @param by         控件
     * @param direction 选择滑动方向，ToUp ToDown ToLeft ToRight
     * @param maxSwipNum 最大滑动次数
     * @param continueTime  持续时间
     */
    public void swipUtilElementAppear(By by, String direction, int maxSwipNum, int continueTime) {
        int i = maxSwipNum;
        Boolean flag = true;
        while (flag) {
            try {
                if (i <= 0) {
                    flag = false;
                }
                driver.findElement(by);
                flag = false;
            } catch (Exception e) {
                i--;
                SelectSwipe(direction,continueTime);
            }
        }
    }

    /**
     * 在某个方向滑动直到这个元素出现
     * @param by        控件
     * @param direction 选择滑动方向，ToUp ToDown ToLeft ToRight
     * @param time  持续时间
     */
    public void swipUtilElementAppear(By by, String direction,int time) {
        Boolean flag = true;
        while (flag) {
            try {
                driver.findElement(by);
                flag = false;
            } catch (Exception e) {
                SelectSwipe(direction,time);
            }
        }
    }

    /**
     * 放大和缩小：
     *
     * @param beginX 起始坐标x轴
     * @param beginY 起始坐标y轴
     * @param endX 结束坐标x轴
     * @param endY 结束坐标y轴
     * @param time 持续时间/秒
     */
    public void zoomAndPinch(int beginX, int beginY, int endX, int endY, int time) {
        int scrHeight = driver.manage().window().getSize().getHeight();// 获取屏幕高度
        int scrWidth = driver.manage().window().getSize().getWidth();// 获取屏幕宽度
        MultiTouchAction multiTouch = new MultiTouchAction(driver);
        TouchAction touchAction1 = new TouchAction(driver);
        TouchAction touchAction2 = new TouchAction(driver);
        touchAction1.press(scrWidth / 2, scrHeight / 2).waitAction(Duration.ofSeconds(time)).moveTo(beginX, beginY).release();
        touchAction2.press(scrWidth / 2, scrHeight / 2 + 40).waitAction(Duration.ofSeconds(time)).moveTo(endX, endY).release();
        multiTouch.add(touchAction1).add(touchAction2);
        multiTouch.perform();
    }


    /*----------------------------------处理App-----------------------------------------*/
    /**
     * 安装App
     * @param appPath App路径
     */
    public void installApp(String appPath){
        try {
            driver.installApp(appPath);
        } catch (Exception e) {
            log.error("--->>App安装失败", e);
            Assert.fail("--->>App安装失败", e);
        }
    }

    /**
     * App置于后台运行
     * @param runTimes 运行时间
     */
    public void runBackground(int runTimes) {
        driver.runAppInBackground(Duration.ofSeconds(runTimes));
        log.info("---->>App已置于后台运行");
    }

    /**
     * 判断App是否安装
     * @param appPackage App包名
     */
    public boolean isInstallApp(String appPackage) {
        if (driver.isAppInstalled(appPackage)){
            log.info("---->>已安装："+appPackage);
            return true;
        }else {
            log.info("---->>未安装："+appPackage);
            return false;
        }
    }

    /**
     * 关闭App
     * @param appName App名称
     */
    public void closeApp(String appName){
        driver.closeApp();
        log.info(appName + "---->>已关闭");
    }

    /**
     * 退出移动浏览器
     */
    public void quitApp(){
        driver.quit();
        log.info("---->>退出App");
    }


    /*----------------------------------处理键盘操作-----------------------------------------*/
    /**
     * 收起键盘
     */
    public void hideKeyboard() {
        driver.hideKeyboard();
        log.info("---->>键盘已收起");
    }



    /*----------------------------------截图操作-----------------------------------------*/

    /**
     * 获取整个页面的截图
     * @param screenShotname 截图保存名称
     * @throws IOException
     */
    public void screenShot(String screenShotname) {
        //获取整个页面的截图
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //将截图保存到指定的文件夹下
        String savePath="E://screenshot//"+screenShotname+".png";
        try {
            FileUtils.copyFile(screenshot, new File(savePath));
        } catch (IOException e) {
            log.error("---->>截图存放路径错误！！！", e);
            Assert.fail("---->>截图存放路径错误！！！", e);
        }
    }

    /***
     * 获取系统时间
     * @return
     */
    public String getDatetime() {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return date.format(new Date());
    }




}
