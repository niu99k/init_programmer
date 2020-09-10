package com.gg.dao;

import com.gg.domain.Account;

public interface AccountDao {
    Account findAccountById(int id);

    void updateAccount(Account account);

    
}
