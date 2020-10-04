package com.company.lab3.task8;

import java.util.ArrayList;

public class Run {

    public static void main(String[] args) {
        Car mazda = new Car("Mazda", 3);
        Motorcycle honda = new Motorcycle("Honda", 4);

        Visitor visitor = new PrintVisitor();

        mazda.accept(visitor);
        System.out.println("\n");

        honda.accept(visitor);
        System.out.println("\n");

        TransportPark transportPark = new TransportPark();
        transportPark.add(mazda);
        transportPark.add(honda);
        transportPark.accept(visitor);
    }
}
