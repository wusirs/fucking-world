package com.world.fucking.controller;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.world.fucking.domain.Comment;
import com.world.fucking.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author heisenberg
 * @version 1.0
 */
@RestController
@CrossOrigin
public class CommentController {
    /**
     * 评论服务
     */
    @Resource
    private CommentService commentService;

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    /**
     * 测试接口
     * @param username 姓名
     * @return {@link List<Comment>}
     */
    @GetMapping("hello")
    public List<Comment> helloGet(String username) {
        LOGGER.info(username);
        List<Comment> comments = commentService.queryAll();
        LOGGER.info("comments: {}", comments);
        return comments;
    }

    /**
     * 测试接口
     * @return {@link String}
     */
    @PostMapping("hello")
    public String helloPost() {
        return "post hello world！";
    }


    /**
     * 条件查询
     * @param username 姓名
     * @return {@link List<Comment>}
     * @throws ParseException 解析异常
     */
    @GetMapping("byCondition")
    public List<Comment> byCondition(String username) throws ParseException {
        LOGGER.info(username);
        List<Comment> comments = commentService.byCondition();
        LOGGER.info("comments: {}", comments);
        return comments;
    }

    /**
     * 查询所有
     * @return {@link List<Comment>}
     */
    @PostMapping("queryByCondition")
    public List<Comment> queryByCondition(@RequestBody Map<String, Object> params) {
        return commentService.queryByCondition(params);
    }

    /**
     * 查询所有
     * @return {@link List<Comment>}
     */
    @PostMapping("queryByWrapper")
    public IPage<Comment> queryByWrapper(@RequestBody JSONObject queryCondition) {
        return commentService.queryByWrapper(queryCondition);
    }
}

