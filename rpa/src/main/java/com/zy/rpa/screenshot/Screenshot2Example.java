package com.zy.rpa.screenshot;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 使用Selenium对网页特定区域进行截图并保存本地
 *
 * @author zhangyu
 * @date 2022/11/18
 **/
@Slf4j
public class Screenshot2Example {

    public static void main(String[] args) {
        WebDriver driver = null;
        try {
            System.setProperty("webdriver.gecko.driver", "driver/firefox/geckodriver.exe");
            driver = new FirefoxDriver();
            Thread.sleep(1000);
            driver.get("https://www.baidu.com");
            driver.manage().window().maximize();
            // 获取页面中某个元素
            WebElement webElement = driver.findElement(By.id("lg"));
            // 进行元素截图，设置输出文件
            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
            BufferedImage bufferedImage = ImageIO.read(srcFile);
            Point location = webElement.getLocation();
            int width = webElement.getSize().getWidth();
            int height = webElement.getSize().getHeight();
            BufferedImage imageSubimage = bufferedImage.getSubimage(location.getX(), location.getY(), width, height);
            ImageIO.write(imageSubimage, "png", new File("screenshot", System.currentTimeMillis() + ".png"));
            Thread.sleep(3000);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            assert driver != null;
            driver.quit();
        }
    }
}
