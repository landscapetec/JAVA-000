package com.lsf.hw_0902.annotationloading;

import com.lsf.hw_0902.bean.entity.ISchool;
import com.lsf.hw_0902.bean.entity.School;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnotationConfig {
    @Bean("school01")
    public ISchool school01() {
        System.out.println("注册Schoole01");
        return new School("第一个5A学校");
    }

}