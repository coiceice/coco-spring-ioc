package com.coco.springframework.context;

public interface ApplicationContext {

    Object getBean(String name) throws Exception;
    
    Object getBean(Class clazz) throws Exception;
    
}