package com.company.lab2.task3.client;

import java.io.IOException;

public class Run {

    public static void main(String[] args) throws IOException {
        Processor processor = new ProxyProcessor();
        double first = 4;
        double second = 5;
        double result = processor.multiple(first, second);
        System.out.println("Результат умножения " + first + " на " + second + ": " + result);
    }
}
