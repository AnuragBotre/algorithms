package com.trendcore.two_phase_commit;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class ClientThread extends Thread {

    DataInputStream is = null;
    String line;
    String destClient = "";
    String name;
    PrintStream os = null;
    Socket clientSocket = null;
    String clientIdentity;
    Server ser;

    public ClientThread(Server ser, Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.ser = ser;
    }

    public void run() {
        try {
            is = new DataInputStream(clientSocket.getInputStream());
            os = new PrintStream(clientSocket.getOutputStream());
            os.println("Enter your name.");
            name = is.readLine();
            clientIdentity = name;
            os.println("Welcome " + name + " to this 2 Phase Application.\nYou will receive a vote Request now...");
            os.println("VOTE_REQUEST\nPlease enter COMMIT or ABORT to proceed : ");
            for (int i = 0; i < (ser.t).size(); i++) {
                if ((ser.t).get(i) != this) {
                    ((ser.t).get(i)).os.println("---A new user " + name + " entered the Appilcation---");
                }
            }
            while (true) {
                line = is.readLine();
                if (line.equalsIgnoreCase("ABORT")) {
                    System.out.println("\nFrom '" + clientIdentity + "' : ABORT\n\nSince aborted we will not wait for inputs from other clients.");
                    System.out.println("\nAborted....");
                    for (int i = 0; i < (ser.t).size(); i++) {
                        ((ser.t).get(i)).os.println("GLOBAL_ABORT");
                        ((ser.t).get(i)).os.close();
                        ((ser.t).get(i)).is.close();
                    }
                    break;
                }
                if (line.equalsIgnoreCase("COMMIT")) {
                    System.out.println("\nFrom '" + clientIdentity + "' : COMMIT");
                    if ((ser.t).contains(this)) {
                        (ser.data).set((ser.t).indexOf(this), "COMMIT");
                        for (int j = 0; j < (ser.data).size(); j++) {
                            if (!(((ser.data).get(j)).equalsIgnoreCase("NOT_SENT"))) {
                                ser.inputFromAll = true;
                                continue;
                            } else {
                                ser.inputFromAll = false;
                                System.out.println("\nWaiting for inputs from other clients.");
                                break;
                            }
                        }
                        if (ser.inputFromAll) {
                            System.out.println("\n\nCommited....");
                            for (int i = 0; i < (ser.t).size(); i++) {
                                ((ser.t).get(i)).os.println("GLOBAL_COMMIT");
                                ((ser.t).get(i)).os.close();
                                ((ser.t).get(i)).is.close();
                            }
                            break;
                        }
                    }//if t.contains
                }//commit
            }//while
            ser.closed = true;
            clientSocket.close();
        } catch (IOException e) {

        }
    }
}
