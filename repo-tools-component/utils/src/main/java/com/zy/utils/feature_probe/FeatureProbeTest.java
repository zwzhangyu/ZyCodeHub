package com.zy.utils.feature_probe;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import org.testng.annotations.Test;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*******************************************************
 * Created by ZhangYu on 2024/11/21
 * Description : FeatureProbe功能测试
 * History   :
 *******************************************************/
public class FeatureProbeTest {

    /**
     * 基于字符串hashCode实现
     */
    @Test
    public void testHash() {
        String[] keyList = {"123", "123", "456", "123", "123", "123", "456", "321", "789"};
        for (String key : keyList) {
            processGrayByHash(key, 50);
        }
    }

    /**
     * 基于字符串hashCode实现
     *
     * @param key            灰度键
     * @param grayPercentage 灰度比例
     */
    private static void processGrayByHash(String key, int grayPercentage) {
        int hashValue = Math.abs(key.hashCode()) % 100; // 将哈希值映射到[0, 100)
        boolean isInGrayGroup = hashValue < grayPercentage;
        System.out.println("灰度计算结果：灰度键：" + key + "  灰度结果：" + isInGrayGroup);
    }


    /**
     * 增加盐值（Salt）
     */
    @Test
    public void testB() {
        String[] keyList = {"123", "123", "456", "123", "123", "123", "456", "321", "789"};
        for (String key : keyList) {
            processGrayWithSalt(key, 50);
        }
    }

    /**
     * @param key            灰度键
     * @param grayPercentage 灰度比例
     */
    private static void processGrayWithSalt(String key, int grayPercentage) {
        // 固定盐值，保证一致性
        int SALT = 12345;
        int hashValue = Math.abs((key + SALT).hashCode()) % 100; // 将哈希值映射到[0, 100)
        boolean isInGrayGroup = hashValue < grayPercentage;
        System.out.println("灰度计算结果：灰度键：" + key + "  灰度结果：" + isInGrayGroup);

    }

    /**
     * 增加盐值（Salt）
     */
    @Test
    public void testSHA256Hash() {
        String[] keyList = {"123", "123", "456", "123", "123", "123", "456", "321", "789"};
        for (String key : keyList) {
            boolean inGrayGroup = isInGrayGroup(key, 50);
            System.out.println("灰度计算结果：灰度键：" + key + "  灰度结果：" + inGrayGroup);
        }
    }

    /**
     * 检查字符串是否在灰度组
     *
     * @param input          输入字符串
     * @param grayPercentage 灰度比例 (0-100)
     * @return 是否在灰度组
     */
    public static boolean isInGrayGroup(String input, int grayPercentage) {
        int hashValue = calculateSHA256Hash(input, 100); // 映射到 [0, 100)
        return hashValue < grayPercentage;
    }

    /**
     * 使用 SHA-256 计算字符串的哈希值，并映射到 [0, range)
     *
     * @param input 输入字符串
     * @param range 目标范围 (如 100 表示 [0, 100) )
     * @return 哈希值映射结果
     */
    public static int calculateSHA256Hash(String input, int range) {
        if (input == null || range <= 0) {
            throw new IllegalArgumentException("Invalid input or range");
        }

        try {
            // 获取 SHA-256 摘要计算实例
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // 计算哈希值
            byte[] hashBytes = digest.digest(input.getBytes());

            // 将前 8 字节转换为一个大整数（可调节用于截取部分哈希值）
            BigInteger hashValue = new BigInteger(1, hashBytes);

            // 映射到目标范围
            return hashValue.mod(BigInteger.valueOf(range)).intValue();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }



    /**
     * Murmur3
     */
    @Test
    public void testMurmur3() {
        String[] keyList = {"123", "123", "456", "123", "123", "123", "456", "321", "789"};
        for (String key : keyList) {
            boolean inGrayGroup = processGrayWithMurmur3(key,"ABCD", 50);
            System.out.println("灰度计算结果：灰度键：" + key + "  灰度结果：" + inGrayGroup);
        }
    }

    public static boolean processGrayWithMurmur3(String identifier, String salt,int percentage) {
        // 计算 MurmurHash3 的哈希值，确保分布均匀
        String hashKey = identifier + ":" + salt;
        HashCode hashCode = Hashing.murmur3_128().hashString(hashKey, StandardCharsets.UTF_8);
        // 将哈希值映射到 [0, 100) 的区间
        return (Math.abs(hashCode.hashCode()) % 100) < percentage;
    }
}
