package com.gg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

public class TransactionMangerConfig {

    @Bean("transactionManager")
    public PlatformTransactionManager createTransactionManger(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
