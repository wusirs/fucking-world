package com.heisenberg.blbl.converter;


import ch.qos.logback.core.PropertyDefinerBase;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class IpConverter extends PropertyDefinerBase {

    /**
     * 获取服务ip
     * @return String
     */
    @Override
    public String getPropertyValue() {
        String ip = "";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("获取IP报错:", e);
        }
        return ip;
    }
}

