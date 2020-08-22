package com.gg.test;

import com.gg.dao.IUserDao;
import com.gg.model.User;
import com.gg.mybatis.io.Resources;
import com.gg.sqlsession.SqlSession;
import com.gg.sqlsession.SqlSessionFactory;
import com.gg.sqlsession.SqlSessionFactoryBuilder;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    public static void main(String[] args) throws Exception {
        /*
        1.读取配置文件
        2.创建SqlSessionFactory工厂
        3.使用工厂生产SqlSession对象
        4.使用SqlSession创建Dao接口的代理对象
        5.使用代理对象执行方法
        6.释放资源
         */
        //读取配置文件
        /*
        在读取配置文件的过程中，无论使用绝对路径或者相对路径其实都不太靠谱
        如果是 绝对路径 d:/xx/xx  部署以后就失效了
        相对路径 src/com/xx/xx 部署以后也不会有src了
        一般采用两种方法
        1.类加载器，只能读取类路径的配置文件
        2.serveletContext对象的getRealPath()
         */
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory工厂
        /*
        这里采用了构建这模式，好处是把创建细节隐藏，是使用者直接调用方法即可拿到对象
         */
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //使用工厂生产SqlSession对象
        /*
        这里采用工厂模式，好处的解耦，降低类之间的依赖关系
         */
        SqlSession session = factory.openSession();
        //使用SqlSession创建Dao接口的代理对象
        /*
        这里采用了代理模式，不修改源码的基础上，对已有方法增强
         */
        IUserDao userDao = session.getMapper(IUserDao.class);
        //使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user.toString());
        }
        //释放资源
        session.close();
        in.close();
    }
}
