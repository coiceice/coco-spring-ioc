package com.coco.springframework.entity;

/**
 * @Author: yanhuanning
 * @Date: 2021/3/8 15:00
 * @Description:
 **/
public class BeanReference {
    private String name;
    private Object bean;

    public BeanReference(){

    }

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Object getBean() {
        return bean;
    }
}
