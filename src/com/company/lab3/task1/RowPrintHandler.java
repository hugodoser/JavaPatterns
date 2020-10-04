package com.company.lab3.task1;

import com.company.lab1.task2.abstraction.Transport;

import java.io.FileWriter;
import java.io.IOException;

public class RowPrintHandler extends PrintHandler {
    public RowPrintHandler() { }

    public RowPrintHandler(PrintHandler handler) {
        super(handler);
    }

    @Override
    public void print(String path, Transport transport) {
        if (transport.getArrayModelLength() <= 3) {
            printRow(path, transport);
        } else if (handler != null) {
            handler.print(path, transport);
        }
    }

    private void printRow(String path, Transport transport) {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(transportToRowString(transport));
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private String transportToRowString(Transport transport) {
        StringBuilder sb = new StringBuilder();
        sb.append(transport.getMark()).append(" ");

        String[] modelNames = transport.getArrayOfModelNames();
        double[] modelPrices = transport.getArrayOfModelPrice();

        for (int i = 0; i < transport.getArrayModelLength(); i++) {
            sb.append(modelNames[i]).append(" ").append(modelPrices[i]).append(" ");
        }
        return sb.toString();
    }
}
