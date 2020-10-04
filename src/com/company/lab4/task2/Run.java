package com.company.lab4.task2;

import com.company.lab1.task2.Car;
import com.company.lab1.task2.Motorcycle;
import com.company.lab1.task2.abstraction.Transport;
import com.company.lab4.task2.abstraction.TransportSource;
import com.company.lab4.task2.implementation.SerializeTransportSource;
import com.company.lab4.task2.implementation.SimpleTransportSource;

import java.util.ArrayList;
import java.util.List;

public class Run {

    public static List<Transport> createTransportList() {
        List<Transport> transports = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            if (i % 2 == 0)
                transports.add(new Car("car", 3));
            else transports.add(new Motorcycle("motorcycle", 4));
        }
        return transports;
    }

    public static void println(List<? extends Transport> transports) {
        transports.forEach(System.out::println);
    }

    public static void main(String[] args) throws Exception {
        TransportSource serializeTransportSource = new SerializeTransportSource("serializeTransport.ser");
        TransportSource simpleTransportSource = new SimpleTransportSource("simpleTransport.txt");

        TransportDao serializeDao = new TransportDao(serializeTransportSource);
        TransportDao simpleDao = new TransportDao(simpleTransportSource);

        List<Transport> transports = createTransportList();
        serializeDao.updateTransports(transports);
        simpleDao.updateTransports(transports);

        System.out.println("Созданные для сохранения: ");
        println(transports);
        System.out.println();

        System.out.println("Считанные из сериализованного файла: ");
        println(serializeDao.getTransports());
        System.out.println();

        System.out.println("Считанные из текстового файла: ");
        println(simpleDao.getTransports());
        System.out.println();
    }
}