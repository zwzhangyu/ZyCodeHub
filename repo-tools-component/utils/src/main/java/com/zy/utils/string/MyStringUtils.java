package com.zy.utils.string;

/***
 * 
 *   字符串工具类
 * @author ZhangYu
 * @date 2021/9/13
 */
public class MyStringUtils {


    /***
     *   剔除字符串的空格，包含不间断空格，以及匹配空格、制表符、换页符等空白字符
     * @param input  待剔除空格的字符串
     * @return java.lang.String  剔除各种空格后的字符串
     * @author ZhangYu
     * @date 2021/9/13
     */
    public String removeWhitespace(String  input) {
        return  input.replaceAll("\\s*", "").replace("\u00A0", "");
    }

}
