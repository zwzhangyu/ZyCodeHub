package com.rpa.csdn;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class VisistProcedure {
    private  static  final  List<String> HREF_LIST =new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {

           for (int j = 0; j < 10; j++) {
               WebDriver driver = WebDriverUtils.opneUrl("https://blog.csdn.net/Octopus21");
               assert driver != null;
               try{
                   List<WebElement> elements = driver.findElements(By.cssSelector(".ui-paging-container > ul > li.ui-pager"));
                   //获取总页数
                   int count = elements.size() - 4;
                   //遍历获取所有文章链接
                   for (int i = 1; i <= count; i++) {
                       driver.get("https://blog.csdn.net/Octopus21/article/list/" + i);
                       List<WebElement> webElementList = driver.findElements(By.cssSelector("#articleMeList-blog > div.article-list > div > h4 > a"));
                       for (WebElement webElement : webElementList) {
                           String href = webElement.getAttribute("href");
                           HREF_LIST .add(href);
                       }
                   }
               }catch (Exception e){
                   log.info("AAA{}{}",e,e.getMessage());
               }
               Thread.sleep(2000);
               for (String href : HREF_LIST ) {
                   try {
                       driver.get(href);
                   }catch (Exception e){
                       System.out.println(e.getMessage());
                   }
               }
               driver.quit();
           }
    }
}
