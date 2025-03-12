package com.world.fucking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.world.fucking.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 查询所有评论
     * @return List
     */
    List<Comment> queryAll();

    /**
     * 根据条件查询
     * @param params 参数
     * @return {@link List<Comment>}
     */
    List<Comment> queryByCondition(Map<String, Object> params);
}
