package com.company.lab4.task2.implementation;

import com.company.lab1.task2.Car;
import com.company.lab1.task2.abstraction.Transport;
import com.company.lab4.task2.abstraction.TransportSource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializeTransportSource implements TransportSource {
    private String path;
    private OutputStream out;
    private InputStream in;

    public SerializeTransportSource(String path) {
        if (path == null)
            throw new NullPointerException("Data source path = null");
        this.path = path;
    }

    public List<? extends Transport> read() throws IOException, ClassNotFoundException {
        ObjectInputStream deserObj = new ObjectInputStream(in);
        return (ArrayList<Car>)deserObj.readObject();
    }

    public void rewrite(List<? extends Transport> transports) throws IOException {
        ObjectOutputStream serObj = new ObjectOutputStream(out);
        serObj.writeObject(transports);
    }

    public void startReadTransaction() throws FileNotFoundException {
        in = new FileInputStream(path);
    }

    public void closeReadTransaction() throws IOException {
        in.close();
    }

    public void startWriteTransaction() throws IOException {
        out = new FileOutputStream(path);
    }

    public void closeWriteTransaction() throws IOException {
        out.flush();
        out.close();
    }
}