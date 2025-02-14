package com.world.fucking.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.world.fucking.domain.Comment;
import com.world.fucking.mapper.CommentMapper;
import com.world.fucking.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Override
    public List<Comment> queryAll() {
        List<Comment> comments = commentMapper.queryAll();
        logger.info("评论: {}", comments);
        return comments;
    }

    @Override
    public List<Comment> byCondition() throws ParseException {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("video_id", "2001");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf.parse("2023-07-28 12:00:00");
        Date date2 = sdf.parse("2023-07-29 12:00:00");
        Date date3 = null;
        Date date4 = null;
        Date date5 = sdf.parse("2023-12-01 12:00:00");
        Date date6 = sdf.parse("2023-12-31 12:00:00");

        if (date1 != null) {
            logger.error("报错了！！！！");
        }


        // 如果三组日期都为空，或者任意一组日期为空，那么会组装出错误的sql，执行会报错。
//             @存在日期为null时，可能为报错
        /**queryWrapper.and(wrapper -> wrapper
         .or ().or(
         wrapper_1 -> wrapper_1.func ( i -> {
         if ( Objects.nonNull ( date1)) {
         i.ge("comment_time", date1);
         }
         }).func(j -> {
         if (Objects.nonNull(date2)) {
         j.le("comment_time", date2);
         }
         }))
         .or().or(
         wrapper_2 -> wrapper_2.func(i -> {
         if (Objects.nonNull(date3)) {
         i.ge("comment_time", date3);
         }
         }).func(j -> {
         if (Objects.nonNull(date4)) {
         j.le("comment_time", date4);
         }
         }))
         .or().or(
         wrapper_3 -> wrapper_3.func(i -> {
         if (Objects.nonNull(date5)) {
         i.ge("comment_time", date5);
         }
         }).func(j -> {
         if (Objects.nonNull(date6)) {
         j.le("comment_time", date6);
         }
         }))
         );*/

        // https://blog.csdn.net/clevermeng123/article/details/120870820
        /*
        所以需要在任意一组日期为空的时候，需要加判断，去掉or / and 结构。
        可以使用or(boolean condition, Consumer<Children> consumer)方法。
        boolean  condition这个参数可以加入自己的判断，如果为false，那么这个or的结构体整个去掉。
        */

        queryWrapper.and(wrapper ->
                    wrapper.or().or(!(Objects.isNull(date1) && Objects.isNull(date2)),
                                    wrapperDate -> wrapperDate
                                            .func(i -> timeGe(i, date1, Comment::getCommentTime))
                                            .func(j -> timeLe(j, date2, Comment::getCommentTime)))
                            .or().or(!(Objects.isNull(date3) && Objects.isNull(date4)),
                                    wrapperDate -> wrapperDate
                                            .func(i -> timeGe(i, date3, Comment::getCommentTime))
                                            .func(j -> timeLe(j, date4, Comment::getCommentTime)))
                            .or().or(!(Objects.isNull(date5) && Objects.isNull(date6)),
                                    wrapperDate -> wrapperDate
                                            .func(i -> timeGe(i, date5, Comment::getCommentTime))
                                            .func(j -> timeLe(j, date6, Comment::getCommentTime)))
        );

        List<Comment> comments = commentMapper.selectList(queryWrapper);
        logger.info("评论: {}", comments);

        return comments;
    }

    private static void timeLe(QueryWrapper<Comment> j, Date date2, SFunction<Comment, ?> commentTime) {
        if (Objects.nonNull(date2)) {
            j.lambda().le(commentTime, date2);
        }
    }

    private static void timeGe(QueryWrapper<Comment> i, Date date1, SFunction<Comment, ?> commentTime) {
        if (Objects.nonNull(date1)) {
            i.lambda().ge(commentTime, date1);
        }
    }

    @Override
    public List<Comment> queryByCondition(Map<String, Object> params) {
        return commentMapper.queryByCondition(params);
    }

    @Override
    public IPage<Comment> queryByWrapper(JSONObject queryCondition) {
        Integer currentPage = queryCondition.getInteger("currentPage");
        Integer pageSize = queryCondition.getInteger("pageSize");
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        JSONObject filter = queryCondition.getJSONObject("filter");
        if (!filter.isEmpty()) {
            filter.forEach((key, value) -> {
                switch (key) {
                    case "commentTime":
                        if (value instanceof ArrayList) {
                            ((ArrayList<?>) value).forEach(item -> {
                                if (item instanceof ArrayList && ((ArrayList<?>) item).size() == 2) {
                                    String commentTimeStart = ((ArrayList<?>) item).get(0).toString();
                                    String commentTimeEnd = ((ArrayList<?>) item).get(1).toString();
                                    commentQueryWrapper.and(
                                            // .lambda() 不加这个 Comment::getCommentTime 无法使用
                                            wrapper -> wrapper.lambda().ge(Comment::getCommentTime, commentTimeStart)
                                                    .or().le(Comment::getCommentTime, commentTimeEnd)
                                    );
                                }
                            });
                        }
                        break;
                    case "objId":
                        // do nothing
                        break;
                    default:
                        break;
                }
            });
        }
        IPage<Comment> commentIPage = new Page<>(currentPage, pageSize);
        commentIPage = commentMapper.selectPage(commentIPage, commentQueryWrapper);
        logger.info("结果：{}", commentIPage);
        return commentIPage;
    }
}
