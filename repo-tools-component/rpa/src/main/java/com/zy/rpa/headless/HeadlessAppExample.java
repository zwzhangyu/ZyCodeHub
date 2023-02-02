package com.zy.rpa.headless;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * 使用Selenium采集网站数据，隐藏浏览器模式
 *
 * @author zhangyu
 * @date 2022/11/18
 **/
@Slf4j
public class HeadlessAppExample {

    public static void main(String[] args) {
        WebDriver driver = null;
        try {
            System.setProperty("webdriver.gecko.driver", "driver/firefox/geckodriver.exe");
            FirefoxBinary firefoxBinary = new FirefoxBinary();
            // 设置隐藏浏览器模式
            firefoxBinary.addCommandLineOptions("--headless");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setBinary(firefoxBinary);
            driver = new FirefoxDriver(firefoxOptions);
            Thread.sleep(1000);
            driver.get("https://www.baidu.com");
            // 查找某一元素是否存在
            WebElement webElement = driver.findElement(By.id("su"));
            log.info("元素text信息:{}", webElement.getAttribute("value"));
            Thread.sleep(3000);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            assert driver != null;
            driver.quit();
        }
    }
}
