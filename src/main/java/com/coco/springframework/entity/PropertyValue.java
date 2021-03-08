package com.coco.springframework.entity;

/**
 * @Author: yanhuanning
 * @Date: 2021/3/8 13:25
 * @Description:
 **/
public class PropertyValue {
    private String name;
    private Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

}
