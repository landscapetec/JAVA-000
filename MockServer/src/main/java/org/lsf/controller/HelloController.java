package org.lsf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.sun.xml.internal.ws.api.message.Packet.Status.Request;

@RestController
@RequestMapping("/hello")
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String sayHello(@RequestHeader(value = "User_Info") String userInfo) {
        logger.info("say hello" + userInfo);
        return "Hello " + userInfo;
    }
}
