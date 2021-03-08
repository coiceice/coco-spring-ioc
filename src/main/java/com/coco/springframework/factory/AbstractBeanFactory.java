package com.coco.springframework.factory;


import com.coco.springframework.entity.BeanDefinition;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBeanFactory implements BeanFactory{

    ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if(beanDefinition == null){
            return null;
        }
        Object bean = beanDefinition.getBean();
        if(beanDefinition.isSingleton() && bean!=null){
            return bean;
        }
        else{
            return doCreateBean(beanDefinition);
        }
    }

    public abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;

    @Override
    public Object getBean(Class clazz) throws Exception {
        BeanDefinition beanDefinition = null;
        Set<Map.Entry<String, BeanDefinition>> entrySet = beanDefinitionMap.entrySet();
        for (Map.Entry<String, BeanDefinition> entry : entrySet) {
            Class beanClass = entry.getValue().getBeanClass();
            if(beanClass==clazz || beanClass.isAssignableFrom(clazz)){
                beanDefinition = entry.getValue();
            }
        }
        if(beanDefinition==null){
            return null;
        }
        if(!beanDefinition.isSingleton() && beanDefinition.getBean()==null){
            return doCreateBean(beanDefinition);
        }
        else{
            return beanDefinition.getBean();
        }
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }

    public void populateBeans()throws Exception{
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            doCreateBean(entry.getValue());
        }
    }
    
}