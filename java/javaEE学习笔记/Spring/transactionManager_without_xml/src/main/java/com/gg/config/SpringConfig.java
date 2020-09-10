package com.gg.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@PropertySource({"jdbcConfig.properties"})
@Import({JdbcConfig.class,TransactionMangerConfig.class})
@ComponentScan("com.gg")
@EnableTransactionManagement
public class SpringConfig {
}
