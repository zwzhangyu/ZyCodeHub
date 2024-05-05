package com.rpa.bmw;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class ImageResize {
    public static BufferedImage resizeImage(BufferedImage image, int newWidth, int newHeight) {
        // 创建一个新的BufferedImage对象，设置宽度、高度和类型
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        // 获取Graphics2D对象，用于进行高级绘图操作
        Graphics2D g2d = resizedImage.createGraphics();
        // 设置RenderingHints以提高图像质量
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 使用AffineTransformOp类进行图像缩放
        AffineTransform at = AffineTransform.getScaleInstance((double) newWidth / image.getWidth(), (double) newHeight / image.getHeight());
        g2d.drawRenderedImage(image, at);
        // 释放资源
        g2d.dispose();
        return resizedImage;
    }


    public static String saveImg(String imgSrc) {
        String fileName = BmwConstants.BASE_PATH + UUID.fastUUID().toString(true) + ".png";
        File file1 = new File(fileName);
        if (file1.exists()) {
            file1.delete();
        }
        File imgFile = FileUtil.writeBytes(decodeBase64Image(imgSrc), file1);
        // 你的原始图像对象
        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(imgFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 新的宽度和高度
        int newWidth = 400;
        int newHeight = 200;
        // 调用resizeImage方法进行图像缩放
        BufferedImage resizedImage = resizeImage(originalImage, newWidth, newHeight);
        // 保存修改后的图片
        File outputFile = new File(fileName);
        try {
            ImageIO.write(resizedImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileName;
    }

    // 解码 base64 编码的图片数据
    private static byte[] decodeBase64Image(String base64Image) {
        return java.util.Base64.getDecoder().decode(base64Image.split(",")[1]);
    }

    public static void main(String[] args) throws IOException {
        // 你的原始图像对象
        BufferedImage originalImage = ImageIO.read(new File("/Users/zhangyu/code/zy/github/ZyCodeHub/repo-tools-component/rpa/bmw/img/1.png"));
        // 新的宽度和高度
        int newWidth = 400;
        int newHeight = 200;
        // 调用resizeImage方法进行图像缩放
        BufferedImage resizedImage = resizeImage(originalImage, newWidth, newHeight);
        // 保存修改后的图片
        File outputFile = new File("/Users/zhangyu/code/zy/github/ZyCodeHub/repo-tools-component/rpa/bmw/img/" + "resized_image.png");
        ImageIO.write(resizedImage, "png", outputFile);
    }
}
