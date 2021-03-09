package com.coco.springframework.factory;


import com.coco.springframework.entity.BeanDefinition;
import com.coco.springframework.entity.BeanReference;
import com.coco.springframework.entity.PropertyValue;

import java.lang.reflect.Field;

/**
 * @Author: yanhuanning
 * @Date: 2021/3/8 13:44
 * @Description:
 **/
public class AutowiredCapableBeanFactory extends AbstractBeanFactory{
    @Override
    public Object doCreateBean(BeanDefinition beanDefinition) throws Exception{
        if(beanDefinition.isSingleton() && beanDefinition.getBean()!=null){
            return beanDefinition.getBean();
        }
        Object bean = beanDefinition.getBeanClass().newInstance();
        if(beanDefinition.isSingleton()){
            beanDefinition.setBean(bean);
        }
        applyPropertyValues(bean,beanDefinition);
        return bean;
    }

    public void applyPropertyValues(Object bean,BeanDefinition beanDefinition) throws Exception{
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValueList()) {
            Field field = bean.getClass().getDeclaredField(propertyValue.getName());
            Object value = propertyValue.getValue();
            if(value instanceof BeanReference){
                BeanReference ref = (BeanReference) value;
                BeanDefinition refDefinition = beanDefinitionMap.get(ref.getName());
                if(refDefinition != null) {
                    if(!refDefinition.isSingleton() || refDefinition.getBean() == null) {
                        value = doCreateBean(refDefinition);
                    } else {
                        value = refDefinition.getBean();
                    }
                } else {
                    // 按照类型匹配，返回第一个匹配的
                    Class clazz = Class.forName(ref.getName());
                    for(BeanDefinition definition : beanDefinitionMap.values()) {
                        if(clazz.isAssignableFrom(definition.getBeanClass())) {
                            if(!definition.isSingleton() || definition.getBean() == null) {
                                value = doCreateBean(definition);
                            } else {
                                value = definition.getBean();
                            }
                        }
                    }
                }
            }
            field.setAccessible(true);
            field.set(bean,value);
        }
    }
}
