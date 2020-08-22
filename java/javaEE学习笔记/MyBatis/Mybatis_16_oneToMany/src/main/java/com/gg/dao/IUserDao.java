package com.gg.dao;

import com.gg.domain.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IUserDao {

    //根据id查询一个user
    @Select("select username,birthday,sex,address from user where id=#{id}")
    User selectOneUserById(Integer id);

    @Select("select id,username,birthday,sex,address from user")
    @Results(
            id = "userMap",
            value = {
                    @Result(id = true, column = "id", property = "id"),
                    @Result(column = "username", property = "userName"),
                    @Result(column = "address", property = "address"),
                    @Result(column = "sex", property = "sex"),
                    @Result(column = "birthday", property = "birthday"),
                    @Result(column = "id",property = "accountList",many = @Many(fetchType = FetchType.LAZY,select ="com.gg.dao.IAccountDao.selectAccountByUid"))
            }
    )
    List<User> findAllOneToMany();

}
