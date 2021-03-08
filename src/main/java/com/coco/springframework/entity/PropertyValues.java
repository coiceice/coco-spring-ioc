package com.coco.springframework.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yanhuanning
 * @Date: 2021/3/8 13:23
 * @Description:
 **/
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public PropertyValues() {}

    public void addPropertyValue(PropertyValue propertyValue){
        this.propertyValueList.add(propertyValue);
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }
}
