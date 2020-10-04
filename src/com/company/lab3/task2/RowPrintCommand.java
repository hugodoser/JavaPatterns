package com.company.lab3.task2;

import com.company.lab1.task2.abstraction.Transport;

import java.io.*;

public class RowPrintCommand implements PrintCommand {
    @Override
    public void execute(Transport transport, String path) throws FileNotFoundException {
        OutputStream out = new FileOutputStream(path);
        try (PrintWriter writer = new PrintWriter(out)) {
            writer.write(transportToRowString(transport));
            writer.flush();
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
