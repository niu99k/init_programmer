package com.gg.dao;

import com.gg.model.User;
//import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserDao {
    /*
    把IUserDao.xml移除，再dao接口方法上使用@Select注解，并且指定SQL语句
    同时需要再SqlMapConfig.xml中的mapper配置时，使用class属性指定dao接口的全限定类名

    实际开发中，因为是越简单越好，所以我们一般不写dao的实现类，无论是xml方式还是注解方式
    但是Mybatis支持写Dao实现类
     */
    List<User> findAll();
}
