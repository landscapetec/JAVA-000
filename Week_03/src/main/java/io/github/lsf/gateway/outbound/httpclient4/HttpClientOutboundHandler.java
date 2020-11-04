package io.github.lsf.gateway.outbound.httpclient4;

import com.sun.deploy.net.HttpUtils;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.*;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpClientOutboundHandler implements OutboundHandler{

    private final String proxyServerUrl;
    private final ExecutorService proxyService;
    private CloseableHttpAsyncClient httpclient;

    public HttpClientOutboundHandler(String proxyServerUrl) {
        this.proxyServerUrl = proxyServerUrl;
        //线程池处理
        int coreSize = Runtime.getRuntime().availableProcessors() * 2;
        int queueSize = 2048;
        int keepAliveTime = 1000;
        RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();
        this.proxyService = new ThreadPoolExecutor(coreSize, coreSize,
                keepAliveTime, TimeUnit.MICROSECONDS,
                new ArrayBlockingQueue<>(queueSize),
                new ThreadNameFactory("proxy"),
                rejectedExecutionHandler);

        IOReactorConfig ioConfig = IOReactorConfig.custom()
                .setConnectTimeout(1000)
                .setSoTimeout(1000)
                .setIoThreadCount(coreSize)
                .setRcvBufSize(32 * 1024)
                .build();

        try {
            httpclient = HttpAsyncClients.custom()
                    .setMaxConnTotal(40)
                    .setMaxConnPerRoute(8)
                    .setDefaultIOReactorConfig(ioConfig)
                    .setKeepAliveStrategy((response, context) -> 6000)
                    .build();
            httpclient.start();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
        }

    }

    public void handle(final FullHttpRequest request, final ChannelHandlerContext ctx) {
        String url = this.proxyServerUrl + request.uri();
        proxyService.submit(() -> futureTask(request, ctx, url));
    }

    public void futureTask(final FullHttpRequest request, final ChannelHandlerContext ctx, final String url) {
        final HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
        httpGet.setHeader("User_Info",request.headers().get("User_Info"));
        httpclient.execute(httpGet, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(final HttpResponse httpResponse) {
                try {
                    handleResponse(request, ctx, httpResponse);
                } catch (Exception exception) {
                    exception.printStackTrace();
                } finally {

                }
            }

            @Override
            public void failed(Exception e) {
                httpGet.abort();
                e.printStackTrace();
            }

            @Override
            public void cancelled() {
                httpGet.abort();
            }
        });
    }

    private void handleResponse(FullHttpRequest request, ChannelHandlerContext ctx, HttpResponse httpResponse) {
        FullHttpResponse response = null;
        try {
            byte[] body = EntityUtils.toByteArray(httpResponse.getEntity());
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));

            response.headers().add("Content-Length", body.length);
            response.headers().add("Content-Type", "application/json");
        } catch (IOException exception) {
            exception.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            exceptionCaught(ctx, exception);
        } finally {
            if (request != null) {
                if (HttpUtil.isKeepAlive(request)) {
                    ctx.write(response);
                } else {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                }
            }
            ctx.flush();
        }
    }

    private void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable) {
        throwable.printStackTrace();
        ctx.close();
    }
}
