package com.world.fucking.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * config 查询
 *
 * @author heisenberg
 */
@RestController
@RequestMapping("/config")
@RefreshScope
@Slf4j
@Api(value = "config", tags = "配置")
public class ConfigController {

    /**
     * 时间格式化
     */
    @Value("${pattern.dateformat}")
    private String dateformat;

    /**
     * url
     */
    @Value("${psr.gateway.url:}")
    private String psrUrl;

    /**
     * url
     */
    @Value("${psr.gateway.tUrl:}")
    private String psrTUrl;

    /**
     * 获取时间格式化
     *
     * @return String
     */
    @GetMapping("dateformat")
    public String dateformat() {
        log.info("url: {}, tUrl: {}", psrUrl, psrTUrl);
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateformat);
        return formatter.format(localDateTime);
    }
}