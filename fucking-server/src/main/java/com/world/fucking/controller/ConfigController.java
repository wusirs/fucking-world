package com.world.fucking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * config 查询
 * @author Ciao
 */
@RestController
@RequestMapping("/config")
@RefreshScope
@Slf4j
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
     * @return String
     */
    @GetMapping("dateformat")
    public String dateformat() {
        log.info("url: {}, tUrl: {}", psrUrl, psrTUrl);
        return dateformat;
    }
}