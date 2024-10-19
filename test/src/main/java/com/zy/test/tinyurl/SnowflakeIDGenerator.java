package com.zy.test.tinyurl;

/*******************************************************
 * Created by ZhangYu on 2024/10/8
 * Description :
 * History   :
 *******************************************************/
public class SnowflakeIDGenerator {
    private final long datacenterId;  // 数据中心ID (5 bits)
    private final long machineId;     // 机器ID (5 bits)
    private long sequence = 0L;       // 毫秒内的计数序列 (12 bits)
    private long lastTimestamp = -1L; // 上次生成ID的时间戳

    private static final long twepoch = 1288834974657L;
    private static final long datacenterIdBits = 5L;
    private static final long machineIdBits = 5L;
    private static final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private static final long maxMachineId = -1L ^ (-1L << machineIdBits);
    private static final long sequenceBits = 12L;
    private static final long machineIdShift = sequenceBits;
    private static final long datacenterIdShift = sequenceBits + machineIdBits;
    private static final long timestampLeftShift = sequenceBits + machineIdBits + datacenterIdBits;
    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);

    public SnowflakeIDGenerator(long datacenterId, long machineId) {
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("Datacenter ID can't be greater than %d or less than 0", maxDatacenterId));
        }
        if (machineId > maxMachineId || machineId < 0) {
            throw new IllegalArgumentException(String.format("Machine ID can't be greater than %d or less than 0", maxMachineId));
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    // 获取当前时间戳（毫秒）
    private long timeGen() {
        return System.currentTimeMillis();
    }

    // 阻塞直到下一毫秒
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    // 生成唯一ID
    public synchronized long nextId() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate ID");
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (machineId << machineIdShift) |
                sequence;
    }
}
