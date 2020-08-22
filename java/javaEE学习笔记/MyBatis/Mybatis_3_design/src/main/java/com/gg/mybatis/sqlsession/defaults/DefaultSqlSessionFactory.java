package com.gg.mybatis.sqlsession.defaults;

import com.gg.mybatis.cfg.Configuration;
import com.gg.mybatis.sqlsession.SqlSession;
import com.gg.mybatis.sqlsession.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg) {
        this.cfg = cfg;
    }

    /*
    用于创建一个新的操作数据库对象
     */
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
