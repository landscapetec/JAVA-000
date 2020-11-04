package io.github.lsf.gateway.inbound;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpInboundServer {
    private static Logger logger = LoggerFactory.getLogger(HttpInboundServer.class);
    private final int port;
    private final String proxyServer;

    public HttpInboundServer(int port, String proxyServer) {
        this.port = port;
        this.proxyServer = proxyServer;
    }

    public void run() throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup(3);
        EventLoopGroup works = new NioEventLoopGroup(30);
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024)     //接收buf大小
                    .option(ChannelOption.SO_SNDBUF, 32 * 1024)     //发送buf大小
                    .option(EpollChannelOption.SO_REUSEPORT, true)  //
                    .childOption(ChannelOption.SO_KEEPALIVE, true)  //保持连接
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            serverBootstrap.group(boss, works)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new HttpInboundInitialzer(this.proxyServer));

            logger.info("开启netty http服务器，监听地址和端口为 http://127.0.0.1:" + port + '/');

            Channel channel = serverBootstrap.bind(port).sync().channel();
            channel.closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            works.shutdownGracefully();
        }
    }
}
