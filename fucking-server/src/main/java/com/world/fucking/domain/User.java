package com.world.fucking.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
//@TableName("t_user")
@ApiModel("用户")
public class User {
    @ApiModelProperty(value = "username", dataType = "string")
    private String username;

    @ApiModelProperty(value = "password", dataType = "string")
    private String password;
}
