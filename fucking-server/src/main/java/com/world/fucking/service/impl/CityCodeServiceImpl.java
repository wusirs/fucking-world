package com.world.fucking.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.world.fucking.common.RedisCommonConst;
import com.world.fucking.domain.CityCode;
import com.world.fucking.mapper.CityCodeMapper;
import com.world.fucking.service.CityCodeService;
import com.world.fucking.utils.RedisUtil;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CityCodeServiceImpl extends ServiceImpl<CityCodeMapper, CityCode> implements CityCodeService {
    @Resource
    private CityCodeMapper cityCodeMapper;

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private RedisUtil redisUtil;

    private static final Logger LOGGER = LoggerFactory.getLogger(CityCodeServiceImpl.class);

    @Override
    public IPage<CityCode> queryAll() {
        QueryWrapper<CityCode> queryWrapper = new QueryWrapper<CityCode>().select();
        Page<CityCode> cityCodePage = new Page<>(1, 10);
        return cityCodeMapper.selectPage(cityCodePage, queryWrapper);
    }

    @Override
    public boolean updateById(CityCode entity) {
        RReadWriteLock cityCodeLock = redissonClient.getReadWriteLock("city_code_lock");
        RLock rLock = cityCodeLock.writeLock();
        try {
            rLock.lock();
            redisUtil.del("cityCode");
            return super.updateById(entity);
        }  finally {
            rLock.unlock();
        }
    }

    @Override
    public CityCode queryById(String cityId) {
        String cityCodeKey = RedisCommonConst.CITY_CODE_PRE_KEY + cityId;
        if (redisUtil.hasKey(cityCodeKey)) {
            LOGGER.info("cache:{}", redisUtil.get(cityCodeKey));
            return JSON.parseObject(redisUtil.get(cityCodeKey).toString(), CityCode.class);
        }

        RReadWriteLock cityCodeLock = redissonClient.getReadWriteLock("city_code_lock");
        RLock rLock = cityCodeLock.readLock();
        try {
            rLock.lock();
            CityCode cityCode = super.getById(cityId);
            redisUtil.set(cityCodeKey, cityCode);
            return cityCode;
        }finally {
            rLock.unlock();
        }
    }
}
