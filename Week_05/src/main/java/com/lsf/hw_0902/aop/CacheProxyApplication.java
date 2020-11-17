package com.lsf.hw_0902.aop;

import com.lsf.hw_0902.bean.entity.Animal;
import com.lsf.hw_0902.bean.entity.Dog;

public class CacheProxyApplication {
    public static void main(String[] args) throws InterruptedException {
        Animal animal = new Dog("AQi");
        //CacheProxy defineProxy = new CacheProxy(animal);
        CacheInterfaceProxy defineProxy=new CacheInterfaceProxy(animal);
        Object proxyObject = defineProxy.createProxy();
        Animal proxyAnimal = (Animal) proxyObject;
        String result = proxyAnimal.eat("大块牛肉");
        System.out.println(result);


        String result1 = proxyAnimal.eat("大块牛肉");
        System.out.println(result1);

        String result2 = proxyAnimal.eat("大块牛肉");
        System.out.println(result2);

        System.out.println("----开始进入等待缓存过期时间窗口----");
        Thread.sleep(5000);
        System.out.println("----程序等待缓存过期----");
        String result3 = proxyAnimal.eat("大块牛肉");
        System.out.println(result3);
    }
}
