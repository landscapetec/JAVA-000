package com.lsf;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class StudyBean implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, BeanPostProcessor,
        InitializingBean, DisposableBean {
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("调用BeanFactoryAware,设置Factory：【" + beanFactory.getClass().getName() + "】");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("调用BeanNameAware,设置BeanName：【" + s + "】");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("调用destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet：" );

    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("调用BeanPostProcessor,设置BeanPostProcessor---初始化：【" + s + "】" + o.getClass().getName());
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("调用BeanPostProcessor,设置BeanPostProcessor---结束化：【" + s + "】" + o.getClass().getName());
        return o;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("设置程序上下文：" + applicationContext.getClass().getName());
    }
}
