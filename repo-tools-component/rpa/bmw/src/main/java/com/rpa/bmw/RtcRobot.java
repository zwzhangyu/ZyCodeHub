package com.rpa.bmw;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RtcRobot {
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
            staffNameField.sendKeys("皖CC7353");
            WebElement idCardField = driver.findElement(By.name("transCertificateCode"));
            idCardField.sendKeys("340322254489");
            WebElement form = driver.findElement(By.id("vehicleForm"));
            WebElement submitButton = form.findElement(By.tagName("button"));
            submitButton.click();
            Thread.sleep(2000);
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
            YdmVerify ydmVerify = new YdmVerify();
            String clickVerify = ydmVerify.clickVerify(image_content, verifyMsg);
            JSONObject jsonObject = JSON.parseObject(clickVerify);
            JSONObject data = jsonObject.getJSONObject("data");
            String dataValue = data.getString("data");
            String[] coordinates = new String[0];
            if (dataValue != null) {
                // 使用 split 方法按照 "|" 分割字符串
                String[] parts = dataValue.split("\\|");
                // 创建一个新的数组用于存储结果
                coordinates = new String[parts.length];
                // 遍历分割后的字符串数组
                for (int i = 0; i < parts.length; i++) {
                    // 使用 split 方法按照 "," 分割每个部分，并将结果存入新数组
                    String[] values = parts[i].split(",");
                    // 构造坐标字符串，以逗号分隔
                    coordinates[i] = values[0] + "," + values[1];
                }
                System.out.println(coordinates);
            }
//            String[] coordinates = {"52,30", "134,33", "102,89"};
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
            Thread.sleep(2000);
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

            jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            // 实例化 JavascriptExecutor 对象

            // 执行 JavaScript 代码
            jsExecutor.executeScript("setTimeout(function(){ var masks = document.getElementsByClassName('mask'); for (var i = 0; i < masks.length; i++) { masks[i].style.display = 'none'; } }, 1000);");




        } catch (Exception e) {
            log.info("AAA{}{}", e, e.getMessage());
        }
        //driver.quit();
    }
}