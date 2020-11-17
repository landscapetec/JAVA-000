package com.lsf.hw_0902.bean.entity;


import com.lsf.hw_0902.aop.MyCache;

public class Dog implements Animal {
    private String name;

    public Dog() {
    }

    public Dog(String name) {
        this.name = name;
    }

    @Override
    @MyCache(expireTime = 5)
    public String eat(String someThing) {
        System.out.println("狗狗:" + name + "吃了" + someThing);
        String result = "狗狗吃饱了";
        System.out.println("本地返回:" + result);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
