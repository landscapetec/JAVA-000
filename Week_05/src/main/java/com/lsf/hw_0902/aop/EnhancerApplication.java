package com.lsf.hw_0902.aop;

import com.lsf.hw_0902.bean.entity.Student;

public class EnhancerApplication {
    public static void main(String[] args) {
        EnhancerProxy enhancerProxy = new EnhancerProxy(new Student("001", "lehman", 50));
        Student student = (Student) enhancerProxy.createObject();
        student.sayHello("good morning");
    }
}
