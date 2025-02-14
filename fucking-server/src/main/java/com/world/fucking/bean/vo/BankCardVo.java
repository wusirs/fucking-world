package com.world.fucking.bean.vo;

import lombok.Data;

import java.util.Date;

@Data
public class BankCardVo {
    private String id;
    private String balance;
    private Date crateTime;
}
