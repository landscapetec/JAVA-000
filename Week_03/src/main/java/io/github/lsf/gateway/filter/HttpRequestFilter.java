package io.github.lsf.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public interface HttpRequestFilter {
    //过滤器
    void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx);
}
