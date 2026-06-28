package com.world.fucking.bean.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 银行卡信息.
 *
 * @author heisenberg
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankCard {
    /**
     * 银行卡id，就是卡号.
     */
    private String id;
    /**
     * 卡余额.
     */
    private BigDecimal balance;
    /**
     * 卡类型（0：信用卡，1：储蓄卡）.
     */
    private Integer cardType;

    /**
     * 建卡时间.
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime crateTime;
}
