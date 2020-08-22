package com.gg.dao;

import com.gg.domain.Account;

import java.util.List;

public interface IAccountDao {
    List<Account> findAll();

    List<Account> finedAllAssociatedWithUser();

    List<Account> finedAllAssociatedWithUserLazy();

    List<Account> findAccountById(int id);
}
