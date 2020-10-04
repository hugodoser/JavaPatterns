package com.company.lab1.task2;

import com.company.lab1.task2.abstraction.Transport;
import com.company.lab1.task2.abstraction.TransportFactory;
import com.company.lab1.task2.exceptions.*;
import com.company.lab1.task2.factory.AutoFactory;
import com.company.lab2.task2.TransportDecorator;

public class StaticTools {

    private static TransportFactory transportFactory = new AutoFactory();

    public static TransportFactory getTransportFactory() {
        return transportFactory;
    }

    public static void setTransportFactory(TransportFactory factory) throws DuplicateModelNameException {
        transportFactory = factory;
    }

    public static Transport createInstnce(String  name, int size) throws DuplicateModelNameException {
       return transportFactory.createInstance(name,size);
    }

    public static double getAveragePrice(Transport transport) {
        double[] prices = transport.getArrayOfModelPrice();
        double average = 0;
        if (prices.length != 0) {
            for (int i = 0; i < prices.length; i++) {
                average += prices[i];
            }
            average /= prices.length;
        } else {
            return -1;
        }
        return average;
    }

    public static void printModelsAndPrices(Transport transport){
        double[] prices = transport.getArrayOfModelPrice();
        String[] names = transport.getArrayOfModelNames();

        int size = transport.getArrayModelLength();

        System.out.println("Марка: "+transport.getMark());
        System.out.println("Количество моделей: "+size);

        for (int i = 0; i < size; i++) {
            String n = names[i];
            double p = prices [i];
            System.out.println(String.format( (i+1) + " Модель: %s, стоимость: %s",n,p));
        }
    }

    public Transport synchronizedTransport (Transport t){
        return new TransportDecorator(t);
    }
}