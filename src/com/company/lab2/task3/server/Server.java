package com.company.lab2.task3.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void start() {

        System.out.println("Это сервер");

        ServerSocket server = null;
        try {
            server = new ServerSocket(5000);
        } catch (IOException e) {
            System.out.println("Не удалось прослушать порт: 5000");
            System.exit(-1);
        }
        ExecutorService executor = Executors.newFixedThreadPool(5);

        while (true) {
            try {
                System.out.println("\nОжидание клиентов...");
                Runnable childProcess = new ChildProcess(server.accept());
                executor.submit(childProcess);
            } catch (IOException e) {
                System.out.println("Не удалось принять");
                System.exit(-1);
            }
        }
    }
}