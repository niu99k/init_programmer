package com.gg.dao;

import com.gg.domain.QueryVo;
import com.gg.domain.User;
import com.gg.domain.User_test;

import java.util.List;

public interface IUserDao {
    //    查询所有
    List<User> findAll();

    //插入一个user
    void insertOneUser(User user);

    //修改一个user
    void updateOneUser(User user);

    //根据id删除一个user
    void deleteOneUserById(Integer userId);

    //根据id查询一个user
    User selectOneUserById(Integer id);

    //根据userName模糊查询
    List<User> fuzzySelectUsersByName(String userName);

    //查询记录数
    Integer getTotalCount();

    //根据包装实体类VO模糊查询
    List<User> fuzzySelectByVoName(QueryVo queryVo);

    //数据库和实体类属性名不一样的查询
    List<User_test> selectAll_differentColumn();
}
