package com.lsf.hw_0902.bean.entity;


public class Student {

    private String code;
    private String name;
    private int age;

    public Student(){}

    public Student(String code, String name, int age) {
        this.code = code;
        this.name = name;
        this.age = age;
    }

    public void sayHello(String helloInfo) {
        System.out.println(helloInfo);
        System.out.println("----学生信息-学号：" + code);
        System.out.println("----学生信息-姓名：" + name);
        System.out.println("----学生信息-年龄：" + age);
    }
}
