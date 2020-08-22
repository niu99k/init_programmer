package com.gg.dao;

import com.gg.model.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll();
}
