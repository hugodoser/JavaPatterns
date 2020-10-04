package com.company.lab2.task3.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkProcessor implements Processor {
    public static String HOST_NAME = "localhost";
    public static int PORT = 5000;

    private Socket client = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;

    public NetworkProcessor() {
        open();
    }

    public double multiple(double first, double second) throws IOException {
        double result = Double.POSITIVE_INFINITY;
        try {
            out.writeDouble(first);
            out.writeDouble(second);
            result = in.readDouble();
            System.out.println("NetworkProcessor получил " + result);
            return result;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        finally {
            close();
        }
        return result;
    }

    private void open() {
        System.out.println("Подключение к " + HOST_NAME);
        try {
            client = new Socket(HOST_NAME, PORT);
            out = new DataOutputStream(client.getOutputStream());
            in = new DataInputStream(client.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Неопознанный хост с именем " + HOST_NAME);
        } catch (IOException e) {
            System.err.println("Не удалось получить I/O при подключении к " + HOST_NAME);
        }
    }

    public void close() throws IOException {
        out.close();
        in.close();
        client.close();
        System.out.println("NetworkProcessor отключился от " + HOST_NAME);
    }
}

