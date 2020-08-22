package com.gg.dao;

import com.gg.model.User;
import com.gg.mybatis.annotation.Select;

import java.util.List;

public interface IUserDao {
    @Select("select * from user")
    List<User> findAll();
}
