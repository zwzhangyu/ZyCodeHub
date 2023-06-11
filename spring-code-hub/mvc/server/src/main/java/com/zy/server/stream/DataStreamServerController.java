package com.zy.server.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *  数据接收方
 *
 * @author zhangyu
 * @date 2023/6/11
 */
@RequestMapping("/stream")
@Slf4j
@RestController
public class DataStreamServerController {

    private static final String UPLOAD_DIR = "demo";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadDataChunk(@RequestBody byte[] dataChunk, @RequestParam("fileName") String fileName) {
        try {
            log.info("接收到上传文件：{}",fileName);
            // 处理数据块，例如将数据写入文件
            writeDataChunkToFile(dataChunk, fileName);
            // 返回成功响应
            return ResponseEntity.ok("数据上传成功");
        } catch (Exception e) {
            // 处理上传失败情况
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("数据块处理异常: " + e.getMessage());
        }
    }

    private void writeDataChunkToFile(byte[] dataChunk,String curFileName) throws IOException {
        // 判断文件是否已创建，如果未创建，则进行初始化
        if (!Files.exists(getFilePath(curFileName))) {
            initializeFile(curFileName);
        }
        File file = new File(UPLOAD_DIR, curFileName);
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            // 将文件指针移到末尾
            raf.seek(raf.length());
            // 写入数据块
            raf.write(dataChunk);
        }
        log.info("完成文件读取写入：{}",curFileName);
    }

    /**
     * 初始化文件
     */
    private void initializeFile(String curFileName) throws IOException {
        Path filePath = getFilePath(curFileName);
        Files.createDirectories(filePath.getParent());
        Files.createFile(filePath);
    }

    /**
     * 获取文件路径
     */
    private Path getFilePath(String curFileName) {
        Path uploadDir = Paths.get(UPLOAD_DIR);
        return uploadDir.resolve(curFileName);
    }
}
