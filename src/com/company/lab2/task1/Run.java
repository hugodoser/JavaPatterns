package com.company.lab2.task1;

import java.io.IOException;
import java.util.Scanner;

public class Run {

    public static void main(String[] args) throws IOException {
        StringWriter stringWriter = new StringWriterAdapter(System.out);

        System.out.print("Введите количество строк: ");
        Scanner read = new Scanner(System.in);

        int count = read.nextInt();
        read.nextLine();

        String[] lines = new String[count];
        for (int i = 0; i < count; i++) {
            System.out.print("Введите " + (i + 1) + " строку: ");
            lines[i] = read.nextLine();
        }

        System.out.println("Вывод адаптера: ");
        stringWriter.write(lines);
    }
}