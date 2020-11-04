package io.github.lsf.gateway.filter;

import io.github.lsf.gateway.outbound.httpclient4.OutboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeaderHttpRequestFilter implements HttpRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(HeaderHttpRequestFilter.class);
    private final String name;

    public HeaderHttpRequestFilter(String name) {
        this.name = name;
    }

    @Override
    public void filter(FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {
        String userInfo = "";
        if (fullRequest.headers().contains("User_Info")) {
            userInfo = fullRequest.headers().get("User_Info") + "_" + name;
        } else {
            userInfo = "Big Head Son,Small Head Father" + "_" + name;
        }
        logger.info("最新user_info：{}", userInfo);
        fullRequest.headers().set("User_Info", userInfo);
    }
}
