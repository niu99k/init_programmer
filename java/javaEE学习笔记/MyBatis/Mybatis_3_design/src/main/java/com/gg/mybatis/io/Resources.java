package com.gg.mybatis.io;

import java.io.InputStream;

public class Resources {
    /*
    根据传入的参数，获取一个字节输入流
     */
    public static InputStream getResourceAsStream(String filePath) {
        return Resources.class.getClassLoader().getResourceAsStream((filePath));
    }

}
