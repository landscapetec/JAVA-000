package ioexample.nettydemo.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.logging.Logger;

public class NettyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private static final Logger logger = Logger.getLogger(NettyClientHandler.class.getName());
    //    private final ByteBuf firstMessage;
    private int counter;
    private byte[] req;

    public NettyClientHandler() {
        req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();

//        byte[] req = "QUERY TIME ORDER".getBytes();
//        firstMessage = Unpooled.buffer(req.length);
//        firstMessage.writeBytes(req);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf messge = null;
        for (int i = 0; i < 100; i++) {
            messge = Unpooled.buffer(req.length);
            messge.writeBytes(req);
            ctx.writeAndFlush(messge);
        }
        //        ctx.writeAndFlush(firstMessage);
    }



//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        String body = (String) msg;
//        System.out.println("The time Server receive order:" + body
//                + "  ; the counter is :" + ++counter);
//    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        System.out.println("i think is the wrong method");
        //        byte[] req=new byte[byteBuf.readableBytes()];
//        byteBuf.readBytes(req);
//        String body=new String(req,"UTF-8");
//        System.out.println("now is :"+body+"; the counter is :"+ ++counter);
//
//        //        ByteBuf buf = (ByteBuf) byteBuf;
////        byte[] req = new byte[buf.readableBytes()];
////        buf.readBytes(req);
////        String body = new String(req, "UTF-8");
////        System.out.println("Now is " + body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warning("Unexpected exception from downstream :" + cause.getMessage());
        ctx.close();
    }
}
