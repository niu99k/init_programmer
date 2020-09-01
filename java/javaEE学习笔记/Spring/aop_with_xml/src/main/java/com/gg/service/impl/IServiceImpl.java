package com.gg.service.impl;

import com.gg.service.IService;

public class IServiceImpl implements IService {
    public void test1() {
        System.out.println("test 1-----------------");
    }

    public void test2(int arg) {
        System.out.println("test 2-----------------" + arg);
    }

    public int test3() {
        System.out.println("test 3-----------------");
        return 0;
    }
}
