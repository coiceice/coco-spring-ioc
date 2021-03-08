package com.coco.springframework.context;

import com.coco.springframework.factory.BeanFactory;

public abstract class AbstractApplicationContext implements ApplicationContext{

    protected BeanFactory beanFactory;

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }

    @Override
    public Object getBean(Class clazz) throws Exception {
        return beanFactory.getBean(clazz);
    }
    
}