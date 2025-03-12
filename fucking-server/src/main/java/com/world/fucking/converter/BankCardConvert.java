package com.world.fucking.converter;


import com.world.fucking.bean.CardTypeEnums;
import com.world.fucking.bean.dto.BankCardDto;
import com.world.fucking.bean.po.BankCard;
import com.world.fucking.bean.vo.BankCardVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 银行卡实体转换器
 * @author heisenberg
 * @since 1.0.0
 */
@Mapper(componentModel = "spring")
public interface BankCardConvert {

    /**
     * po2dto
     * @param bankCard 银行卡实体
     * @return {@link BankCardDto}
     */
    @Mapping(target = "cardType", expression = "java(cardType(bankCard.getCardType()))")
    BankCardDto po2BankCardDto(BankCard bankCard);

    /**
     * dto2vo
     * @param bankCardDto 入参
     * @return {@link BankCardVo}
     */
    @Mapping(source = "balance", target = "balance", numberFormat = "￥#.00", defaultValue = "￥0.00")
    BankCardVo dto2BankCarVo(BankCardDto bankCardDto);

    /**
     * 转义
     * @param cardType 卡类型
     * @return {@link String}
     */
    default String cardType(Integer cardType) {
        return CardTypeEnums.byCode(cardType).getLabel();
    }
}
