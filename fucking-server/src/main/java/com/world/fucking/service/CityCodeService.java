package com.world.fucking.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.world.fucking.domain.CityCode;


/**
 * 邮政编码service
 * @author heisenberg
 * @date 2024/02/19
 * @since 1.0.0
 */
public interface CityCodeService extends IService<CityCode> {
    /**
     * 查询所有城市编码
     * @return {@link IPage<CityCode>}
     */
    IPage<CityCode> queryAll();

    /**
     *
     * @param cityId 城市id
     * @return {@link CityCode}
     * @author Heisenberg
     * @date 2024/6/16 15:08
     */
    CityCode queryById(String cityId);
}
