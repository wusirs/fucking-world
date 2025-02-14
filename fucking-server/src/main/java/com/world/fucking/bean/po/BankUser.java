package com.world.fucking.bean.po;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class BankUser {
    private String userId;
    private String userName;
    private String idNumber;
    private Date birthday;

    /**
     * db数据库存的是 id 字符串 ==> 1,2,3
     */
    private String bankCards;
}
