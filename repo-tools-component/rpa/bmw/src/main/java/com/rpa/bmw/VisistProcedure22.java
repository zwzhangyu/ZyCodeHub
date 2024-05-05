package com.rpa.bmw;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class VisistProcedure22 {
    private static final List<String> HREF_LIST = new ArrayList<>();

    // 解码 base64 编码的图片数据
    private static byte[] decodeBase64Image(String base64Image) {
        return java.util.Base64.getDecoder().decode(base64Image.split(",")[1]);
    }

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = WebDriverUtils.opneUrl("https://ysfw.mot.gov.cn/NetRoadCGSS-web/information/query?searchType=person");
        assert driver != null;
        try {
            Thread.sleep(2000);
            WebElement staffNameField = driver.findElement(By.name("staffName"));
            staffNameField.sendKeys("贺志高");
            WebElement idCardField = driver.findElement(By.name("idCard"));
            idCardField.sendKeys("410724198009287055");

            WebElement form = driver.findElement(By.id("staffForm"));
            WebElement submitButton = form.findElement(By.tagName("button"));
            submitButton.click();
            Thread.sleep(2000);
            // 截图并保存弹框截图
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String fileName = "/Users/zhangyu/code/zy/github/ZyCodeHub/repo-tools-component/rpa/bmw/test.png";
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
            }
            FileUtil.copyFile(screenshot, new File(fileName));
            By captcha_img_path = By.xpath("//*[@id=\"captcha_img\"]/div/div/div[2]/div/div[1]/div/div");
            WebElement captcha_img_btn = form.findElement(captcha_img_path);
            captcha_img_btn.click();
            Thread.sleep(2000);
            captcha_img_btn.click();
            // 创建 Actions 对象
            Actions actions = new Actions(driver);
            // 解析坐标信息
            // 解析坐标信息
            WebElement element = driver.findElement(By.className("verify-img-out"));
            // 获取元素左上角顶点坐标
            Point location = element.getLocation();
            // 获取图片元素
            WebElement imgElement = driver.findElement(By.className("back-img"));
            // 获取图片的 src 属性
            String imgSrc = imgElement.getAttribute("src");

            // 下载图片
            try {
                File imgFile = FileUtil.writeBytes(decodeBase64Image(imgSrc), new File("downloaded_image.png"));
                BufferedImage image = ImageIO.read(imgFile);
                // 修改图片尺
                int newWidth = 400;
                int newHeight = 200;
                BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, image.getType());
                resizedImage.getGraphics().drawImage(image.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH), 0, 0, null);
                // 保存修改后的图片
                File outputFile = new File("resized_image.png");
                ImageIO.write(resizedImage, "png", outputFile);
                // 等待一段时间以便查看效果
                Thread.sleep(3000);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            // 下载图片并设置宽度和高度
            try {
                // 等待一段时间以便查看效果
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 输出坐标
            System.out.println("X 坐标: " + location.getX());
            System.out.println("Y 坐标: " + location.getY());
            String[] coordinates = {"52,30", "134,33", "102,89"};

            for (String coord : coordinates) {
                String[] xy = coord.split(",");
                int x = Integer.parseInt(xy[0]) - 195;
                int y = Integer.parseInt(xy[1]) - 95;
                System.out.println(location.getX() - 200 + x);
                System.out.println(location.getY() - 100 + y);
                // 显示鼠标轨迹
                actions.moveToElement(element, x, y).perform();
                actions.click().perform();
                // 可以加一些等待时间，让页面有响应
                try {
                    Thread.sleep(1000); // 等待 1 秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Thread.sleep(5000);
        } catch (Exception e) {
            log.info("AAA{}{}", e, e.getMessage());
        }
        //driver.quit();
    }
}
