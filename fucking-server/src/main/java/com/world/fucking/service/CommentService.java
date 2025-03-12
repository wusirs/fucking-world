package com.world.fucking.service;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.world.fucking.domain.Comment;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * comment service
 * @author heisenberg
 */
public interface CommentService {
    /**
     * 查询全部
     * @return {@link List<Comment>}
     * @author heisenberg
     */
    List<Comment> queryAll();

    /**
     * 根据条件查询
     * @return {@link List<Comment>}
     * @author heisenberg
     */
    List<Comment> byCondition() throws ParseException;

    /**
     * 根据条件查询
     * @param params 查询条件
     * @return {@link List<Comment>}
     * @author heisenberg
     */
    List<Comment> queryByCondition(Map<String, Object> params);

    /**
     * 根据条件分页查询
     * @param queryCondition 查询条件
     * @return {@link IPage<Comment>}
     * @author heisenberg
     */
    IPage<Comment> queryByWrapper(JSONObject queryCondition);
}
