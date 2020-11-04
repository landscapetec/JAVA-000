### 第三周作业：手写网关
- <font color=red>第一题： 整合httpclient/okhttp </font>
本题实现：参考的是老师的样例代码，主要代码在HttpClientOutboundHandler中

- <font color=red>使用netty实现后段http访问（代替上一步骤）</font>
- <font color=red>实现过滤器 </font>
主要代码放在：HeaderHttpRequestFilter
```java
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

```
- <font color=red>实现路由 </font>