package com.lsf.hw_0902.bean.entity;


public class Dog implements Animal {
    private String name;

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public String eat(String someThing) {
        System.out.println("狗狗:" + name + "吃了" + someThing);
        return "狗狗吃饱了";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
