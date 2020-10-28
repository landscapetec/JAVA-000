package ioexample.nettydemo.server;

public class NettyServerApplication {
    public static void main(String[] args) throws InterruptedException {
        new NettyServer().bind(9005);
    }
}
