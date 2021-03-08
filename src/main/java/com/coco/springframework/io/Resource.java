package com.coco.springframework.io;

import java.io.InputStream;

/**
 * @Author: yanhuanning
 * @Date: 2021/3/8 13:51
 * @Description:
 **/
public interface Resource {
    InputStream getInputStream() throws Exception;
}
