package com.lsf.studymybatis.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Reader;

//@Configuration
//public class SqlSessionBeanConfig {
//
//    @Autowired
//    @Qualifier(value = "orderDB")
//    private DataSource dataSource;
//
//    @Bean
//    public SqlSessionFactoryBean sqlSessionFactory() {
//        SqlSessionFactoryBean result = new SqlSessionFactoryBean();
//        result.setDataSource(dataSource);
//        Resource resource = new ClassPathResource("Mybatis-config.xml");
//        result.setConfigLocation(resource);
//        return result;
//    }

//    @Bean
//    public SqlSessionFactory getSqlSessionFactory() {
//        String resource = "Mybatis-config.xml";
//        SqlSessionFactory sqlSessionFactory = null;
//        try {
//            Reader reader = Resources.getResourceAsReader(resource);
//            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return sqlSessionFactory;
//    }
//}
