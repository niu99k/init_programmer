package com.gg.service;

import com.gg.domain.Account;

public interface ITransferService {
    void transfer(int idA, int idB, int transferMoney);
}
