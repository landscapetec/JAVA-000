package com.lsf.studymybatis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import lombok.Getter;
//import lombok.Setter;
//import org.apache.ibatis.datasource.DataSourceFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Component;
//import sun.security.util.Resources;
//
//import javax.sql.DataSource;
//
//@ConfigurationProperties(prefix = "spring.datasource.order")
//@Getter
//@Setter
@Configuration
public class DatasourceConfig {

    @Bean(name = "dsmaster")
    @ConfigurationProperties(prefix = "spring.shardingsphere.datasource.dsmaster")
    @Primary
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dsslave0")
    @ConfigurationProperties(prefix = "spring.shardingsphere.datasource.dsslave0")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Autowired
    @Qualifier("dsmaster")
    private DataSource dsMasterDataSource;

    @Autowired
    @Qualifier("dsslave0")
    private DataSource dsSlave0DataSource;

    @Bean
    public MyRoutingDataSource dataSource(){

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("dsmaster", dsMasterDataSource);
        targetDataSources.put("dsslave0", dsSlave0DataSource);

        MyRoutingDataSource dataSource = new MyRoutingDataSource();
        //设置数据源映射
        dataSource.setTargetDataSources(targetDataSources);
        //设置默认数据源，当无法映射到数据源时会使用默认数据源
        dataSource.setDefaultTargetDataSource(dsMasterDataSource);
        dataSource.afterPropertiesSet();

        return dataSource;
    }
}
//    private final Logger logger = LoggerFactory.getLogger(DatasourceConfig.class);
//    //    @Value("${spring.datasource.order.driver-class-name}")
//    private String driverClassName;
//    //    @Value("${spring.datasource.order.username}")
//    private String username;
//    //    @Value("${spring.datasource.order.password}")
//    private String password;
//    //    @Value("${spring.datasource.order.url}")
//    private String url;
//
//    @Value("${spring.datasource.order.hikari.maximum-pool-size}")
//    private int hikariMaximumPoolSize;
//
//    @Value("${spring.datasource.order.hikari.pool-name}")
//    private String hikariPoolName;
//
//    @Primary
//    @Bean(name = "orderDB")
//    public DataSource getOrderDataSource() {
//        logger.info("基础参数：{}{}{}{}", url, username, password, driverClassName);
//        HikariConfig config = new HikariConfig();
//        config.setPoolName(hikariPoolName);
//        config.setJdbcUrl(url);
//        config.setDriverClassName(driverClassName);
//        config.setMaximumPoolSize(hikariMaximumPoolSize);
//        config.setUsername(username);
//        config.setPassword(password);
//
//        HikariDataSource dataSource= new HikariDataSource(config);
//        return dataSource;
////       return DataSourceBuilder.create().type(HikariDataSource.class)
////                .driverClassName(driverClassName)
////                .username(username)
////                .password(password)
////                .url(url)
////                .build();
//    }
//
//    //spring.datasource.hikari.minimum-idle=5
//    //## 空闲连接存活最大时间，默认600000（10分钟）
//    //spring.datasource.hikari.idle-timeout=180000
//    //## 连接池最大连接数，默认是10
//    //spring.datasource.hikari.maximum-pool-size=10
//}
