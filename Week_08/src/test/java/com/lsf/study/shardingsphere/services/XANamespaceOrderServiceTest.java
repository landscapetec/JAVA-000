package com.lsf.study.shardingsphere.services;

import com.lsf.study.shardingsphere.StudyShardingSphereApplication;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = {StudyShardingSphereApplication.class})
@RunWith(SpringRunner.class)
public class XANamespaceOrderServiceTest {

    @Autowired
    private XANamespaceOrderService xaNamespaceOrderService;

    @Test
    public void testInsertOrder() throws Exception {
        xaNamespaceOrderService.insertOrder(600);
    }

    @Test
    public void testInsertOrderFail() throws Exception {
        xaNamespaceOrderService.insertOrderFail(600);
    }
} 
