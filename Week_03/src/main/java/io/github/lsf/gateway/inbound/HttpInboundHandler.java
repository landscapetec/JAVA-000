package io.github.lsf.gateway.inbound;

import io.github.lsf.gateway.filter.HeaderHttpRequestFilter;
import io.github.lsf.gateway.filter.HttpRequestFilter;
import io.github.lsf.gateway.outbound.httpclient4.HttpClientOutboundHandler;
import io.github.lsf.gateway.outbound.httpclient4.OutboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class HttpInboundHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(HttpInboundHandler.class);
    private final String proxyServer;
    private final OutboundHandler outboundHandler;
    private List<HttpRequestFilter> filters;

    public HttpInboundHandler(String proxyServer) {
        this.proxyServer = proxyServer;
        this.outboundHandler = new HttpClientOutboundHandler(this.proxyServer);
        this.filters = new ArrayList<>();
        this.filters.add(new HeaderHttpRequestFilter("f1"));
        this.filters.add(new HeaderHttpRequestFilter("f2"));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest request = (FullHttpRequest) msg;
        logger.info("eventLoop：{}, inboudUriInfo：{}", Thread.currentThread().getName(), request.uri());

        for (HttpRequestFilter filter : filters) {
            filter.filter(request, ctx);
        }

        outboundHandler.handle(request, ctx);
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
