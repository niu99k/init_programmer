package com.gg.sevice.impl;

import com.gg.dao.TestDao;
import com.gg.sevice.TestService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

@Service("testService")
@Scope(value = "singleton")
//@Scope(value = "prototype")
public class TestServiceImpl implements TestService {
    @Resource(name = "testDao")
    public TestDao testDao;

}
