package com.world.fucking.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.world.fucking.domain.CityCode;
import com.world.fucking.service.CityCodeService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("cityCode")
public class CityCodeController {
    @Resource
    private CityCodeService cityCodeService;

    /**
     * 查询所有城市编码
     * @return {@link IPage<CityCode>}
     */
    @RequestMapping("/queryAll")
    public IPage<CityCode> queryAll() {
        return cityCodeService.queryAll();
    }

    /**
     *
     * @param cityCodeList 城市编码集合
     * @author Heisenberg
     * @date 2024/2/27 21:43
     */
    @RequestMapping("/saveCityCode")
    public void saveCityCode(@RequestBody List<CityCode> cityCodeList){
        cityCodeService.saveBatch(cityCodeList);
    }

    /**
     * @param cityCode 城市编码
     * @author Heisenberg
     * @date 2024/6/15 18:10
     */
    @RequestMapping("/updateCityCode")
    public void updateCityCode(@RequestBody CityCode cityCode){
        cityCodeService.updateById(cityCode);
    }

    /**
     *
     * @param cityId 城市id
     * @return {@link CityCode}
     * @author Heisenberg
     * @date 2024/6/16 15:08
     */
    @RequestMapping("/queryById")
    public CityCode queryById(String cityId){
        return cityCodeService.queryById(cityId);
    }
}
