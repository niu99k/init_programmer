package com.gg.sqlsession;

import com.gg.cfg.Configuration;
import com.gg.sqlsession.defaults.DefaultSqlSessionFactory;
import com.gg.utils.XMLConfigBuilder;

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
