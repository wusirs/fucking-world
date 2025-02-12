package com.heisenberg.blbl.controller;

import com.alibaba.fastjson2.JSONObject;
import com.heisenberg.blbl.feign.ConfigControllerFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign")
@Slf4j
public class FeignController {
    private final ConfigControllerFeign configControllerFeign;

    public FeignController(ConfigControllerFeign configControllerFeign) {
        this.configControllerFeign = configControllerFeign;
    }

    @PostMapping("postFeign")
    public void postFeign(){
        ResponseEntity<String> res = configControllerFeign.dateformat();
        log.info(JSONObject.toJSONString(res));
    }
}
