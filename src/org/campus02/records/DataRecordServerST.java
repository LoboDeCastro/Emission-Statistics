package org.campus02.records;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DataRecordServerST {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1111)) {
            System.out.println("waiting for clients...");
            try (Socket client = serverSocket.accept()) {
                System.out.println("client connected");
                new ReportLogic(client).run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
