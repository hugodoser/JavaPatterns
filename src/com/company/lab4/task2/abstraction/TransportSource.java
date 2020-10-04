package com.company.lab4.task2.abstraction;

import com.company.lab1.task2.abstraction.Transport;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface TransportSource {
    List<? extends Transport> read() throws Exception;

    void rewrite(List<? extends Transport> pupils) throws IOException;

    void startReadTransaction() throws FileNotFoundException;

    void closeReadTransaction() throws IOException;

    void startWriteTransaction() throws IOException;

    void closeWriteTransaction() throws IOException;
}
