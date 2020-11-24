package com.lsf.hw_0902.aop;

import com.google.common.reflect.Reflection;
import com.lsf.hw_0902.bean.entity.Animal;
import com.lsf.hw_0902.bean.entity.Dog;

public class GuawaProxy {
    public static void main(String[] args) {

        Animal animal = new Dog("AQi");
        InterfaceProxy defineProxy = new InterfaceProxy(animal);
        Animal proxy = Reflection.newProxy(Animal.class,defineProxy);
        proxy.eat("面包");
    }
}
