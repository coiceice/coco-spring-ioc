package com.coco.springframework.main.service;

import com.coco.springframework.annotaion.Autowired;
import com.coco.springframework.annotaion.Component;
import com.coco.springframework.annotaion.Scope;

/**
 * @Author: yanhuanning
 * @Date: 2021/3/8 15:56
 * @Description:
 **/
@Component(name = "wrapService")
@Scope(value = "prototype")
public class WrapService {
    @Autowired
    private HelloWorldService helloWorldService;

    public void say() {
        helloWorldService.saySomething();
    }
}
