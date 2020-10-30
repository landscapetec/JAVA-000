package ioexample.nettydemo.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.Date;

public class NettyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int counter;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!",
//                CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 读客户端写入流
        String body = (String) msg;
        System.out.println("The time Server receive order:" + body
                + "  ; the counter is :" + ++counter);

        // 拼接自负写回客户端
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)
                ? new Date(System.currentTimeMillis()).toString()
                : "BAD ORDERT";
        currentTime = currentTime + System.getProperty("line separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        Thread.sleep(20);
//        System.out.println("Client received: " + byteBuf.toString(CharsetUtil.UTF_8));
//        String pageInfo = "hello,lehman";
//
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("HTTP/1.1 200\n");
//        stringBuilder.append("Content-Type: text/html\n");
//        stringBuilder.append("Content-Length:" + pageInfo.getBytes("UTF-8").length + "\n");
//        stringBuilder.append("\n");
//        stringBuilder.append(pageInfo.getBytes());
//        System.out.println("=========================");
//        System.out.println(stringBuilder.toString());
//        System.out.println("=========================");
//
//        ByteBuf resp = Unpooled.copiedBuffer(stringBuilder.toString().getBytes());

//        byte[] req = new byte[byteBuf.readableBytes()];
//        byteBuf.readBytes(req);
//        String body = new String(req, "UTF-8").substring(0, req.length
//                - System.getProperty("line.separator").length());
//
//        System.out.println("The time Server receive order:" + body
//                + "  ; the counter is :" + ++counter);
//        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString()
//                : "BAD ORDERT";
//        currentTime = currentTime + System.getProperty("line separator");
//
//        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
//        channelHandlerContext.write(resp);
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
