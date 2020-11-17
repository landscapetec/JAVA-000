package com.lsf.controller;

import org.lsf.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testconfig")
public class TestConfigController {
    @Autowired
    private TestConfig testConfig;

    @GetMapping("")
    public String get() {
        return testConfig.getConfigVar1() + testConfig.getConfigVar2();
    }
}
