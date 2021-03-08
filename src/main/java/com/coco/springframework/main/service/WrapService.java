package com.coco.springframework.main.service;

/**
 * @Author: yanhuanning
 * @Date: 2021/3/8 15:56
 * @Description:
 **/
public class WrapService {
    private HelloWorldService helloWorldService;

    public void say() {
        helloWorldService.saySomething();
    }
}
