package com.zy.tool.video.blob;

import cn.hutool.http.HttpUtil;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * JAVA实现根据m3u8文件下载视频
 *
 * @author zhangyu
 * @date 2022/11/19
 **/
public class Main {

    //要下载的创建的视频地址
    public final static String fileSavePath = "new.mp4";

    public static void main(String[] args) {
        String API = "https://boot-video.xuexi.cn/video/1005/p/10430998350597652493121005/a5aa722e1e394b4a8012385646993cfb-2.m3u8";
        String result = HttpUtil.get(API);
        System.out.println(result);
        downVideo(API, fileSavePath);
    }

    public static void downVideo(String url, String fileSavePath) {
        try {
            String result = HttpUtil.get(url);
            String[] lines = result.split("\n");
            String urlPrefix = url.substring(0, url.lastIndexOf("/") + 1);
            FileOutputStream fs = new FileOutputStream(fileSavePath);
            for (String line : lines) {
                if (!line.contains("#")) {
                    String tsUrl = urlPrefix + line;
                    System.out.println("当前下载的TS文件：" + tsUrl);
                    byte[] bytes = HttpUtil.downloadBytes(tsUrl);
                    fs.write(bytes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
