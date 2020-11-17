package com.lsf.hw_0902.bean.entity;

public class School implements ISchool {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public School(String name) {
        this.name = name;
    }

    public School() {
    }

    @Override
    public void ding() {
        System.out.println("---学校名称:" + this.name);
    }
}
