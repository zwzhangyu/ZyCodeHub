package com.rpa.csdn;

import com.rpa.bmw.WebDriverUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class BmwApplication {
    private static final List<String> HREF_LIST = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(BmwApplication.class, args);
    }

    public void csdn() throws InterruptedException {
        WebDriver driver = WebDriverUtils.opneUrl("https://ysfw.mot.gov.cn/NetRoadCGSS-web/information/query?searchType=person");
        assert driver != null;
        try {
            List<WebElement> elements = driver.findElements(By.cssSelector(".ui-paging-container > ul > li.ui-pager"));
            //获取总页数
            int count = elements.size() - 4;
            //遍历获取所有文章链接
            for (int i = 1; i <= count; i++) {
                driver.get("https://blog.csdn.net/Octopus21/article/list/" + i);
                List<WebElement> webElementList = driver.findElements(By.cssSelector("#articleMeList-blog > div.article-list > div > h4 > a"));
                for (WebElement webElement : webElementList) {
                    String href = webElement.getAttribute("href");
                    HREF_LIST.add(href);
                }
            }
        } catch (Exception e) {
            log.info("AAA{}{}", e, e.getMessage());
        }
        Thread.sleep(2000);
        for (String href : HREF_LIST) {
            try {
                driver.get(href);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        driver.quit();
    }
}
