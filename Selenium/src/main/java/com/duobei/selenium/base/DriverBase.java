package com.duobei.selenium.base;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


/**
 * 封装所有selenium提供的操作方法，并生成driver对象
 */
public class DriverBase {

    public WebDriver driver;
    public static Logger log = Logger.getLogger(DriverBase.class);
    private int TimeOut = 30;

    /**
     * 构造方法：
     *          创建对象时实例化driver
     * @param browser
     */
    public DriverBase(String browser){
        SelectDriver selectDriver = new SelectDriver();
        this.driver = selectDriver.driverName(browser);
    }

    /**
     * 获取driver
     * @return
     */
    public WebDriver getDriver(){
        return driver;
    }

    /**
     * 获取Url
     * @param urlText
     * @param url
     */
    public void getUrl(String urlText, String url) {
        driver.get(url);
        log.info("--->>" +urlText+ " " +url);
    }



    /*--------------------------------失败截图的方法封装------------------------------------*/
    public void takeScreenShot(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String dateStr = simpleDateFormat.format(date);
        String path = this.getClass().getSimpleName() + "_" + dateStr + ".png";
        takeScreenShot((TakesScreenshot) this.getDriver(),path);

    }


    public void takeScreenShot(TakesScreenshot drivername,String path){
        String currentPath = System.getProperty("user.dir");
        File scrFile = drivername.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile,new File(currentPath + "\\" +path));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("截图成功！");
        }
    }












    /*--------------------------------操作元素的方法封装------------------------------------*/
    /**
     * 查找单个元素
     * @param by
     * @return
     */
    public WebElement findElement(By by){
        return driver.findElement(by);
    }

    /**
     * 获取一组元素
     * @param bys
     * @return
     */
    public List<WebElement> findElements(By bys){
        isElementExist(bys);
        if (driver.findElements(bys).size()>0){
            return driver.findElements(bys);
        }else {
            log.error("-->> ["+bys.toString()+"] 不是一组数据，请重新验证！！！");
            Assert.fail("-->> ["+bys.toString()+"] 不是一组数据，请重新验证！！！");
        }
        return null;
    }

    /**
     * 按角标获取获取elements中的某个元素
     * @param bys
     * @param index
     * @return
     */
    public WebElement getElementIndex(By bys,int index){
        return driver.findElements(bys).get(index);
    }

    /**
     * 按角标获取获取elements中的某个元素后继续定位
     * @param bys
     * @param by
     * @param index
     * @return
     */
    public WebElement getOneElement(By bys,By by,int index){
        return driver.findElements(bys).get(index).findElement(by);
    }


    /**
     * 获取元素的文本并去除空格
     * @param element
     * @return
     */
    public String getElementText(WebElement element){
        return element.getText().trim();
    }

    /**
     * 获取元素的属性值并去除空格
     * @param element
     * @param elementAttribute
     * @return
     */
    public String getElementAttribute(WebElement element,String elementAttribute){
        return element.getAttribute(elementAttribute).trim();
    }

    /**
     * 根据元素来获取此元素的定位值
     * @param element
     * @param expectText
     * @return
     */
    public String getLocatorByElement(WebElement element,String expectText){
        String text = element.toString();
        String expect = null;
        try {
            text.substring(text.indexOf(expectText)+1,text.length()-1);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("-->>未能找到预期的字符串["+expectText+"]");
        }
        return expect;
    }

    /**
     * 判断元素是否可用
     * @param element
     * @return
     */
    public boolean isEnabled(WebElement element){
        boolean flag = false;
        if (element.isEnabled()){
            flag = true;
        }else {
            log.error("-->>元素: ["+element+"] 不可使用！！！");
            flag = false;
        }
        return flag;
    }

    /**
     * 判断checkbox是否被勾选
     * @param element
     * @return
     */
    public boolean isSelected(WebElement element){
        boolean flag = true;
        if (element.isSelected()==true){
            flag = true;
        }else{
            flag = false;
            log.error("-->>元素: ["+element+"] 未被勾选！！！");
        }
        return flag;
    }

    /**
     * 获取select选择的值
     * @param element
     * @return
     */
    public List<WebElement> getSelectValue(WebElement element){
        Select select = new Select(element);
        return select.getAllSelectedOptions();
    }



    /*--------------------------------操作鼠标的方法封装------------------------------------*/
    /**
     * 模拟鼠标操作：
     *      左击操作
     * @param element
     */
    public void clickElement(WebElement element){

        element.click();
    }

    /**
     * 模拟鼠标操作：
     *      右击操作
     * @param element
     */
    public void rightClick(WebElement element){
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
    }

    /**
     * 模拟鼠标操作：
     *      双击操作
     * @param element
     */
    public void doubleClickElement(WebElement element){
        Actions actions = new Actions(driver);
        actions.doubleClick(element);
    }

    /**
     * 模拟鼠标操作：
     *      鼠标移动到指定的元素上
     * @param element
     */
    public void mouseMoveToElement(WebElement element){
        Actions actions = new Actions(driver);
        Actions mouse = actions.moveToElement(element);
        mouse.perform();
    }

    /**
     * 在指定的元素上单机左键，等待n秒后释放
     * @param element
     * @param time
     */
    public void clickAndRelease(WebElement element,int time){
        Actions actions = new Actions(driver);
        actions.clickAndHold(element).perform();
        wait(time);
        actions.release(element).perform();
    }


    /*--------------------------------操作键盘的方法封装------------------------------------*/
    /**
     * 模拟键盘操作
     * @param element
     * @param key 键盘上的功能键，如：Ctrl,Alt等
     * @param keyword 键盘上的字母
     */
    public static void pressKeysOnkeyboard(WebElement element,Keys key,String keyword){
        element.sendKeys(Keys.chord(key,keyword));
    }

    /**
     * 按住shift键，输入大写字母后释放shift键
     * @param key
     */
    public void keyDown(String key){
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.SHIFT).sendKeys(key).perform();
    }



    /*--------------------------------操作输入框的方法封装------------------------------------*/
    /**
     * 清空输入框
     * @param element
     */
    public void clearInput(WebElement element){
        element.clear();
    }

    /**
     * 输入预期文本或文件路径
     * @param element
     * @param expectText
     */
    public void inputText(WebElement element,String expectText){
        element.sendKeys(expectText);
    }



    /*--------------------------------操作frame的方法封装------------------------------------*/
    /**
     * 根据frame名称切换frame
     * @param frameID 根据String类型(frameName)
     */
    public void frameID(String  frameID){
        driver.switchTo().frame(frameID);
    }

    /**
     * 根据frame在当前页面中的顺序来定位
     * @param frameNum
     */
    public void frameNum(String frameNum){
        driver.switchTo().frame(frameNum);
    }

    /**
     * 跳出frame
     */
    public void outFrame(){
        driver.switchTo().defaultContent();
    }



    /*--------------------------------操作下拉框的方法封装------------------------------------*/
    /**
     * 处理下拉框：
     *      根据index角标选择下拉选项
     * @param element
     * @param index
     */
    public static void selectByValue(WebElement element,int index){
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    /**
     * 处理下拉框：
     *      根据文本内容选择下拉框选项
     * @param element
     * @param text
     */
    public void selectByText(WebElement element,String text){
        Select select = new Select(element);
        select.deselectByVisibleText(text);
    }


    /*------------------------------上传文件的方法封装------------------------------------*/
    /**
     * 上传文件：
     *      需要点击弹出上传照片的窗口才可以
     * @param browserName 使用的浏览器名称
     * @param file  需要上传的文件及文件名
     */
    public void handleUpload(String browserName, File file){
        String filePath = file.getAbsolutePath();
        String executeFile = "res/script/autoit/Upload.exe";
        String cmd = "\"" + executeFile + "\"" + " " + "\"" + browserName + "\"" + " " + "\"" + filePath + "\"";
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }














    /*--------------------------------显示等待的方法封装------------------------------------*/
    /**
     * 显示等待封装：
     *      在给定的时间内查找元素是否存在，如果不存在则超时
     * @param by
     * @return
     */
    public WebElement isElementExist(By by) {
        WebElement element = null;
        WebDriverWait webDriverWait = new WebDriverWait(driver, TimeOut);
        try {
            element = webDriverWait.until(new ExpectedCondition<WebElement>() {
                @Override
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(by);
                }
            });
        } catch (TimeoutException e) {
            log.error("--->>查找元素超时!!! " + TimeOut + " 秒之后仍未找到元素 ：[" + by.toString() + "]");
            Assert.fail("--->>查找元素超时!!! " + TimeOut + " 秒之后仍未找到元素 ：[" + by.toString() + "]");
        }
        return element;
    }


    /**
     * 显示等待封装：
     *      在给定的时间内判断元素是否显示，如果不显示则超时
     * @param by
     * @return
     */
    public boolean isElementDisplay(By by){
        boolean flag = true;
        WebDriverWait webDriverWait = new WebDriverWait(driver,TimeOut);
        try {
            flag = webDriverWait.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    return driver.findElement(by).isDisplayed();
                }
            });
        } catch (TimeoutException e) {
            log.error("--->>判断元素显示超时!!! 等待" + TimeOut + " 秒之后  [" + by.toString() + "] 仍不显示！！！");
            Assert.fail("--->>判断元素显示超时!!! 等待" + TimeOut + " 秒之后  [" + by.toString() + "] 仍不显示！！！");
        }
        return flag;
    }


    /**
     * 显示等待封装：
     *      在给定的时间内查找预期的文本，如果未找到则超时
     * @param by
     * @param ExpectText
     * @param time
     * @return
     */
    public boolean isPresentExpectText(final By by, final String ExpectText, int time){
        boolean flag = true;
        WebDriverWait webDriverWait = new WebDriverWait(driver,time);
        try {
            flag = webDriverWait.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver){
                    return driver.findElement(by).getText().contains(ExpectText);
                }
            });
        } catch (TimeoutException e) {
            log.error("--->>查找元素的文本值超时!!! 等待  " + time + " 秒之后预期的文本：  [" + ExpectText+ "] 仍未找到！！！");
            Assert.fail("--->>查找元素的文本值超时!!! 等待  " + time + " 秒之后预期的文本：  [" + ExpectText+ "] 仍未找到！！！");
        }
        return flag;
    }

    /**
     * 线程休眠
     * @param millis
     */
    public void wait(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /*--------------------------------操作浏览器的方法封装------------------------------------*/
    /**
     * 最大化浏览器
     * @param browserName 浏览器名称
     */
    public void maxWindow(String browserName){
        log.info("-->>最大化浏览器:" + browserName);
        driver.manage().window().maximize();
    }

    /**
     * 自定义浏览器大小
     * @param width 浏览器宽度
     * @param height 浏览器高度
     */
    public void setBrowserSize(int width,int height){
        driver.manage().window().setSize(new Dimension(width,height));
    }

    /**
     * 关闭浏览器窗口
     */
    public void closeBrowser(){
        driver.close();;
        log.info("-->>关闭浏览器窗口!");
    }

    /**
     * 退出浏览器
     */
    public void quitBrowser() {
        driver.quit();
        log.info("-->>退出浏览器!");
    }

    /**
     * 刷新浏览器
     */
    public void refreshBrowser(){
        driver.navigate().refresh();
    }

    /**
     * 前进浏览器
     */
    public void forwardBrowser(){
        driver.navigate().forward();
    }

    /**
     * 后退浏览器
     */
    public void backBrowser(){
        driver.navigate().back();
    }

    /**
     * 获取当前页面标题
     * @return
     */
    public String getPageTitle(){
        return driver.getTitle();
    }

    /**
     * 获取当前页面Url
     * @return
     */
    public String getCurrenPageUrl(){
        return driver.getCurrentUrl();
    }

    /**
     * 切换浏览器窗口：
     *      获取所有浏览器窗口句柄进行遍历，如果遍历到预期的窗口Title则跳出循环
     * @param expectTitle 预期窗口的Title
     */
    public void switchToWindow(String expectTitle){
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles){
            driver.switchTo().window(handle);
            if (driver.getTitle().contains(expectTitle)){
                break;
            }
        }
    }

    /**
     * 获得屏幕的分辨率 - 宽
     * @return
     */
    public double getScreenWidth(){
        return Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }

    /**
     * 获得屏幕的分辨率 - 高
     * @return
     */
    public double getScreenHeight(){
        return Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    }


    /*--------------------------------操作Alert的方法封装------------------------------------*/
    /**
     * 关闭或取消Alert对话框
     */
    public void dismissAlert(){
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    /**
     * 确定Alert对话框
     */
    public void acceptAlert(){
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    /**
     * 获取Alert文本内容
     * @return
     */
    public String getAlertText(){
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    /**
     * Alert中输入文本内容
     * @param inputText 需要输入的文本内容
     */
    public void inputTextAlert(String inputText){
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(inputText);
    }



    /*--------------------------------操作cookie的方法封装------------------------------------*/
    /**
     * 添加cookies:
     *      做自动登录的必要方法
     * @param millis
     */
    public void addCookies(int millis){
        wait(millis);
        Set<Cookie> cookies=driver.manage().getCookies();
        for(Cookie ck : cookies){
            System.out.println(ck.getName()+ "->" +ck.getValue());
            if(ck.getName().equals("logisticSessionid")){
                Cookie cook=new Cookie(ck.getName(), ck.getValue());
                driver.manage().addCookie(cook);
                System.out.println(ck.getName() + "->" +ck.getValue());
                System.out.println("添加成功！");
            }else{
                System.out.println("未找到logisticSessionid ！！！");
            }
        }
    }

    /**
     * 删除当前域的所有cookies
     */
    public void deleteAllCookies(){
        driver.manage().deleteAllCookies();
    }

    /**
     * 根据cookie名称删除cookie
     * @param cookieName	 cookie的name值
     */
    public void deleteCookies(String cookieName){
        driver.manage().deleteCookieNamed(cookieName);
    }

    /**
     * 根据名称获取指定的cookie
     * @param cookieName cookie名称
     * @return Map<String, String>, 如果没有cookie则返回空
     */
    public Map<String , String> getCookieByName(String cookieName){
        Cookie cookie = driver.manage().getCookieNamed(cookieName);
        if(cookie != null){
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", cookie.getName());
            map.put("value", cookie.getValue());
            map.put("path", cookie.getPath());
            map.put("domain", cookie.getDomain());
            map.put("expiry", cookie.getExpiry().toString());
            return map;
        }
        return null;
    }

    /**
     * 获取当前域所有的cookies
     * @return Set<Cookie> 当前的cookies集合
     */
    public Set<Cookie> getAllCookies(){
        return driver.manage().getCookies();
    }

    /**
     * 用给定的name和value创建默认路径的Cookie并添加, 永久有效
     * @param name
     * @param value
     */
    public void addCookie(String name, String value){
        driver.manage().addCookie(new Cookie(name, value));
    }



    /**
     * 封装element方法
     * @return
     */
//    public WebElement findElement(By by){
//        return driver.findElement(by);
//    }

    /**
     * 获取测试url
     * @param url
     */
    public void getUrl(String url){
        driver.get(url);
    }

    /**
     * 设置cookie
     * @param cookie
     */
    public void setCookie(Cookie cookie){
        driver.manage().addCookie(cookie);
    }

    /**
     * 删除cookie
     */
    public void deleteCookie(){
        driver.manage().deleteAllCookies();
    }

    /**
     * 获取cookie
     * @return
     */
    public Set<Cookie> getCookie(){
        Set<Cookie> cookies = driver.manage().getCookies();
        return cookies;
    }


}

