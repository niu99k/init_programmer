package com.gg.dao;

import com.gg.domain.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IAccountDao {
    @Select("select id,uid,money from account")
    @Results(
            id = "reusltMap",
            value = {
                    @Result(id = true, column = "id", property = "id"),
                    @Result(column = "uid", property = "user", one = @One(select = "com.gg.dao.IUserDao.selectOneUserById")/*,fetchType = FetchType.EAGER*/),
                    @Result(column = "money", property = "money")
            }
    )
    List<Account> findAll();

    List<Account> finedAllAssociatedWithUser();
}
