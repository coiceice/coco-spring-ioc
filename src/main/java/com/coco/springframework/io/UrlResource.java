package com.coco.springframework.io;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author: yanhuanning
 * @Date: 2021/3/8 13:52
 * @Description:
 **/
public class UrlResource implements Resource {
    private URL url;
    public UrlResource(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws Exception{
        URLConnection connection = url.openConnection();
        connection.connect();
        return connection.getInputStream();
    }
}
