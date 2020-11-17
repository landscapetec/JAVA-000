package com.lsf.hw_0902.bean.entity;

public class Klass {

    private ISchool school;
    private String name;

    public Klass(){}

    public Klass(ISchool school, String name) {
        this.school = school;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void dong() {
        school.ding();
        System.out.println("----班级信息：" + name);
    }

    public void setSchool(ISchool school) {
        this.school = school;
    }
}
