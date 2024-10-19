package com.zy.test.tinyurl;

/*******************************************************
 * Created by ZhangYu on 2024/10/8
 * Description :
 * History   :
 *******************************************************/
import java.util.concurrent.ConcurrentHashMap;

public class URLShortenerService {
    private static final String BASE_HOST = "http://short.url/";
    private static final String CHAR_SET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int BASE = CHAR_SET.length();

    private ConcurrentHashMap<String, String> shortToLongMap;
    private ConcurrentHashMap<String, String> longToShortMap;
    private SnowflakeIDGenerator idGenerator;

    public URLShortenerService() {
        shortToLongMap = new ConcurrentHashMap<>();
        longToShortMap = new ConcurrentHashMap<>();
        idGenerator = new SnowflakeIDGenerator(1, 1); // 分布式ID生成器
    }

    // 将自增ID转换为Base62编码
    private String encodeBase62(long id) {
        StringBuilder shortURL = new StringBuilder();
        while (id > 0) {
            shortURL.append(CHAR_SET.charAt((int) (id % BASE)));
            id = id / BASE;
        }
        return shortURL.reverse().toString();
    }

    // 缩短URL，返回短链接
    public String shortenURL(String longURL) {
        // 检查是否已存在相同的长URL
        if (longToShortMap.containsKey(longURL)) {
            return BASE_HOST + longToShortMap.get(longURL);
        }

        // 生成唯一的短URL
        long id = idGenerator.nextId();
        String shortURL = encodeBase62(id);

        // 存储映射
        shortToLongMap.put(shortURL, longURL);
        longToShortMap.put(longURL, shortURL);

        return BASE_HOST + shortURL;
    }

    // 通过短链接获取原始的长链接
    public String getLongURL(String shortURL) {
        return shortToLongMap.get(shortURL.replace(BASE_HOST, ""));
    }
}
