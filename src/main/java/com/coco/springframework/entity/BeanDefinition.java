package com.coco.springframework.entity;

public class BeanDefinition {
    private Object bean;

    private String className;

    private Class beanClass;

    private PropertyValues propertyValues;

    private boolean isSingleton;

    public boolean isSingleton() {
        return isSingleton;
    }

    public void setSingleton(boolean singleton) {
        isSingleton = singleton;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
        try {
            this.beanClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Class getBeanClass() {

        return beanClass;
    }

    public PropertyValues getPropertyValues() {
        if (propertyValues==null){
            propertyValues = new PropertyValues();
        }
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}