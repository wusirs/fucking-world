package com.world.fucking.bean.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class BankUser {
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
     * db数据库存的是 id 字符串 ==> 1,2,3
     */
    private String bankCards;

    /**
     * 银行卡
     */
    private List<BankCard> bankCardList;
}
