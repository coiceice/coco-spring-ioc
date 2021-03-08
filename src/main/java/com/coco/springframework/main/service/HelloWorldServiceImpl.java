package com.coco.springframework.main.service;

/**
 * @Author: yanhuanning
 * @Date: 2021/3/8 15:57
 * @Description:
 **/
public class HelloWorldServiceImpl implements HelloWorldService {
    private String text;

    @Override
    public void saySomething() {
        System.out.println(text);
    }
}
