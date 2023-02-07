package com.zy.utils.image;

import cn.hutool.core.io.FileUtil;
import com.aspose.imaging.Image;
import com.aspose.imaging.fileformats.jpeg.JpegCompressionMode;
import com.aspose.imaging.imageoptions.JpegOptions;

import java.io.File;
import java.util.List;

/**
 * 图片处理工具类
 *
 * @author zhangyu
 */
public class ImageUtil {


    public static void main(String[] args) {
        List<File> files = FileUtil.loopFiles("测试照片\\测试等比例压缩");
        for (File file : files) {
            String resultFile = file.getParent() + "\\" + file.getName().replace(".jpg", "") + "COMPRESS" + ".jpg";
            compress(file.getAbsolutePath(), 1024, 768, file.getParent(), resultFile);
        }
    }

    public static void compress(String path, int width, int height, String parent, String newPath) {
        // 加载图片
        Image original = Image.load(path);
        try {
            // 设置宽度，高度等比例
            original.resizeWidthProportionally(width);
            JpegOptions jpegOptions = new JpegOptions() {{
                // 应用压缩
                setCompressionType(JpegCompressionMode.Progressive);

            }};
            jpegOptions.setQuality(78);
            original.save(newPath, jpegOptions);
        } finally {
            original.dispose();
        }
    }
}
