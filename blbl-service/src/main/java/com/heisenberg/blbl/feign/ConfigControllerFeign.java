package com.heisenberg.blbl.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * feign 实现接口调用
 * @author Ciao
 */
@FeignClient(value = "blbl-cloud", path = "/blbl-cloud")
public interface ConfigControllerFeign {

    /**
     * feign 实现接口调用
     * @return ResponseEntity<String>
     */
    @GetMapping("/config/dateformat")
    ResponseEntity<String> dateformat();
}
