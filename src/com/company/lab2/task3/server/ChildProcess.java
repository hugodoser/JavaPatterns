package com.company.lab2.task3.server;

import java.io.*;
import java.net.Socket;

public class ChildProcess implements Runnable {
    private Socket client;
    private InputStream in = null;
    private DataOutputStream out = null;

    public ChildProcess(Socket client){
        this.client = client;
    }

    @Override
    public void run() {
        try {
            in = client.getInputStream();
            out = new DataOutputStream(client.getOutputStream());
            System.out.println("Child Process: NetworkProcessor подключен");
        } catch (IOException e) {
            System.out.println("Child Process: Не удалось принять");
            System.exit(-1);
        }
        try {
            while (!client.isClosed() && in.available() != 0) {
                DataInputStream input = new DataInputStream(in);
                double first = input.readDouble();
                double second = input.readDouble();
                double result = first * second;
                out.writeDouble(result);
                System.out.println("Child Process: Отправил: " + result);
            }
        } catch (IOException e){
            System.out.println("Child Process: Испорченные данные");
        }
        finally {
            close();
        }
    }

    private void close() {
        try {
            out.close();
            in.close();
            client.close();
            System.out.println("Child Process: NetworkProcessor был отключен");
        } catch (IOException e){
            System.out.println("Child Process: NetworkProcessor не был отключен");
        }
    }
}