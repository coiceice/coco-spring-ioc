package com.coco.springframework.io;

import java.net.URL;

/**
 * @Author: yanhuanning
 * @Date: 2021/3/8 13:55
 * @Description:
 **/
public class ResourceLoader {

    public Resource getResource(String location){
        URL url = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(url);
    }
}
