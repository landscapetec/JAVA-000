package com.lsf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanApplication {

    public static void main(String[] args){
        AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(BeanApplication.class);

        System.out.println("准备获取Bean");

        StudyBean person=ctx.getBean(StudyBean.class);

        System.out.println("获取Bean，关闭ApplicationContext前");

        //关闭IoC容器
        ctx.close();
        System.out.println("关闭ApplicationContext后");
        //SpringApplication.run(BeanApplication.class,args);
    }

    @Bean(name = "studyBean_01")
    public StudyBean getStudentBean(){
        return new StudyBean();
    }
}
