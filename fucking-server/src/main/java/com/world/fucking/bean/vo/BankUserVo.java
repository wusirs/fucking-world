package com.world.fucking.bean.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户实体
 *
 * @author heisenberg
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankUserVo {
    /**
     * 用户id
     */
    private String userId;

    /**
     * 姓名
     */
    private String userName;

    /**
     * id
     */
    private String idNumber;

    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 银行卡
     */
    private List<BankCardVo> bankCardList;
}
