package ioexample.nettydemo;

public class NettyApplication {
    public static void main(String[] args) throws InterruptedException {
        new NettyServer().bind(9005);
    }
}
