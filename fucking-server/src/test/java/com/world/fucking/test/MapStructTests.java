package com.world.fucking.test;

import com.world.fucking.FuckingWorldApplication;
import com.world.fucking.bean.po.BankCard;
import com.world.fucking.bean.po.BankUser;
import com.world.fucking.converter.BankUserConvert;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = FuckingWorldApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class MapStructTests {

    @Autowired
    private BankUserConvert bankUserConvert;

    @Test
    public void testMapStruct(){
        System.out.println("_____________________________");
        System.out.println(bankUserConvert);
        List<BankCard> bankCardList = new ArrayList<>();
        BankCard bankCard = new BankCard("123", BigDecimal.valueOf(12), 1, null);
        bankCardList.add(bankCard);
        BankUser bankUser = new BankUser("123", "张三", "12321421321", new Date(), "1,2,3", bankCardList);
        log.info(bankUserConvert.po2Dto(bankUser).toString());
    }
}
