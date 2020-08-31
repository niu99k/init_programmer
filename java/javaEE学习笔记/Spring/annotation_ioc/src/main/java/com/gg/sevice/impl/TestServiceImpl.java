package com.gg.sevice.impl;
import com.gg.dao.TestDao;
import com.gg.sevice.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("testService")
public class TestServiceImpl implements TestService {
    @Resource(name = "testDao")
    public TestDao testDao;
}
