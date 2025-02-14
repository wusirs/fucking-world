package com.world.fucking.bean.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 银行卡信息DTO.
 */
@Data
public class BankCardDto {
    /**
     * 银行卡信息.
     */
    private String id;
    /**
     * 卡余额.
     */
    private BigDecimal balance;
    /**
     * 卡类型（0：信用卡，1：储蓄卡）.
     */
    private String cardType;
    /**
     * 建卡时间.
     */
    private Date crateTime;
}
