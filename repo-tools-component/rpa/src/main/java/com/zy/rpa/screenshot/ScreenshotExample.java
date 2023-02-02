package com.zy.rpa.screenshot;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

/**
 * 使用Selenium对网页特定区域进行截图并保存本地
 *
 * @author zhangyu
 * @date 2022/11/18
 **/
@Slf4j
public class ScreenshotExample {

    public static void main(String[] args) {
        WebDriver driver = null;
        try {
            System.setProperty("webdriver.gecko.driver", "driver/firefox/geckodriver.exe");
            driver = new FirefoxDriver();
            Thread.sleep(1000);
            driver.get("https://www.baidu.com");
            driver.manage().window().maximize();
            // 进行整个屏幕截图，设置输出文件
            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            // 创建本地文件
            File DestFile = new File("screenshot", System.currentTimeMillis() + ".png");
            FileUtil.copyFile(SrcFile, DestFile);
            Thread.sleep(3000);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            assert driver != null;
            driver.quit();
        }
    }
}
