package com.world.fucking.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用户实体
 *
 * @author heisenberg
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankUserDto {
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
    private Date birthday;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 银行卡
     */
    private List<BankCardDto> bankCards;
}
