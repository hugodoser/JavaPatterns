package com.company.lab4.task2.implementation;

import com.company.lab1.task2.abstraction.Transport;
import com.company.lab1.task2.exceptions.DuplicateModelNameException;
import com.company.lab4.task2.TransportFactory;
import com.company.lab4.task2.abstraction.TransportSource;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleTransportSource implements TransportSource {
    private String path;
    private Writer out;
    private Reader in;

    public SimpleTransportSource(String path) {
        if (path == null)
            throw new IllegalArgumentException("Data source path = null");
        this.path = path;
    }

    public List<? extends Transport> read() throws DuplicateModelNameException, ParseException {
        Scanner read = new Scanner(in);
        int arraySize = Integer.parseInt(read.nextLine());
        List<Transport> transports = new ArrayList<>();

        while (arraySize > 0) {
            String nameClass = read.nextLine();
            String name = read.nextLine();
            int count = Integer.parseInt(read.nextLine());

            Transport transport = TransportFactory.createReflectiveTransport(name, nameClass);
            for (int i = 0; i < count; i++) {
                String modelName = read.nextLine();

                NumberFormat format = NumberFormat.getInstance();
                Number number = format.parse(read.nextLine());
                double price = number.doubleValue();

                transport.addNewModel(modelName, price);
            }
            transports.add(transport);
            arraySize--;
        }
        return transports;
    }

    public void rewrite(List<? extends Transport> transports) throws IOException {
        PrintWriter writer = new PrintWriter(out);
        writer.printf("%d%n", transports.size());
        for (Transport transport : transports) {
            writer.printf("%s%n%s%n%d%n", transport.getClass().getName(), transport.getMark(), transport.getArrayModelLength());
            String[] modelNames = transport.getArrayOfModelNames();
            double[] modelPrices = transport.getArrayOfModelPrice();
            for (int i = 0; i < transport.getArrayModelLength(); i++) {
                writer.printf("%s%n%f%n", modelNames[i], modelPrices[i]);
            }
        }
    }

    public void startReadTransaction() throws FileNotFoundException {
        in = new FileReader(path);
    }

    public void closeReadTransaction() throws IOException {
        in.close();
    }

    public void startWriteTransaction() throws IOException {
        out = new FileWriter(path);
    }

    public void closeWriteTransaction() throws IOException {
        out.flush();
        out.close();
    }
}