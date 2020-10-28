package ioexample.nettydemo.client;

public class NettyClientApplication {
    public static void main(String[] args) {
        new NettyClient().connect(9005,"localhost");
    }
}
