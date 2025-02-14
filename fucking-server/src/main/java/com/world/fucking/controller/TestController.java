package com.world.fucking.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("test")
public class TestController {
    @Value("${blbl.path}")
    private String path;

    /**
     * 读取配置文件
     * @return Object
     */
    @RequestMapping("testA")
    public Object test()  {
        int available;
        byte[] bytes;
        String res = "";
        try (InputStream inputStream = this.getClass().getResourceAsStream(path)) {
            assert inputStream != null;
            available = inputStream.available();
            bytes = new byte[available];
            inputStream.read(bytes, 0, available);
            res = new String(bytes, 0, available, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}
