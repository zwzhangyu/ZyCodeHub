package com.example.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;

import java.io.*;

public class AliyunOssUntil {

    public static final String endpoint = "oss-cn-shanghai.aliyuncs.com";
    public static final String accessKeyId = " ";
    public static final String accessKeySecret = "  ";
    public static final String bucketName = "app-test-img";

    public static OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

    public static void main(String[] args) throws IOException {
    }


    /**
     * 从阿里云OSS下载图片
     * @throws IOException
     */
    private static void downloadFile() throws IOException {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
        OSSObject ossObject = ossClient.getObject(bucketName, "test.jpg");
        // 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
        InputStream content = ossObject.getObjectContent();
        writeToLocal("test-download.jpg",content);
        ossClient.shutdown();
    }


    public static void writeToLocal(String destination, InputStream input)
            throws IOException {
        int index;
        byte[] bytes = new byte[1024];
        FileOutputStream downloadFile = new FileOutputStream(destination);
        while ((index = input.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        input.close();
        downloadFile.close();
    }



        /**
         * 上传文件
         */
    private static void uploadFile() {
        PutObjectResult putObjectResult = ossClient.putObject(bucketName, "test.jpg", new File("5.png"));
        System.out.println(putObjectResult.getETag());
        System.out.println(putObjectResult.getRequestId());
        ossClient.shutdown();
        System.out.println("上传图片完成！");
    }

}