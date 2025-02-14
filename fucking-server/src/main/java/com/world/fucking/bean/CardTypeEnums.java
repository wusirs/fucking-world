package com.world.fucking.bean;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum CardTypeEnums {
    CREDIT_CARD(0, "信用卡"),
    DEBIT_CARD(1, "储蓄卡");


    private final Integer code;
    private final String label;
    CardTypeEnums(Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    public static CardTypeEnums byCode(Integer code){
        return Arrays.stream(values()).filter(t -> t.getCode().equals(code)).findFirst().orElse(CREDIT_CARD);
    }
}
