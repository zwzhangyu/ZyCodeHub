package com.zy.test.log_parse;

import cn.hutool.core.lang.UUID;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {

    public static void main(String[] args) {
        String logFilePath = "/Users/zhangyu/【20241018】五证证件版本无法覆盖数据分析/test.txt";  // 日志文件路径
        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                CardBeanDto cardBeanDto = parseLogLine(line);
                if (cardBeanDto != null) {
                    try {
                        cardBeanDto.setUuid(UUID.fastUUID().toString());

                        if (!StringUtils.isEmpty(cardBeanDto.getTimestamp())) {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
                            Date date = null;
                            try {
                                date = formatter.parse(cardBeanDto.getTimestamp());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            cardBeanDto.setTimestampObj(date);
                        }
                        System.out.println(JSONUtil.toJsonPrettyStr(cardBeanDto));
                        Db.use().insert(
                                Entity.create("t_test_card_log")
                                        .set("idcard", cardBeanDto.getIdCard())
                                        .set("uuid", cardBeanDto.getUuid())
                                        .set("card_type", cardBeanDto.getCardType())
                                        .set("carno", cardBeanDto.getCartBadgeNo())
                                        .set("color", cardBeanDto.getCartColor())
                                        .set("card_type", cardBeanDto.getCardType())
                                        .set("tid", cardBeanDto.getTid())
                                        .set("timestamp", cardBeanDto.getTimestampObj())
                        );
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 解析每行日志
    private static CardBeanDto parseLogLine(String logLine) {
        // 日志的正则表达式，匹配日志中的各个部分
        String logPattern = "\\[(.*?)\\] \\[(.*?)\\]\\[(.*?)\\]\\[(.*?)\\]\\[(.*?)\\]\\[(.*?)\\]\\[(.*?)\\] (.*)";
        Pattern pattern = Pattern.compile(logPattern);
        Matcher matcher = pattern.matcher(logLine);
        if (matcher.find()) {
            CardBeanDto.CardBeanDtoBuilder dtoBuilder = CardBeanDto.builder();
            Map<String, Object> logMap = new HashMap<>(16);
            logMap.put("IP", matcher.group(1));
            logMap.put("Timestamp", matcher.group(2));
            logMap.put("LogLevel", matcher.group(3));
            logMap.put("Thread", matcher.group(4));
            logMap.put("TID", matcher.group(5));
            logMap.put("Session", matcher.group(6));
            logMap.put("ClassMethod", matcher.group(7));

            dtoBuilder.tid(logMap.get("TID").toString());
            dtoBuilder.timestamp(logMap.get("Timestamp").toString());

            // 分割日志消息和 LgCardVersionIdBean
            String message = matcher.group(8);
            String[] parts = message.split("：");
            logMap.put("Message", parts[0]);
            if (parts.length > 1) {
                // 解析 LgCardVersionIdBean 对象
                String beanData = parts[1].trim();
                Map<String, String> beanMap = parseBeanData(beanData);
                if (logLine.contains("身份证")) {
                    dtoBuilder.cardType("0");
                    dtoBuilder.idCard(beanMap.get("idCard"));
                } else if (logLine.contains("车头行驶证")) {
                    dtoBuilder.cardType("4");
                    dtoBuilder.cartBadgeNo(beanMap.get("cartBadgeNo"));
                    dtoBuilder.cartColor(beanMap.get("cartColor"));
                } else if (logLine.contains("挂车正反面")) {
                    dtoBuilder.cardType("5");
                    dtoBuilder.cartBadgeNo(beanMap.get("cartBadgeNo"));
                    dtoBuilder.cartColor(beanMap.get("cartColor"));
                }else if (logLine.contains("道路运输证")) {
                    dtoBuilder.cardType("6");
                    dtoBuilder.cartBadgeNo(beanMap.get("cartBadgeNo"));
                    dtoBuilder.cartColor(beanMap.get("cartColor"));
                }else if (logLine.contains("驾驶证")) {
                    dtoBuilder.cardType("1");
                    dtoBuilder.idCard(beanMap.get("idCard"));
                }else if (logLine.contains(" 从业资格证信息完善")) {
                    dtoBuilder.cardType("2");
                    dtoBuilder.idCard(beanMap.get("idCard"));
                }
            }
            return dtoBuilder.build();
        }
        return null;
    }

    // 解析 LgCardVersionIdBean 对象中的数据
    private static Map<String, String> parseBeanData(String beanData) {
        Map<String, String> beanMap = new HashMap<>();
        // 使用正则表达式提取键值对
        Pattern pattern = Pattern.compile("(\\w+)=(.*?)(, |\\))");
        Matcher matcher = pattern.matcher(beanData);
        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2);
            beanMap.put(key, value);
        }
        return beanMap;
    }
}
