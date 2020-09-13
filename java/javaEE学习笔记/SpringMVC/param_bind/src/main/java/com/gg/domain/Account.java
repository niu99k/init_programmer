package com.gg.domain;

import lombok.Data;

@Data
public class Account {
    private int account;

    @Override
    public String toString() {
        return "Account{" +
                "account=" + account +
                '}';
    }
}
