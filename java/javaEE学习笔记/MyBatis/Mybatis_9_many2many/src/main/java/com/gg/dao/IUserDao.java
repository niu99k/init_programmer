package com.gg.dao;

import com.gg.domain.User;

import java.util.List;

public interface IUserDao {
    //    查询所有
    List<User> findAll();

    //根据id查询一个user
    User selectOneUserById(Integer id);


}
