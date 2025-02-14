package com.world.fucking.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@TableName("common.city_code")
@Data
@ApiModel("城市编码")
public class CityCode {
    @TableId
    @ApiModelProperty(value = "主键", dataType = "string")
    private String id;

    @ApiModelProperty(value = "省份", dataType = "string")
    private String province;

    @ApiModelProperty(value = "城市", dataType = "string")
    private String city;

    @ApiModelProperty(value = "区域", dataType = "string")
    private String area;

    @ApiModelProperty(value = "邮编", dataType = "string")
    private String postCode;

    @ApiModelProperty(value = "区号", dataType = "string")
    private String areaCode;
}
