package com.heisenberg.blbl.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${pattern.dateformat}")
    private String dateformat;

    @GetMapping("dateformat")
    public String now(){
        return dateformat;
    }

}