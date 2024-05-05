package com.rpa.bmw;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

@Slf4j
public class WebDriverUtils {

    /**
     * 打开获取浏览器，打开地址，浏览器最大化
     *
     * @param url 地址
     * @return
     */
    public static WebDriver opneUrl(String url) {
        try {
//            System.setProperty("webdriver.chrome.driver", "/Users/zhangyu/code/zy/github/ZyCodeHub/repo-tools-component/rpa/bmw/chromedriver");
//            System.setProperty("webdriver.gecko.driver", "D:\\zhangyu\\CodeHub\\repo\\rpa\\csdn\\geckodriver.exe");
            System.setProperty("webdriver.gecko.driver", "/Users/zhangyu/code/zy/github/ZyCodeHub/repo-tools-component/rpa/bmw/geckodriver");
//            WebDriver driver = new ChromeDriver();
            WebDriver driver = new FirefoxDriver();
            Thread.sleep(1000);
            driver.get(url);
            //浏览器最大化
            driver.manage().window().maximize();
            return driver;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

    }

    /**
     * 关闭火狐浏览器
     *
     * @param
     * @return
     */
    public static void closeFirefix() throws IOException {
        String command = "taskkill /f /im Firefox.exe";
        Runtime.getRuntime().exec(command);
    }

    /**
     * 关闭ie浏览器
     *
     * @return
     */
    public static void closeIE() throws IOException {
        String command = "taskkill /f /im iexplore.exe";
        Runtime.getRuntime().exec(command);
    }

    /**
     * 判断某个元素是否存在
     */
    public static boolean isExistElement(WebDriver webDriver, By by) {
        try {
            webDriver.findElement(by);
            return true;
        } catch (Exception e) {
            log.error("元素未找到：{}", by.toString());
            return false;
        }
    }

    /**
     * 修改input的type为text
     *
     * @param driver
     * @param elementId
     * @return void
     * @author shangliming
     * @date 2020/11/26 9:29
     */
    public static void setInputType(WebDriver driver, String elementId) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        String s = "document.getElementById('" + elementId + "').getElementsByTagName('input')[1].type='text'";
        jse.executeScript(s);
    }

    /**
     * 通过js实现往input中塞值(针对webdriver中无法给hidden塞值问题)
     *
     * @param driver
     * @param elementId
     * @param value
     * @return void
     * @author shangliming
     * @date 2020/11/26 9:26
     */
    public static void setValueById(WebDriver driver, String elementId, Object value) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        String s = "document.getElementById('" + elementId + "').value='" + value + "'";
        jse.executeScript(s);
    }

    /**
     * 执行js
     *
     * @param driver
     * @param js
     * @return void
     * @author chujian
     * @date 2020/11/26 9:26
     */
    public static void runJs(WebDriver driver, String js) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(js);
    }
}
