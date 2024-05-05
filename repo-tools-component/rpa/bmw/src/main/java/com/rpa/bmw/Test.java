package com.rpa.bmw;

import cn.hutool.core.lang.UUID;

/*******************************************************
 * Created by ZhangYu on 2024/5/3
 * Description :
 * History   :
 *******************************************************/
public class Test {
    public static void main(String[] args) {
//        String fileName = UUID.fastUUID().toString(true);
//        System.out.println(fileName);

        String text = "请依次点击【睡,千,反】";
        System.out.println(text.substring(6, text.length() - 1));


    }
}
