package com.coco.springframework.reader;


import com.coco.springframework.entity.BeanDefinition;
import com.coco.springframework.io.ResourceLoader;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: yanhuanning
 * @Date: 2021/3/8 14:01
 * @Description:
 **/
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    private ConcurrentHashMap<String, BeanDefinition> registry;

    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.registry = new ConcurrentHashMap();
        this.resourceLoader = resourceLoader;
    }

    public ConcurrentHashMap<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
