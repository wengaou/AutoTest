package com.duobei.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class CreateCourse_net {

    private WebDriver driver;

    private String url = "http://www.duobeiyun.net/login";

    private int RoomType = 2;

    private String studentCount = "20";

    private int timeOut = 30;

    private String username = "13501331103";

    private String password = "zhang@888";

    public static Logger log = Logger.getLogger(com.duobei.seleniums.CreateCourse_net.class);



    @Test
    public void test() {

        driver.get(url);

        findElement(By.name("username")).sendKeys(username);
        findElement(By.name("password")).sendKeys(password);
        findElement(By.xpath("//*[@id='login_form']/button")).click();
        //findElement(By.className("btn_login")).click();
        findElement(By.linkText("教室管理")).click();
        findElement(By.linkText("创建教室")).click();
        findElement(By.className("title")).click();



        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        findElement(By.id("meeting_name")).sendKeys("测试课程" + date.format(new Date()));
        findElement(By.id("startTime")).click();
        findElement(By.className("ui-datepicker-current")).click();
        findElement(By.className("ui-datepicker-close")).click();

        WebElement element = findElement(By.id("courseLength"));
        Select select = new Select(element);
        select.selectByVisibleText("3小时");


        WebElement e = findElement(By.className("jiangzuo_type"));
        List<WebElement> list = e.findElements(By.tagName("label"));

        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        list.get(RoomType).findElement(By.name("courseType")).click();

        findElement(By.id("media_flag")).click();
        findElement(By.id("submit_btn")).click();
        findElement(By.className("ui-button-text")).click();

        WebElement LoginUrl = findElement(By.className("login_url")).findElement(By.tagName("a"));
        log.info("教师登录链接：" + LoginUrl.getAttribute("href"));

        findElement(By.className("btn_add_login_code")).click();
        switchTowindow("邀请码");
        findElement(By.className("createCodeBtn")).click();
        findElement(By.id("add_count")).sendKeys(studentCount);
        findElement(By.className("ui-button-text")).click();
        findElement(By.xpath("//*[@id='main']/div[5]/div[11]/div/button/span")).click();
        driver.navigate().refresh();

        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }


        findElement(By.className("show")).click();
        WebElement ele = findElement(By.xpath("//div[@id='listDetail1']/table/tbody"));
        List<WebElement> list1 = ele.findElements(By.tagName("tr"));
        for (int i = 0; i < list1.size(); i++) {
            String code = list1.get(i).findElements(By.tagName("td")).get(1).getText();
            log.info("学生邀请码：" + code);
        }
    }






    @BeforeClass
    public void beforeClass(){
        String path = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", path + "/driver/chromedriver");
        driver = new ChromeDriver();

    }



    public  WebElement findElement(By by) {
        WebElement element = null;
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOut);
        try {
            element = webDriverWait.until(new ExpectedCondition<WebElement>() {
                @Override
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(by);
                }
            });
        } catch (TimeoutException e) {
            log.error("--->>查找元素超时!!! " + timeOut + " 秒之后仍未找到元素 ：[" + by.toString() + "]");
            Assert.fail("--->>查找元素超时!!! " + timeOut + " 秒之后仍未找到元素 ：[" + by.toString() + "]");
        }
        return element;
    }


    public void switchTowindow(String title){
        Set<String> handles = driver.getWindowHandles();
        for (String handle: handles){
            driver.switchTo().window(handle);
            if (driver.getTitle().contains(title)){
                break;
            }
        }
    }











}
