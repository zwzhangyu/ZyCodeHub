@[toc]
# 功能描述
FeatureProbe提供了用户稳定进入灰度组功能。
大部分情况下，我们希望在一个功能的灰度放量过程中，某个特定用户一旦进入了灰度放量组，在灰度比例不减少的情况下，总是进入灰度组。 不希望用户因为刷新页面、重新打开APP、请求被分配到另一个服务端实例等原因，一会看到新功能，一会看不到新功能，从而感到迷惑。 这种应用场景我们称之为『用户稳定进入灰度组』。
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/b4f5132621ca455c9ff1081fadcc5e57.png)
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/4554c0a7c5fc4bccbe6de453faa39c57.png)
# 原理分析
在FeatureProbe或类似的灰度发布系统中，实现『用户稳定进入灰度组』的核心在于稳定的用户分流策略，即确保同一用户在同一灰度比例下，始终能被分配到固定的组。
使用用户的唯一标识（如 userId 或 uuid），通过哈希算法生成一个固定的值，从而决定用户是否进入灰度组。
步骤：
1. 获取用户的唯一标识（userId、deviceId、或 customKey）。
2. 对该标识应用哈希算法（如 MD5、SHA256 或 CRC32）。
3. 将哈希值映射到一个固定的范围（如 [0, 100) 表示百分比）。
4. 与当前灰度比例进行比较，决定是否进入灰度组。

### 基于字符串hashCode实现

```java
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
```
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/828ee788c51843b183d6eceef7356f39.png)
通过结构可以看到项目的key,走的灰度是一样的，即使调整了灰度百分比，结果也是一样的。

### 增强机制
• 盐值（Salt）： 为了避免用户标识本身过于简单导致哈希分布不均匀，可以在哈希计算前添加一个随机盐值（但盐值应固定，保持一致性）。
• 多条件灰度： 除用户标识外，可以结合其他条件（如 IP、地域、版本号）作为复合键，增强分组的灵活性。
• 缓存优化： 在请求中间层对用户的灰度结果进行缓存，减少重复计算，提升性能。

```java
public class GrayGroupChecker {
    private static final int SALT = 12345; // 固定盐值，保证一致性

    /**
     * 判断用户是否在灰度组
     * @param userId 用户标识
     * @param grayPercentage 灰度比例 (0-100)
     * @return 是否在灰度组
     */
    public static boolean isInGrayGroup(String userId, int grayPercentage) {
        if (userId == null || grayPercentage < 0 || grayPercentage > 100) {
            throw new IllegalArgumentException("Invalid input");
        }
        // 计算哈希值
        int hashValue = Math.abs((userId + SALT).hashCode()) % 100;
        return hashValue < grayPercentage;
    }

    public static void main(String[] args) {
        String userId = "user123";
        int grayPercentage = 30;

        // 检查是否在灰度组
        boolean result = isInGrayGroup(userId, grayPercentage);
        System.out.println("Is user in gray group? " + result);
    }
}

```
### 基于哈希函数实现
hashCode() 是通过字符串的字符内容计算出来的固定整数，能快速反映字符串的特性。算法简单高效，在大多数场景下具有良好的哈希分布特性，但仍可能发生冲突。在实际应用中，尤其是分布式哈希计算时，可以根据需求选择更复杂的哈希函数（如 MurmurHash 或 SHA256）来替代默认的 hashCode() 方法。
使用 SHA-256 计算字符串的哈希值，可以提供更高的安全性和更好的分布均匀性。相较于 hashCode()，SHA-256 生成的是一个固定长度（256 位）的哈希值，通常以 64 个十六进制字符表示。
MurmurHash3 是一种高性能、分布均匀的哈希算法，广泛用于分布式系统中。

【FeatureProbe 的关键实现逻辑】
用户稳定进入灰度组的实现基于以下几个关键点：
1. 用户标识（Key）： 用户的唯一标识（如 UUID、手机号、用户名等）作为计算的输入。
2. 规则分配（Rule Assignment）： 使用用户标识通过一个哈希算法，将其稳定地映射到 [0, 100) 的区间，确保相同标识始终落在同一区间。
3. 灰度范围判定： 根据灰度策略配置的比例（如 30%）判断用户是否属于灰度范围。
4. 使用增强的哈希算法： FeatureProbe 通常使用 MurmurHash 或其他一致性较好的哈希算法来实现均匀分布和高效计算。

```java
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
```
通过组合用户标识和盐值并计算最终的哈希值，使用 MurmurHash3 生成分布均匀且稳定的分组结果，再结合灰度比例判定用户是否属于灰度组。这种实现方式简单高效，能很好地满足灰度发布中“用户稳定进入灰度组”的需求，同时保证了性能和扩展性。


# 完整代码

