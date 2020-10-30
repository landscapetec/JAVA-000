package ioexample;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

public class SimpleIO {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(9001)) {
            while (true) {
                final Socket socket = serverSocket.accept();
                socketHandle(socket);
            }
        }
    }

    private static void socketHandle(Socket socket) {
        try {
            Thread.sleep(20);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String pageInfo="hello,lehman";
            out.println("HTTP/1.1 200");
            out.println("Content-Type: text/html");
            out.println("Content-Length:"+pageInfo.length());
            out.println();
            out.write(pageInfo);

            out.close();
            socket.close();
        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
