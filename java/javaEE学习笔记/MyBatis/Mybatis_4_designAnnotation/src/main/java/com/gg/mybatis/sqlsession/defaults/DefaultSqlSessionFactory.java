package com.gg.mybatis.sqlsession.defaults;

import com.gg.mybatis.cfg.Configuration;
import com.gg.mybatis.sqlsession.SqlSession;
import com.gg.mybatis.sqlsession.SqlSessionFactory;

/**
 * SqlSessionFactory接口的实现类
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg) {
        this.cfg = cfg;
    }

    /**
     * 创建操作数据库对象
     *
     * @return
     */
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
