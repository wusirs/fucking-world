package com.world.fucking.bean.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class BankUserVo {
    private String userId;
    private String userName;
    private String idNumber;
    private Date birthday;
    private BigDecimal balance;

    private List<BankCardVo> bankCardList;
}
