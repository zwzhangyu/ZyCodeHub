package com.zy.utils.array;


import org.apache.commons.lang3.StringUtils;

/***
 *
 *    数组/集合操作工具类
 * @author ZhangYu
 * @date 2021/10/14
 */
public class ArrayUtil {


    /**
     *   将数组用固定分隔符拼接成字符串
     * @param array  输入数组
     * @param separator  分隔符
     * @return   拼接字符串
     */
    public  String   joinToStr(Object[] array, String separator){
        return   StringUtils.join(array, separator);
    }



}
