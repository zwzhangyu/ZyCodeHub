package com.zy.utils.json;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


/*******************************************************
 * Created by ZhangYu on 2024/9/7
 * Description :
 * History   :
 *******************************************************/
public class JsonUtil {
    public static void main(String[] args) throws IOException {
        //已知一个json 字符串
        String json = "{\"name\":\"zhangSan\",\"age\":23,\"address\":\"anhui\"}";
        //求优雅输出
        ObjectMapper mapper = new ObjectMapper();
        Object obj = mapper.readValue(json, Object.class);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
    }

}
