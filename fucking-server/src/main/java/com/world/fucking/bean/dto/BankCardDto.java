package com.world.fucking.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 银行卡信息.
 * @author heisenberg
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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
