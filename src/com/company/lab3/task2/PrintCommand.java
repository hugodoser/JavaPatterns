package com.company.lab3.task2;

import com.company.lab1.task2.abstraction.Transport;

import java.io.FileNotFoundException;

public interface PrintCommand {
    void execute(Transport transport, String path) throws FileNotFoundException;
}
