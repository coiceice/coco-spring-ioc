package com.coco.springframework.context;

import com.coco.springframework.entity.BeanDefinition;
import com.coco.springframework.factory.AbstractBeanFactory;
import com.coco.springframework.factory.AutowiredCapableBeanFactory;
import com.coco.springframework.io.ResourceLoader;
import com.coco.springframework.reader.XmlBeanDefinitionReader;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: yanhuanning
 * @Date: 2021/3/8 15:38
 * @Description:
 **/
public class ClassPathXmlApplicationContext extends AbstractApplicationContext{

    private final Object startupShutdownMonitor = new Object();


    public ClassPathXmlApplicationContext(String location) throws Exception{

        synchronized(startupShutdownMonitor){
            XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
            xmlBeanDefinitionReader.loadBeanDefinition(location);

            ConcurrentHashMap<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();

            AbstractBeanFactory beanFactory = new AutowiredCapableBeanFactory();
            for (Map.Entry<String, BeanDefinition> entry : registry.entrySet()) {
                beanFactory.registerBeanDefinition(entry.getKey(),entry.getValue());
            }

            beanFactory.populateBeans();

            this.beanFactory = beanFactory;
        }
    }
}
