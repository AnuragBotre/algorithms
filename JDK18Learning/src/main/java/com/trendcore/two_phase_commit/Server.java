package com.trendcore.two_phase_commit;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static final int PORT_NUMBER = 1111;
    boolean closed = false, inputFromAll = false;
    List<ClientThread> t;
    List<String> data;

    Server() {
        t = new ArrayList<>();
        data = new ArrayList<>();
    }

    public static void main(String args[]) {
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        int port_number = PORT_NUMBER;
        Server ser = new Server();
        try {
            serverSocket = new ServerSocket(port_number);
        } catch (IOException e) {
            System.out.println(e);
        }
        while (!ser.closed) {
            try {
                clientSocket = serverSocket.accept();
                ClientThread th = new ClientThread(ser, clientSocket);
                (ser.t).add(th);
                System.out.println("\nNow Total clients are : " + (ser.t).size());
                (ser.data).add("NOT_SENT");
                th.start();
            } catch (IOException e) {
            }
        }

        try {
            serverSocket.close();
        } catch (Exception e1) {
        }
    }

}
