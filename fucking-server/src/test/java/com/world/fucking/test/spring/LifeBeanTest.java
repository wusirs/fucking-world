package com.world.fucking.test.spring;

import com.world.fucking.config.life.LifeBean;
import com.world.fucking.config.life.LifeBeanConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertNotNull;

public class LifeBeanTest {
    @Test
    public void testLifeBean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(LifeBeanConfig.class);
        LifeBean lifeBean = context.getBean(LifeBean.class);
        assertNotNull(lifeBean);
        System.out.println(lifeBean);
        lifeBean.destroy();
    }
}
