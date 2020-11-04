package org.lsf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MockServerApplication {
    private static final Logger logger = LoggerFactory.getLogger(MockServerApplication.class);

    public static void main(String[] args) {
        logger.info("asdfasdfasdfasdf");
        SpringApplication.run(MockServerApplication.class, args);
    }
}
