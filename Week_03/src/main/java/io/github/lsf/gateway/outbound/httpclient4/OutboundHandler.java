package io.github.lsf.gateway.outbound.httpclient4;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public interface OutboundHandler {

    void handle(FullHttpRequest request, ChannelHandlerContext ctx);
}
