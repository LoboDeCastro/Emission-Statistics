package org.campus02.records;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DataRecordServerMT {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1111)) {
            System.out.println("waiting for clients...");
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("client connected");
                new Thread(new ReportLogic(client)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
