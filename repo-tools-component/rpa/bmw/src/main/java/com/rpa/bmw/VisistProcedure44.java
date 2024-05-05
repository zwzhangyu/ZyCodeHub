package com.rpa.bmw;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class VisistProcedure44 {
    private static final List<String> HREF_LIST = new ArrayList<>();

    // 解码 base64 编码的图片数据
    private static byte[] decodeBase64Image(String base64Image) {
        return java.util.Base64.getDecoder().decode(base64Image.split(",")[1]);
    }

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = WebDriverUtils.opneUrl("https://www.csdn.net/");
        assert driver != null;
        try {
            // 创建 Actions 对象
            Actions actions = new Actions(driver);

            // 设置要点击的坐标位置（示例中为(100, 100)）
            int xCoordinate = 100;
            int yCoordinate = 100;
            // 将鼠标移动到指定的坐标位置并点击（以整个网页为基准点）
            actions.moveByOffset(xCoordinate, yCoordinate).click().perform();

        } catch (Exception e) {
            log.info("AAA{}{}", e, e.getMessage());
        }
        //driver.quit();
    }
}
