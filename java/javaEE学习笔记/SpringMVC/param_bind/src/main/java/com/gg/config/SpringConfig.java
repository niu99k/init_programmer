package com.gg.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@ComponentScan("com.gg")
@Import({})
@EnableWebMvc
@EnableAspectJAutoProxy
public class SpringConfig {
    @Bean(name = "internalResourceViewResolver")
    public InternalResourceViewResolver createInternalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/pages");
        internalResourceViewResolver.setSuffix(".jsp/");
        return internalResourceViewResolver;
    }
}
