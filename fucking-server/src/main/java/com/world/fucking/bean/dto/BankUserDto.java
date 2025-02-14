package com.world.fucking.bean.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class BankUserDto {
    private String userId;
    private String userName;
    private String idNumber;
    private Date birthday;
    private BigDecimal balance;

    /**
     * db数据库存的是 id 字符串 ==> 1,2,3
     */
    private List<BankCardDto> bankCards;
}
