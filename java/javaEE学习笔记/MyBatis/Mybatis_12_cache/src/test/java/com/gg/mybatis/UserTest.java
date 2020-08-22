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
    private SqlSessionFactory factory;

    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        sqlSession = factory.openSession();
        iUserDao = sqlSession.getMapper(IUserDao.class);
    }

//    @After
//    public void destroy() throws Exception {
//        sqlSession.close();
//        in.close();
//    }

    /**
     * 测试查询所有
     */
    @Test
    public void testCache() {
        SqlSession sqlSession1 = factory.openSession();
        IUserDao iUserDao1 = sqlSession1.getMapper(IUserDao.class);
        SqlSession sqlSession2 = factory.openSession();
        IUserDao iUserDao2 = sqlSession2.getMapper(IUserDao.class);
        User user1 = iUserDao1.selectOneUserById(41);
        sqlSession1.close();
        User user2 = iUserDao2.selectOneUserById(41);
        sqlSession2.close();
        System.out.println(user1 == user2);
    }

}