package com.gg.mybatis.sqlsession;

import com.gg.mybatis.cfg.Configuration;
import com.gg.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import com.gg.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * 用于创建一个sqlseesion对象
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream config) {
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        return new DefaultSqlSessionFactory(cfg);
    }
}
