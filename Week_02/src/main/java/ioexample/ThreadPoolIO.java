package ioexample;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolIO {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(40);
        try (ServerSocket serverSocket = new ServerSocket(9003)) {
            while (true) {
                final Socket socket = serverSocket.accept();
                executorService.execute(() -> {
                    socketHandle(socket);
                });
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
