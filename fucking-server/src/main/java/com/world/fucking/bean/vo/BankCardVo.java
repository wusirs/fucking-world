package com.world.fucking.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 银行卡实体
 * @author heisenberg
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankCardVo {
    /**
     * 卡号
     */
    private String id;

    /**
     * 余额
     */
    private String balance;

    /**
     * 建卡时间
     */
    private Date crateTime;
}
