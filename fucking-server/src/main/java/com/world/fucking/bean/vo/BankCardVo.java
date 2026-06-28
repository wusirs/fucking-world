package com.world.fucking.bean.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime crateTime;
}
