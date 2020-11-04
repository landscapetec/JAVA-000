package io.github.lsf.gateway.outbound.httpclient4;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class HttpClientOutboundHandler {

    private final String proxyServerUrl;
    private final ExecutorService proxyService;

    public HttpClientOutboundHandler(String proxyServerUrl) {
        this.proxyServerUrl = proxyServerUrl;
        int coreSize=Runtime.getRuntime().availableProcessors()*2;
        int queueSize=2048;
        RejectedExecutionHandler rejectedExecutionHandler=new ThreadPoolExecutor.CallerRunsPolicy();
        this.proxyService=new ThreadPoolExecutor()
    }

    public void handle(FullHttpRequest request, ChannelHandlerContext ctx) {

    }
}
