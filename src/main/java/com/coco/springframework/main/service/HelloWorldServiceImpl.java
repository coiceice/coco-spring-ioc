package com.coco.springframework.main.service;

import com.coco.springframework.annotaion.Component;
import com.coco.springframework.annotaion.Value;

/**
 * @Author: yanhuanning
 * @Date: 2021/3/8 15:57
 * @Description:
 **/
@Component(name = "helloWorldService")
public class HelloWorldServiceImpl implements HelloWorldService {
    @Value("不要")
    private String text;

    @Override
    public void saySomething() {
        System.out.println(text);
    }
}
