package com.gg.dao;

import com.gg.domain.QueryVo;
import com.gg.domain.User;
import com.gg.domain.User_test;

import java.util.List;

public interface IUserDao {
    //    查询所有
    List<User> findAll();

    //根据id查询一个user
    User selectOneUserById(Integer id);

    //根据userName模糊查询
    List<User> fuzzySelectUsersByName(String userName);

    //根据包装实体类VO模糊查询
    List<User> fuzzySelectByVoName(QueryVo queryVo);

    //测试if标签的有条件模糊查询
    List<User> selectByCondition(User user);

    //in()查询
    List<User> selectUsersByIds(QueryVo vo);

}
