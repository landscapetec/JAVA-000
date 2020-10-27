package ioexample;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadIO {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(9002)) {
            while (true) {
                final Socket socket = serverSocket.accept();
                new Thread(() -> {
                    socketHandle(socket);
                }).start();
            }
        }
    }

    private static void socketHandle(Socket socket) {
        try {
            Thread.sleep(20);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String pageInfo = "hello,lehman";
            out.println("HTTP/1.1 200");
            out.println("Content-Type: text/html");
            out.println("Content-Length:" + pageInfo.length());
            out.println();
            out.write(pageInfo);

            out.close();
            socket.close();
        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }

    }
}
