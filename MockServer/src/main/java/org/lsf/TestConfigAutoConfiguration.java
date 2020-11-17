package org.lsf;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TestConfigProperties.class)
public class TestConfigAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(TestConfig.class)
    public TestConfig testConfigProperties(TestConfigProperties properties) {
        System.out.println("初始化testConfig");
        return new TestConfig(properties.getConfigvar1(), properties.getConfigvar2());
    }
}
