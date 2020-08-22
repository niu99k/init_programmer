package com.gg.mybatis.sqlsession;


/*
自定义Mybatis的核心类
可以创建dao接口的代理对象

 */
public interface SqlSession {
    /*
    根据参数创建代理对象
     */
    <T> T getMapper(Class<T> daoInterfaceClass);

    /*
    释放资源
     */
    void close();
}
