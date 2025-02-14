package com.world.fucking.converter;


import com.world.fucking.bean.CardTypeEnums;
import com.world.fucking.bean.dto.BankCardDto;
import com.world.fucking.bean.dto.BankUserDto;
import com.world.fucking.bean.po.BankCard;
import com.world.fucking.bean.po.BankUser;
import com.world.fucking.bean.vo.BankCardVo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BankUserConvert {
    @Mapping(target = "bankCards", ignore = true) // 忽略这个字段，需要从字符串查询转实体list对象
    BankUserDto po2Dto(BankUser bankUser);

    @Mapping(target = "cardType", expression = "java(cardType(bankCard.getCardType()))")
    BankCardDto po2BankCardDto(BankCard bankCard);

    List<BankUserDto> po2BanKUserDto(List<BankUser> bankUserList);

    List<BankCardDto> po2BankCardDto(List<BankCard> bankCardList);


    @Mapping(source = "balance", target = "balance", numberFormat = "￥#.00", defaultValue = "￥0.00")
    BankCardVo dto2BankCarVo(BankCardDto bankCardDto);


    /**
     * 反转
     * @param bankCardVo vo
     * @return BankCardDto
     */
    @InheritInverseConfiguration(name = "dto2BankCarVo")
    BankCardDto vo2BankUserDto(BankCardVo bankCardVo);


    default String cardType(Integer cardType) {
        return CardTypeEnums.byCode(cardType).getLabel();
    }
}
