package com.zy.tool.logtxt;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.ReUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LogParser {

    public static void main(String[] args) {
        // 读取日志文件
        FileReader fileReader = new FileReader("/Users/zhangyu/Downloads/lg_log_search_20240723 (1).txt");
        List<String> logLines = fileReader.readLines();

        // 定义正则表达式，提取所需的信息
        String timeRegex = "\\[(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})";
        String tidRegex = "\\[TID: ([^\\]]+)";

        List<Map<String, Object>> rows = new ArrayList<>();
        // 解析每一行日志信息
        for (String logLine : logLines) {
            // 使用正则表达式提取所需的信息
            String time = ReUtil.get(timeRegex, logLine, 1);
            String tid = ReUtil.get(tidRegex, logLine, 1);
            String description = getCard(logLine);
            String cartBadgeNo = getCartBadgeNo(logLine);
            String idCard = getIdCard(logLine);
            String tlCartBadgeNo = getTlCartBadgeNo(logLine);
            // 创建一个Map，存放提取的信息
            Map<String, Object> row = Map.of(
                    "time", time,
                    "TID", tid,
                    "description", description,
                    "cartBadgeNo", cartBadgeNo,
                    "idCard", idCard,
                    "tlCartBadgeNo", tlCartBadgeNo
            );
            System.out.println(row);
            rows.add(row);
        }

        // 使用Hutool库将数据写入Excel
        ExcelWriter writer = ExcelUtil.getWriter("/Users/zhangyu/Downloads/output.xlsx");

        // 自定义标题别名
        writer.addHeaderAlias("time", "时间");
        writer.addHeaderAlias("TID", "TID");
        writer.addHeaderAlias("description", "证件描述");
        writer.addHeaderAlias("cartBadgeNo", "车牌号");
        writer.addHeaderAlias("tlCartBadgeNo", "挂车车牌号");
        writer.addHeaderAlias("idCard", "身份证号");


        // 写出内容
        writer.write(rows, true);

        // 关闭writer，释放内存
        writer.close();
    }

    private static String getTlCartBadgeNo(String log) {
        // 定义目标字符串前缀和后缀
        String prefix = "cartBadgeNo=";
        String suffix = ",";
        if (!log.contains(prefix)) {
            return "";
        }
        if (!log.contains("挂车正反面都完善")) {
            return "";
        }
        // 查找前缀的位置
        int prefixIndex = log.indexOf(prefix);
        if (prefixIndex != -1) {
            // 从前缀之后开始找后缀的位置
            int suffixIndex = log.indexOf(suffix, prefixIndex);
            if (suffixIndex != -1) {
                // 截取前缀和后缀之间的部分
                String result = log.substring(prefixIndex + prefix.length(), suffixIndex);
                return result.trim();
            } else {
                return "";
            }
        } else {
            return "";
        }
    }


    private static String getIdCard(String log) {
        // 定义目标字符串前缀和后缀
        String prefix = "idCard=";
        String suffix = ",";
        if (!log.contains(prefix)) {
            return "";
        }
        // 查找前缀的位置
        int prefixIndex = log.indexOf(prefix);
        if (prefixIndex != -1) {
            // 从前缀之后开始找后缀的位置
            int suffixIndex = log.indexOf(suffix, prefixIndex);
            if (suffixIndex != -1) {
                // 截取前缀和后缀之间的部分
                String result = log.substring(prefixIndex + prefix.length(), suffixIndex);
                return result.trim();
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    public static String getCard(String log) {
        // 定义目标字符串
        String target = "完善，不更新当前提交信息";
        // 查找目标字符串的位置
        int targetIndex = log.indexOf(target);
        if (targetIndex != -1) {
            // 向前找到最近的 "]" 符号的位置
            int lastBracketIndex = log.lastIndexOf(']', targetIndex);
            if (lastBracketIndex != -1) {
                // 截取中间的部分
                String result = log.substring(lastBracketIndex + 1, targetIndex + target.length() - "，不更新当前提交信息".length());
                return result.trim();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static String getCartBadgeNo(String log) {
        // 定义目标字符串前缀和后缀
        String prefix = "cartBadgeNo=";
        String suffix = ",";
        if (!log.contains(prefix)) {
            return "";
        }
        if (log.contains("挂车正反面都完善")) {
            return "";
        }
        // 查找前缀的位置
        int prefixIndex = log.indexOf(prefix);
        if (prefixIndex != -1) {
            // 从前缀之后开始找后缀的位置
            int suffixIndex = log.indexOf(suffix, prefixIndex);
            if (suffixIndex != -1) {
                // 截取前缀和后缀之间的部分
                String result = log.substring(prefixIndex + prefix.length(), suffixIndex);
                return result.trim();
            } else {
                return "";
            }
        } else {
            return "";
        }
    }
}
