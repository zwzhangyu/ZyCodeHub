package com.zy.test.tinyurl;

/*******************************************************
 * Created by ZhangYu on 2024/10/8
 * Description :
 * History   :
 *******************************************************/
public class URLShortenerTest {
    public static void main(String[] args) {
        URLShortenerService service = new URLShortenerService();

        // 长 URL
        String longURL = "https://www.example.com/some/really/long/path/to/resource";

        // 生成短 URL
        String shortURL = service.shortenURL(longURL);
        System.out.println("Shortened URL: " + shortURL);

        // 通过短 URL 还原长 URL
        String originalURL = service.getLongURL(shortURL);
        System.out.println("Original URL: " + originalURL);
    }
}
