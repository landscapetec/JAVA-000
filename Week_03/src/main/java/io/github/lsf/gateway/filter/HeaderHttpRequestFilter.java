package io.github.lsf.gateway.filter;

import io.github.lsf.gateway.outbound.httpclient4.OutboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class HeaderHttpRequestFilter implements HttpRequestFilter {
    private final OutboundHandler outboundHandler;

    public HeaderHttpRequestFilter(OutboundHandler outboundHandler) {
        this.outboundHandler = outboundHandler;
    }

    @Override
    public void filter(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {
        FullHttpRequest filterFullRequest = fullRequest;
        filterFullRequest.headers().add("User_Info", "Big Head Son,Small Head Father");
        outboundHandler.handle(filterFullRequest, ctx);
    }
}
