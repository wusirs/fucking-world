package com.heisenberg.blbl.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/config")
@RefreshScope
@Slf4j
public class ConfigController {

    @Value("${pattern.dateformat}")
    private String dateformat;

    @Value("${psr.gateway.url:}")
    private String psrUrl;

    @Value("${psr.gateway.tUrl:}")
    private String psrTUrl;

    @GetMapping("dateformat")
    public String now(){
        log.info("url: {}, tUrl: {}", psrUrl, psrTUrl);
        return dateformat;
    }

}