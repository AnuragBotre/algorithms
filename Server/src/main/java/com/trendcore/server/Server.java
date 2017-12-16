package com.trendcore.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Anurag
 */
public class Server {

    public static void main(String[] args) {
        Server s = new Server();
        s.start(8080);
    }

    public void start(int portNo) {
        try {
            ServerSocket serverSocket = new ServerSocket(portNo);

            ExecutorService executorService = Executors.newFixedThreadPool(10);

            for (int i = 0; i < 2; i++) {
                Thread thread = new Thread(() -> {

                    boolean flag = true;
                    while (flag) {

                        try {
                            Socket client = serverSocket.accept();

                            System.out.println(Thread.currentThread().getName() + " "+client.getPort() + " ");

                            executorService.submit(() -> {
                                processClient(client);
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();
            }


        } catch (IOException e) {
            throw new RuntimeException("Socket Exception", e);
        }
    }

    private void processClient(Socket client) {
        //System.out.println(client.getPort() + " " + client.getLocalAddress());
        readRequest(client);
        sendResponse(client);
    }

    private void readRequest(Socket client) {
        StringBuilder builder = new StringBuilder();
        try {
            InputStream inputStream = client.getInputStream();

            int bytesRead;
            byte bytes[] = new byte[1024];

            while ((bytesRead = inputStream.read(bytes)) != -1) {
                if (bytesRead != 1024) {
                    builder.append(new String(bytes, 0, bytesRead));
                    break;
                }
                builder.append(new String(bytes));
            }

            //System.out.println(builder);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendResponse(Socket client) {
        try {
            OutputStream outputStream = client.getOutputStream();
            String s = "HTTP/1.1 200 OK \n" +
                    "Date: Thu, 24 Jan 2002 17:33:52 GMT \n" +
                    "Server: Apache/1.3.14 \n" +
                    "Last-Modified: Mon, 21 Jan 2002 22:08:33 GMT \n" +
                    "Accept-Ranges: bytes \n" +
                    "Content-Length: 9696 \n" +
                    "Connection: close \n" +
                    "Content-Type: text/html \n" +
                    " \n\n" +
                    "hello";
            outputStream.write(s.getBytes());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            close(client);
        }
    }

    private void close(Socket client) {
        if (client != null) {
            try {
                client.close();
            } catch (IOException e) {

            }
        }
    }
}
