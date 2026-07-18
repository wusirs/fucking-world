package com.world.fucking.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * feign 实现接口调用
 *
 * @author heisenberg
 */
@FeignClient(value = "fucking-world", path = "/fucking-world")
public interface ConfigControllerFeign {

    /**
     * feign 实现接口调用
     *
     * @return ResponseEntity<String>
     */
    @GetMapping("/config/dateformat")
    ResponseEntity<String> dateformat();


    /**
     * feign 实现接口调用
     *
     * @return ResponseEntity<String>
     */
    @PostMapping(value = "/document/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<String> upload(@RequestPart("file") MultipartFile file,
                                  @RequestParam("userId") String userId,
                                  @RequestParam(value = "remark", required = false) String remark);
}
