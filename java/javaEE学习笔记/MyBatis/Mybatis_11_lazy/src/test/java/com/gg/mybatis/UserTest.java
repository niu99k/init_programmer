package com.gg.mybatis;

import com.gg.dao.IAccountDao;
import com.gg.dao.IUserDao;
import com.gg.domain.Account;
import com.gg.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 测试mybis crud
 */
public class UserTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao iUserDao;

    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        sqlSession = factory.openSession();
        iUserDao = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws Exception {
        sqlSession.close();
        in.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() {
        List<User> userList = iUserDao.findAll();
        for (User user : userList) {
            System.out.println(user.toString());
        }
    }

    /**
     * 根据id查询一个user
     */
    @Test
    public void testSelectOneUserById() {
        User user = iUserDao.selectOneUserById(10);
        System.out.println(user.toString());
    }
    /**
     * 多表查询 一对多
     */
    @Test
    public void testOne2Many(){
        List<User> userList=iUserDao.findAllAssociatedWithAccount();
//        for(User user:userList){
//            System.out.println("---------------");
//            System.out.println(user.toString());
//            System.out.println(user.getAccountList().toString());
//        }

    }
}
