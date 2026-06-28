package com.world.fucking.config.life;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * bean 的生命周期
 *
 * @author Bella Ciao
 */
@Component("lifeBean")
@Slf4j
public class LifeBean implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean {

    public LifeBean() {
        log.info("构造方法执行了...");
    }

    @Value("life")
    public void setLifeName(String lifeName) {
        log.info("setLifeName execute ...");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("setBeanFactory execute ...");
    }

    @Override
    public void setBeanName(String beanName) {
        log.info("setBeanName execute ...");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("setApplicationContext execute ...");
    }

    @PostConstruct
    public void init() {
        log.info("init execute ...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("afterPropertiesSet execute ...");
    }

    @PreDestroy
    public void destroy() {
        log.info("destroy execute ...");
    }
}
