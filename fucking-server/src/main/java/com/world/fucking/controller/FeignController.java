package com.world.fucking.controller;

import com.alibaba.fastjson2.JSONObject;
import com.world.fucking.feign.ConfigControllerFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * feign 调用接口
 *
 * @author heisenberg
 */
@RestController
@RequestMapping("/feign")
@Slf4j
@Api(value = "feign", tags = "feign")
public class FeignController {
    /**
     * feign interface
     */
    private final ConfigControllerFeign configControllerFeign;

    /**
     * 构造方法
     *
     * @param configControllerFeign feign
     */
    @Autowired
    public FeignController(ConfigControllerFeign configControllerFeign) {
        this.configControllerFeign = configControllerFeign;
    }

    /**
     * 通过 feign 调用接口
     */
    @ApiOperation(value = "获取服务器时间", notes = "feign 调用")
    @PostMapping("postFeign")
    public String postFeign() {
        ResponseEntity<String> res = configControllerFeign.dateformat();
        return res.getBody();
    }

    /**
     * 通过 feign 调用接口
     */
    @ApiOperation(value = "附件上传调用", notes = "feign 调用")
    @PostMapping("feignFileUpload")
    public void feignFileUpload() throws IOException {
        File file = ResourceUtils.getFile("E:\\Users\\heisenberg\\Pictures\\Screenshots\\AA.png");
        MultipartFile multipartFile = new MockMultipartFile(file.getName(),
                new FileInputStream(file)
        );
        configControllerFeign.upload(multipartFile, "10001", "A.png");
    }
}
