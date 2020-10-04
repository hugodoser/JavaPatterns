package com.company.lab3.task1;

import com.company.lab1.task2.Motorcycle;
import com.company.lab1.task2.abstraction.Transport;

public class Run {

    public static void main(String[] args) {
        Transport yamahaColumn = new Motorcycle("yamaha", 4);
        Transport hondaRow = new Motorcycle("honda", 3);

        RowPrintHandler rowPrintHandler = new RowPrintHandler();
        ColumnPrintHandler columnPrintHandler = new ColumnPrintHandler();

        rowPrintHandler.setChainHandler(columnPrintHandler);

        rowPrintHandler.print("yamahaColumn.txt", yamahaColumn);
        rowPrintHandler.print("hondaRow.txt", hondaRow);
    }
}