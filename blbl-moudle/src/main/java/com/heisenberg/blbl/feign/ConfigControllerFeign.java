package com.heisenberg.blbl.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "blbl-cloud", path = "/blbl-cloud")
public interface ConfigControllerFeign {
    @GetMapping("/config/dateformat")
    ResponseEntity<String> dateformat();
}
