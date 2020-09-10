package com.gg.domain;

import lombok.Data;

@Data
public class Account {
    private int id;
    private String name;
    private int money;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
