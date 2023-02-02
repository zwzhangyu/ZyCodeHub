package com.zy.tool.video.blob;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class Main1 {



    public static void main(String[] args) {
        HttpRequest referer = HttpRequest.get("https://video.xuexi.cn/customerTrans/5fe46739b9b2bda7e29d0210237f56e1/3ebcb3b6-1622dd89bec-0005-0cf3-24e-05740.mp4")
                .header("Referer", "https://www.xuexi.cn/");
        byte[] bytes = referer.execute().bodyBytes();
        File file = FileUtil.writeBytes(bytes, new File("a.mp4"));


    }


}
