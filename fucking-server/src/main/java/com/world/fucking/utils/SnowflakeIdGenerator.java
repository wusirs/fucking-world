package com.world.fucking.utils;

import com.world.fucking.exception.BusinessException;

import static com.world.fucking.enums.ResultEnum.TIME_EXCEPTION;

/**
 * 雪花算法 生成uuid
 * @author heisenberg
 * @since 1.0.0
 */
public class SnowflakeIdGenerator {
    private static final long EPOCH = 1609459200000L; // 起始时间戳（2021-01-01）
    private static final long WORKER_ID_BITS = 5L;      // 机器ID占5位
    private static final long DATACENTER_ID_BITS = 5L;  // 数据中心ID占5位
    private static final long SEQUENCE_BITS = 12L;     // 序列号占12位
    private static final long MAX_WORKER_ID = (1L << WORKER_ID_BITS) - 1; // 最大机器ID（31）
    private static final long MAX_DATACENTER_ID = (1L << DATACENTER_ID_BITS) - 1; // 最大数据中心ID（31）

    /**
     * 机械id
     */
    private final long workerId;

    /**
     * 数据中心 id
     */
    private final long datacenterId;

    /**
     * 序号
     */
    private long sequence = 0L;

    /**
     * 最后一次生成的id的时间戳
     */
    private long lastTimestamp = -1L;

    /**
     * 构造方法
     * @param workerId 机械id
     * @param datacenterId 分布式服务注册中心id
     */
    public SnowflakeIdGenerator(long workerId, long datacenterId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException("Worker ID超出范围");
        }
        if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0) {
            throw new IllegalArgumentException("Datacenter ID超出范围");
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     *  生成 uuid
     * @return {@link long}
     */
    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();
        if (timestamp < lastTimestamp) {
            throw new BusinessException(TIME_EXCEPTION);
        }
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & ((1L << SEQUENCE_BITS) - 1);
            if (sequence == 0) {
                timestamp = waitForNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return ((timestamp - EPOCH) << (WORKER_ID_BITS + DATACENTER_ID_BITS + SEQUENCE_BITS))
                | (datacenterId << (WORKER_ID_BITS + SEQUENCE_BITS))
                | (workerId << SEQUENCE_BITS)
                | sequence;
    }

    /**
     * 阻塞到下一毫秒
     * @param lastTimestamp 最后一次生成id时间戳
     * @return {@link long}
     */
    private long waitForNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}
