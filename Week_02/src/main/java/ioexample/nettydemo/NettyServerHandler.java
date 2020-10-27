package ioexample.nettydemo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class NettyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!",
//                CharsetUtil.UTF_8));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        System.out.println("Client received: " + byteBuf.toString(CharsetUtil.UTF_8));
        Thread.sleep(20);
        String pageInfo = "hello,lehman";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("HTTP/1.1 200\n");
        stringBuilder.append("Content-Type: text/html\n");
        stringBuilder.append("Content-Length:" + pageInfo.length() + "\n");
        stringBuilder.append("\n");
        stringBuilder.append(pageInfo);
        System.out.println("=========================");
        System.out.println(stringBuilder.toString());
        System.out.println("=========================");

        ByteBuf resp = Unpooled.copiedBuffer(stringBuilder.toString().getBytes());
        channelHandlerContext.write(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("-------刷入流---------");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        System.out.println("-------关闭流---------");
        ctx.close();
    }
}
