package com.lsf.hw_0902.annotationloading;

import com.lsf.hw_0902.bean.entity.ISchool;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationApplication {

    public static void main(String[] args) {
        System.out.println("获取配置前配置");
        ApplicationContext context = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        System.out.println("配置后");
        ISchool school = (ISchool) context.getBean("school01");
        school.ding();
    }
}
