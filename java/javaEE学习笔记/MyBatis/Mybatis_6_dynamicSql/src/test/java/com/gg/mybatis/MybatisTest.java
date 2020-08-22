package com.gg.mybatis;

import com.gg.dao.IUserDao;
import com.gg.domain.QueryVo;
import com.gg.domain.User;
import com.gg.domain.User_test;
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
public class MybatisTest {
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
     * 根据userName模糊查询
     */
    @Test
    public void testFuzzySelectUsersByName() {
        List<User> userList = iUserDao.fuzzySelectUsersByName("%User%");
        for (User user : userList) {
            System.out.println(user.toString());
        }
    }

    /**
     * 根据vo包装实体类进行模糊查询
     */
    @Test
    public void testFuzzySelectByVoName() {
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUserName("%User%");
        queryVo.setUser(user);
        List<User> userList = iUserDao.fuzzySelectByVoName(queryVo);
        for (User e : userList) {
            System.out.println(e.toString());
        }
    }

    /**
     * 测试if标签，根据条件查询
     */
    @Test
    public void testSelectByCondition() {
        User user = new User();
        user.setUserName("%User%");
        List<User> userList = iUserDao.selectByCondition(user);
        for (User u : userList) {
            System.out.println(u.toString());
        }
    }

    /**
     * 测试select in()
     */
    @Test
    public void testSelectByIds() {
        QueryVo vo = new QueryVo();
        List<Integer> listTemp = new ArrayList<Integer>();
        listTemp.add(1);
        listTemp.add(2);
        listTemp.add(3);
        listTemp.add(4);
        listTemp.add(5);
        listTemp.add(6);
        vo.setIds(listTemp);
        List<User> userList = iUserDao.selectUsersByIds(vo);
        for (User user : userList) {
            System.out.println(user.toString());
        }
    }
}
