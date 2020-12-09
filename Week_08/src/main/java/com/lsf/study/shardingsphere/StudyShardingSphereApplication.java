package com.lsf.study.shardingsphere;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.lsf.study.shardingsphere.mapper"})
public class StudyShardingSphereApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudyShardingSphereApplication.class, args);
    }
}
