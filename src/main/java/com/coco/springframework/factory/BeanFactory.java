package com.coco.springframework.factory;


import com.coco.springframework.entity.BeanDefinition;

public interface BeanFactory {

    Object getBean(String name) throws Exception;
    
    Object getBean(Class clazz) throws Exception;

    void registerBeanDefinition(String name, BeanDefinition beanDefinition);
    
}