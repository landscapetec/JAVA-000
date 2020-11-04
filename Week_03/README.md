### 第三周作业：手写网关
- <font color=red>第一题： 整合httpclient/okhttp </font>
本题实现：参考的是老师的样例代码，主要代码在HttpClientOutboundHandler中

- <font color=red>使用netty实现后段http访问（代替上一步骤）</font>
- <font color=red>实现过滤器 </font>
主要代码放在：HeaderHttpRequestFilter
- 声明HttpRequestFilter,处理Header头
```java
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
```
- 使用Filter，在HttpInboundHandler 添加过滤器集合 及循环调用过滤器
- <font color=red>实现路由 </font>