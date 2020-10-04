package com.company.lab3.task3;

public class Run {

    public static void main(String[] args) {
        Car mazda = new Car("Mazda", 4);
        for (Car.Register register : mazda) {
            System.out.println(register);
        }
    }
}
