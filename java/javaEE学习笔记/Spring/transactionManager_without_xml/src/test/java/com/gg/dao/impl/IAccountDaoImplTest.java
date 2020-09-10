package com.gg.dao.impl;

import com.gg.config.SpringConfig;
import com.gg.dao.AccountDao;
import com.gg.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class IAccountDaoImplTest {
    @Resource
    private AccountDao accountDao;

    @Test
    public void findAccountById() {
        Account account = accountDao.findAccountById(1);
        System.out.println(account);
    }

    public void updateAccount(Account account) {

    }
}
