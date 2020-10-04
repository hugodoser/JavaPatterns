package com.company.lab1.task3;

import com.company.lab1.task2.*;
import com.company.lab1.task2.abstraction.Transport;
import com.company.lab1.task2.exceptions.*;
import com.company.lab1.task2.factory.AutoFactory;

public class Run {
    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException, CloneNotSupportedException {
        Motorcycle kawasaki = new Motorcycle("kawasaki", 5);

        kawasaki.addNewModel("ninja 400", 4999.9);
        kawasaki.editPriceByModelName("ninja 400", 5000);
        kawasaki.editNameModel("ninja 400", "ninja 300");

        Transport transportCar = new AutoFactory().createInstance("ford", 4);
        Car ford = (Car) transportCar;

        Motorcycle kawasakiClone = (Motorcycle)kawasaki.clone();
        System.out.println(kawasaki == kawasakiClone);
        System.out.println(kawasaki.getMark() == kawasakiClone.getMark());

        Car fordClone = (Car)ford.clone();
        System.out.println(ford == fordClone);
        System.out.println(ford.getMark() == fordClone.getMark());
    }
}