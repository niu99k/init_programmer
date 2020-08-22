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
     * 测试插入一个user
     */
    @Test
    public void testSaveOneUser() {
        User user = new User("mybatisTestUserName", "mybatisTestAddress", "G", new Date());
        iUserDao.insertOneUser(user);
        sqlSession.commit();
    }

    /**
     * 测试修改一个user
     */
    @Test
    public void testUpdateOneUser() {
        User user = new User("UpdateUserName", "mybatisTestUpdateAddress", "G", new Date());
        user.setId(10);
        iUserDao.updateOneUser(user);
        sqlSession.commit();

    }

    /**
     * 测试删除一个user
     */
    @Test
    public void deleteOneUserById() {
        iUserDao.deleteOneUserById(13);
        sqlSession.commit();
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
     * 查询记录总条数
     */
    @Test
    public void testSelectTotalCount() {
        System.out.println("Total count=" + iUserDao.getTotalCount());
    }

    /**
     * 获取插入后user的ID
     */
    @Test
    public void getInsertUserId() {
        User user = new User("testInsert", "address", "w", new Date());
        System.out.println(user);
        iUserDao.insertOneUser(user);
        System.out.println(user);
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
     * 测试数据库和实体类属性名不一样的查询
     */
    @Test
    public void selectAll_differentColumn() {
        List<User_test> userList = iUserDao.selectAll_differentColumn();
        for (User_test user : userList) {
            System.out.println(user.toString());
        }
    }
}
