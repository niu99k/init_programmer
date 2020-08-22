package com.gg.mybatis.sqlsession.defaults;

import com.gg.mybatis.cfg.Configuration;
import com.gg.mybatis.sqlsession.SqlSession;
import com.gg.mybatis.sqlsession.procxy.MapperProxy;
import com.gg.mybatis.utils.DataSourceUtils;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

public class DefaultSqlSession implements SqlSession {
    private Configuration cfg;
    private Connection conn;

    public DefaultSqlSession(Configuration cfg) {
        this.cfg = cfg;
        conn = DataSourceUtils.getConnection(cfg);
    }

    public <T> T getMapper(Class<T> daoInterFaceClass) {
        return (T) Proxy.newProxyInstance(daoInterFaceClass.getClassLoader(), new Class[]{daoInterFaceClass}, new MapperProxy(cfg.getMappers(), conn));
    }

    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
