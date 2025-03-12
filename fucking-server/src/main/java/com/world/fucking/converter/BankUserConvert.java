package com.world.fucking.converter;


import com.world.fucking.bean.CardTypeEnums;
import com.world.fucking.bean.dto.BankUserDto;
import com.world.fucking.bean.po.BankUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BankCardConvert.class})
public interface BankUserConvert {
    @Mapping(source = "bankUser.bankCardList", target = "bankCards")
    BankUserDto po2Dto(BankUser bankUser);

    List<BankUserDto> po2BanKUserDto(List<BankUser> bankUserList);

    default String cardType(Integer cardType) {
        return CardTypeEnums.byCode(cardType).getLabel();
    }
}
