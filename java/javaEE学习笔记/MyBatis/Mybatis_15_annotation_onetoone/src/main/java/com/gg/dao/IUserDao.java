package com.gg.dao;

import com.gg.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserDao {
    //    查询所有
    List<User> findAll();

    //根据id查询一个user
    @Select("select username,birthday,sex,address from user where id=#{id}")
    User selectOneUserById(Integer id);



}
