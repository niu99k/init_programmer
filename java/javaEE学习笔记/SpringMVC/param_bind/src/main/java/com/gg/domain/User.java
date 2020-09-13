package com.gg.domain;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class User {
    private String name;
    private int age;
    private Account account;
    List<Manager> managerList;
    Map<String, Integer> testMap;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", account=" + account +
                ", managerList=" + managerList +
                ", testMap=" + testMap +
                '}';
    }

}
