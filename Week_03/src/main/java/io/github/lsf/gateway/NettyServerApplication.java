package io.github.lsf.gateway;

import io.github.lsf.gateway.inbound.HttpInboundServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServerApplication {
    public static Logger logger = LoggerFactory.getLogger(NettyServerApplication.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("程序启动");
        HttpInboundServer server = new HttpInboundServer(9002, "http://localhost:8088");
        server.run();
    }
}
