package com.world.fucking.converter;


import com.world.fucking.bean.CardTypeEnums;
import com.world.fucking.bean.dto.BankCardDto;
import com.world.fucking.bean.po.BankCard;
import com.world.fucking.bean.vo.BankCardVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BankCardConvert {

    @Mapping(target = "cardType", expression = "java(cardType(bankCard.getCardType()))")
    BankCardDto po2BankCardDto(BankCard bankCard);

    @Mapping(source = "balance", target = "balance", numberFormat = "￥#.00", defaultValue = "￥0.00")
    BankCardVo dto2BankCarVo(BankCardDto bankCardDto);

    default String cardType(Integer cardType) {
        return CardTypeEnums.byCode(cardType).getLabel();
    }
}
