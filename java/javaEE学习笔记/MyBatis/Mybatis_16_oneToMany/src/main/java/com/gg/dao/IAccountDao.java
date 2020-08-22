package com.gg.dao;

import com.gg.domain.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IAccountDao {
    @Select("select * from account where uid=#{uid}")
    List<Account> selectAccountByUid(int uid);
}
