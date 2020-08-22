package com.gg.mybatis.sqlsession;

public interface SqlSessionFactory {
    /**
     * 用于创建一个新的sqlsession对象
     * @return
     */
    SqlSession openSession();
}
