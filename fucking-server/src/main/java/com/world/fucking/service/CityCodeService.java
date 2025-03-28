package com.world.fucking.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.world.fucking.domain.CityCode;

import java.util.List;
import java.util.Map;


/**
 * 邮政编码service
 * @author heisenberg
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
     * @author heisenberg
     */
    CityCode queryById(String cityId);

    /**
     * 条件筛选城市编码
     * @param parameter 过滤条件
     * @return {@link  IPage<CityCode>}
     */
    IPage<CityCode> listCityCode(Map<String, String> parameter);

    /**
     * 更新编码表
     * @param cityCodeList 列表
     * @return {@link Integer}
     */
    Integer updateCityCode(List<CityCode> cityCodeList);
}
