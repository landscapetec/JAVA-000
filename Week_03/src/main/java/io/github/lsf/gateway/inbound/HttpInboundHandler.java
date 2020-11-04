package io.github.lsf.gateway.inbound;

import io.github.lsf.gateway.outbound.httpclient4.HttpClientOutboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpInboundHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(HttpInboundHandler.class);
    private final String proxyServer;
    private final HttpClientOutboundHandler outboundHandler;

    public HttpInboundHandler(String proxyServer) {
        this.proxyServer = proxyServer;
        this.outboundHandler = new HttpClientOutboundHandler(this.proxyServer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest request = (FullHttpRequest) msg;
        logger.info("eventLoop：{}, inboudUriInfo：{}", Thread.currentThread().getName(), request.uri());

        ctx.writeAndFlush("hello");

        //outboundHandler.handle(request, ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
