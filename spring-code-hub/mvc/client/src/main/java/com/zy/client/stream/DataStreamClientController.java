package com.zy.client.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.UUID;


/**
 *  数据接发送发
 *
 * @author zhangyu
 * @date 2023/6/11
 */
@Slf4j
@RequestMapping("/stream")
@RestController
public class DataStreamClientController {

    public static final String SEND_FILE="/Users/zhangyu/code/test_data/demo2.mp4";

    /**
     * 每个块的大小
     */
    private static final int CHUNK_SIZE = 5242880;

    @GetMapping("/split/send")
    public String sendFileBySplit() throws IOException {
        sendDataToService();
        return "ok";
    }

    public void sendDataToService() throws IOException {
        // 设置文件名称
        String fileName = UUID.randomUUID() +"."+ getFileExtension(SEND_FILE);
        log.info("文件：{} 开始发送",fileName);
        // 读取文件，设置缓冲区读取，避免一次读取OOM
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(SEND_FILE),CHUNK_SIZE)) {
            byte[] buffer = new byte[CHUNK_SIZE];
            int bytesRead;
            int chunkIndex=0;
            while ((bytesRead = inputStream.read(buffer, 0, CHUNK_SIZE)) != -1) {
                chunkIndex++;
                processChunk(fileName, buffer, bytesRead, chunkIndex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("文件：{} 发送完成",fileName);

    }

    private static void processChunk(String fileName, byte[] buffer, int bytesRead, int chunkIndex) {
        // 创建RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        // 设置请求头application/octet-stream
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 分块传输数据给服务
        byte[] chunk = new byte[bytesRead];
        System.arraycopy(buffer, 0, chunk, 0, bytesRead);
        // 构建请求实体
        HttpEntity<byte[]> requestEntity = new HttpEntity<>(chunk, headers);
        // 发送数据块给服务A
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                "http://127.0.0.1:9999/stream/upload?fileName=" + fileName,
                requestEntity,
                String.class
        );
        // 处理响应
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            // 响应成功
            log.info("数据分块 {} 发送成功", (chunkIndex + 1) );
        } else {
            // 响应失败
            log.error("数据分块 {} 发送失败", (chunkIndex + 1) );
        }
    }

    public static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
            return "";
        } else {
            return fileName.substring(dotIndex + 1);
        }
    }
}
