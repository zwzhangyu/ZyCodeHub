package com.rpa.bmw;

import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RtcRobotCopy {
    private static final List<String> HREF_LIST = new ArrayList<>();


    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = WebDriverUtils.opneUrl("https://ysfw.mot.gov.cn/NetRoadCGSS-web/information/query?searchType=person");
        assert driver != null;
        try {
            Thread.sleep(2000);
            // 点击name属性为"car"的元素
            WebElement carElement = driver.findElement(By.name("car"));
            carElement.click();
            Thread.sleep(2000);
            WebElement staffNameField = driver.findElement(By.name("vehicleNo"));
            staffNameField.sendKeys("皖ATZ045");
            WebElement idCardField = driver.findElement(By.name("transCertificateCode"));
            idCardField.sendKeys("222222222");
            WebElement form = driver.findElement(By.id("vehicleForm"));
            WebElement submitButton = form.findElement(By.tagName("button"));
            submitButton.click();
            Thread.sleep(2000);
            int maxTime = 5;
            int curTime = 0;
            while (!checkValidationResult(driver) && curTime <= maxTime) {
                curTime++;
                refreshValidImg(driver);
                // 继续验证
                executeTargetIdentification(driver);
                ThreadUtil.sleep(500);
            }
        } catch (Exception e) {
            log.info("AAA{}{}", e, e.getMessage());
        }
        //driver.quit();
    }

    private static void refreshValidImg(WebDriver driver) {
        WebElement verifyRefreshElement = driver.findElement(By.className("verify-refresh"));
        // 手动触发点击事件
        verifyRefreshElement.click();
        ThreadUtil.sleep(500);
    }

    private static void executeTargetIdentification(WebDriver driver) {
        Actions actions = new Actions(driver);
        // 解析坐标信息
        WebElement element = driver.findElement(By.className("verify-img-out"));
        // 获取元素左上角顶点坐标
        Point location = element.getLocation();
        // 获取图片元素
        WebElement imgElement = driver.findElement(By.className("back-img"));
        // 获取图片的 src 属性
        String imgSrc = imgElement.getAttribute("src");
        String fileName = ImageResize.saveImg(imgSrc);
        System.out.println(fileName);
        WebElement verifyMsgElement = driver.findElement(By.className("verify-msg"));
        String verifyMsgElementText = verifyMsgElement.getText();
        System.out.println(verifyMsgElementText);
        String verifyMsg = verifyMsgElementText.substring(6, verifyMsgElementText.length() - 1);
        System.out.println("获取到需要点击的文本信息：" + verifyMsg);
        String image_content = YdmVerify.fileToBase64(fileName);
        // 调用本地OCR

    }

    private static boolean checkValidationResult(WebDriver driver) {
        try {
            // 查找带有class="mask"的元素
            WebElement maskElement = driver.findElement(By.className("mask"));
            // 获取元素的style属性
            String styleAttribute = maskElement.getAttribute("style");
            // 判断style属性是否包含"display: block;"
            if (styleAttribute.contains("display: block;")) {
                System.out.println("验证码验证不通过");
                return false;
            } else {
                System.out.println("验证码验证通过");
                return true;
            }
        } catch (Exception ignored) {

        }
        return false;
    }
}