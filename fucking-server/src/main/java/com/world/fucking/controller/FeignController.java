package com.world.fucking.controller;

import com.alibaba.fastjson2.JSONObject;
import com.world.fucking.feign.ConfigControllerFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * feign 调用接口
 * @author wl
 */
@RestController
@RequestMapping("/feign")
@Slf4j
public class FeignController {
    /**
     * feign interface
     */
    private final ConfigControllerFeign configControllerFeign;

    /**
     * 构造方法
     * @param configControllerFeign feign
     */
    @Autowired
    public FeignController(ConfigControllerFeign configControllerFeign) {
        this.configControllerFeign = configControllerFeign;
    }

    /**
     * 通过feign 调用接口
     */
    @PostMapping("postFeign")
    public void postFeign(){
        ResponseEntity<String> res = configControllerFeign.dateformat();
        log.info(JSONObject.toJSONString(res));
    }
}
