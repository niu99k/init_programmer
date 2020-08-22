package com.gg.dao;

import com.gg.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IUserDao {
    @Select("select id,username,birthday,sex,address from user")
    List<User> findAll();

    @Insert("insert into user(username,birthday,sex,address) values(#{userName},#{birthday},#{sex},#{address})")
    void saveUser(User user);

    @Update("update user set username=#{userName},birthday=#{birthday},sex=#{sex},address=#{address} where id=#{id}")
    void update(User user);
}
