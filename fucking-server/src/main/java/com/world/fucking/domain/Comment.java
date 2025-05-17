package com.world.fucking.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@TableName("fucking.fucking_user_comment")
@ApiModel("评论实体类")
public class Comment {
    @JsonFormat(pattern = "cid")
    @JsonProperty("cid")
    @JsonAlias("cid")
    @TableId(value = "cid")
    @ApiModelProperty("主键")
    @NotNull
    private Integer fuckingId;

    @ApiModelProperty(value = "prevComment", dataType = "integer")
    private Integer prevComment;

    @ApiModelProperty(value = "nextComment", dataType = "integer")
    private Integer userId;

    @ApiModelProperty(value = "relateComment", dataType = "integer")
    private Integer relateComment;

    @ApiModelProperty(value = "content", dataType = "string")
    private String content;

    @ApiModelProperty(value = "videoId", dataType = "integer")
    private Integer videoId;

    @ApiModelProperty(value = "commentTime", dataType = "date")
    private Date commentTime;
}
