package com.coco.springframework.reader;

/**
 * @Author: yanhuanning
 * @Date: 2021/3/8 13:48
 * @Description:
 **/
public interface BeanDefinitionReader {
    void loadBeanDefinition(String location) throws Exception;
}
