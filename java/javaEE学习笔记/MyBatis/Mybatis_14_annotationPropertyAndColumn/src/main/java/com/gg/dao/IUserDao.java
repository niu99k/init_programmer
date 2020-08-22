package com.gg.dao;

import com.gg.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {
    @Select("select id,username,birthday,sex,address from user")
    @Results(id = "resultMap", value = {
            @Result(id = true, column = "id", property = "uid"),
            @Result(column = "username", property = "uname"),
            @Result(column = "birthday", property = "ubirthday"),
            @Result(column = "sex", property = "usex"),
            @Result(column = "address", property = "uaddress")
    })
    List<User> findAll();


}
