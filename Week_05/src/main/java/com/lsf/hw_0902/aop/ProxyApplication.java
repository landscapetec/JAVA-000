package com.lsf.hw_0902.aop;


import com.lsf.hw_0902.bean.entity.Animal;
import com.lsf.hw_0902.bean.entity.Dog;

public class ProxyApplication {
    public static void main(String[] args) {

        Animal animal = new Dog("AQi");
        InterfaceProxy defineProxy = new InterfaceProxy(animal);

        Object proxyObject = defineProxy.createProxy();
        Animal proxyAnimal = (Animal) proxyObject;
        String result = proxyAnimal.eat("大块牛肉");
        System.out.println(result);

    }
}
