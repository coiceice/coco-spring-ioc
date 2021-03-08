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
                if(refDefinition.getBean()==null){
                   value = doCreateBean(refDefinition);
                }
            }
            field.setAccessible(true);
            field.set(bean,value);
        }
    }
}
