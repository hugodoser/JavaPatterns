package com.company.lab3.task8;

public class StaticTools {

    private StaticTools() {}

    public static String transportToRowString(Transport transport) {
        StringBuilder sb = new StringBuilder();
        sb.append(transport.getMark()).append(" ");

        String[] modelNames = transport.getArrayOfModelNames();
        double[] modelPrices = transport.getArrayOfModelPrice();

        for (int i = 0; i < transport.getArrayModelLength(); i++) {
            sb.append(modelNames[i]).append(" ").append(modelPrices[i]).append(" ");
        }
        return sb.toString();
    }

    public static String transportToColumnString(Transport transport) {
        StringBuilder sb = new StringBuilder();
        sb.append(transport.getMark()).append("\n");

        String[] modelNames = transport.getArrayOfModelNames();
        double[] modelPrices = transport.getArrayOfModelPrice();

        for (int i = 0; i < transport.getArrayModelLength(); i++) {
            sb.append(modelNames[i]).append("\n").append(modelPrices[i]).append("\n");
        }
        return sb.toString();
    }
}
