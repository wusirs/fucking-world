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
import lombok.Getter;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Getter
public class CityCodeServiceImpl extends ServiceImpl<CityCodeMapper, CityCode> implements CityCodeService {
    @Resource
    private CityCodeMapper cityCodeMapper;

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private RedisUtil redisUtil;

    private final CityCodeService cityCodeService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CityCodeServiceImpl.class);

    /**
     * 延迟注入自身
     * @param cityCodeService service
     */
    @Autowired
    public CityCodeServiceImpl(@Lazy CityCodeServiceImpl cityCodeService){
        this.cityCodeService = cityCodeService;
    }

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
        } finally {
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
        } finally {
            rLock.unlock();
        }
    }

    @Override
    public IPage<CityCode> listCityCode(Map<String, String> parameter) {
        QueryWrapper<CityCode> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(!parameter.get("province").isEmpty(), CityCode::getProvince, parameter.get("province"))
                .eq(!parameter.get("area").isEmpty(), CityCode::getAreaCode, parameter.get("area"))
                .and(!parameter.get("name").isEmpty(), wrapper -> wrapper.like(
                        CityCode::getCity, parameter.get("name")
                ).or().like(
                        CityCode::getArea, parameter.get("name")
                ));
        Page<CityCode> page = new Page<>(1, 10);
        return cityCodeMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Integer updateCityCode(List<CityCode> cityCodeList) {
        return cityCodeService.saveOrUpdateBatch(cityCodeList) ? cityCodeList.size() : 0;
    }
}
