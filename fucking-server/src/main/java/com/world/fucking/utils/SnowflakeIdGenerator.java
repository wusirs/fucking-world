package com.world.fucking.utils;

import com.world.fucking.exception.BusinessException;

import static com.world.fucking.enums.ResultEnum.TIME_EXCEPTION;

public class SnowflakeIdGenerator {
    private static final long EPOCH = 1609459200000L; // 起始时间戳（2021-01-01）
    private static final long WORKER_ID_BITS = 5L;      // 机器ID占5位
    private static final long DATACENTER_ID_BITS = 5L;  // 数据中心ID占5位
    private static final long SEQUENCE_BITS = 12L;     // 序列号占12位
    private static final long MAX_WORKER_ID = (1L << WORKER_ID_BITS) - 1; // 最大机器ID（31）
    private static final long MAX_DATACENTER_ID = (1L << DATACENTER_ID_BITS) - 1; // 最大数据中心ID（31）

    private final long workerId;
    private final long datacenterId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

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

    private long waitForNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}
