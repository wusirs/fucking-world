package com.world.fucking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.world.fucking.domain.CityCode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 邮政编码mapper
 * @author heisenberg
 * @date 2024/02/19
 * @since 1.0.0
 */
@Mapper
@Component
public interface CityCodeMapper extends BaseMapper<CityCode> {
}
