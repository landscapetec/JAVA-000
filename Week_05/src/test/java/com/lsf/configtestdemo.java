package com.lsf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lsf.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class configtestdemo {

    @Autowired
    private TestConfig testConfig;

    @Test
    public void show() {
        System.out.println(testConfig.getConfigVar1());
        System.out.println(testConfig.getConfigVar2());
    }
}
