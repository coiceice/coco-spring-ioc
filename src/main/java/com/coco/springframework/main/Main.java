package com.coco.springframework.main;

import com.coco.springframework.annotaion.Autowired;
import com.coco.springframework.context.ApplicationContext;
import com.coco.springframework.context.ClassPathXmlApplicationContext;
import com.coco.springframework.main.service.HelloWorldService;
import com.coco.springframework.main.service.WrapService;

/**
 * @Author: yanhuanning
 * @Date: 2021/3/8 15:58
 * @Description:
 **/
public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        WrapService wrapService = (WrapService) applicationContext.getBean("wrapService");
        wrapService.say();
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        HelloWorldService helloWorldService2 = (HelloWorldService) applicationContext.getBean("helloWorldService");
        System.out.println("prototype验证：" + (helloWorldService == helloWorldService2));
        WrapService wrapService2 = (WrapService) applicationContext.getBean("wrapService");
        System.out.println("singleton验证：" + (wrapService == wrapService2));
    }
}
