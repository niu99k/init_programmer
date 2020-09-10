package com.gg.service.impl;

import com.gg.config.SpringConfig;
import com.gg.service.ITransferService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class ITransferServiceImplTest {
    @Resource(name = "transfer")
    ITransferService iTransferService;

    @Test
    public void testTransfer() {
        iTransferService.transfer(1, 2, 100);
    }

}
