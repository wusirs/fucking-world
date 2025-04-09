package com.world.fucking.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("test")
@RefreshScope
public class TestController {
    @Value("${fucking.world.path}")
    private String path;

    /**
     * 读取配置文件
     * @return Object
     */
    @PostMapping("testA")
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
            throw new UncheckedIOException("Failed to read resource: " + path, e);
        }
        return res;
    }
}
