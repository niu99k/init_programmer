package com.gg.service.impl;

import com.gg.dao.AccountDao;
import com.gg.domain.Account;
import com.gg.service.ITransferService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("transfer")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ITransferServiceImpl implements ITransferService {
    @Resource
    AccountDao accountDao;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void transfer(int idA, int idB, int transferMoney) {
        Account accountA = accountDao.findAccountById(idA);
        Account accountB = accountDao.findAccountById(idB);
        accountA.setMoney(accountA.getMoney() - transferMoney);
        accountB.setMoney(accountB.getMoney() + transferMoney);
        accountDao.updateAccount(accountA);
        int i = 1 / 0;
        accountDao.updateAccount(accountB);
    }
}
