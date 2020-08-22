package com.gg.mybatis;

import com.gg.dao.IRoleDao;
import com.gg.domain.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * 测试mybis crud
 */
public class RoleTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IRoleDao iRoleDao;

    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        sqlSession = factory.openSession();
        iRoleDao = sqlSession.getMapper(IRoleDao.class);
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
        List<Role> roleList= iRoleDao.findAll();
        for (Role role: roleList) {
            System.out.println(role.toString());
        }
    }
    /**
     * 测试查询与user的联表查询
     */
    @Test
    public void testFindAllAssociatedWithUser(){
        List<Role> roleList=iRoleDao.findAllAssociatedWithUser();
        for (Role role: roleList) {
            System.out.println(role.toString());
            System.out.println(role.getUser());
        }
    }


}
