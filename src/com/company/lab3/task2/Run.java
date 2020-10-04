package com.company.lab3.task2;

import com.company.lab3.task2.Car;

import java.io.FileNotFoundException;

public class Run {

    public static void main(String[] args) throws FileNotFoundException {
        Car mazda = new Car("Mazda", 4);
        PrintCommand columnPrintCommand = new ColumnPrintCommand();
        mazda.setPrintCommand(columnPrintCommand);
        mazda.print("mazdaColumn.txt");

        PrintCommand rowPrintCommand = new RowPrintCommand();
        mazda.setPrintCommand(rowPrintCommand);
        mazda.print("mazdaRow.txt");
    }
}
