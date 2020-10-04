package com.company.lab2.task2;

import com.company.lab1.task2.Motorcycle;
import com.company.lab1.task2.StaticTools;
import com.company.lab1.task2.abstraction.Transport;
import com.company.lab1.task2.exceptions.*;

public class Run {

    public static void main(String[] args)throws DuplicateModelNameException, NoSuchModelNameException {
        Motorcycle yamaha = new Motorcycle("kawasaki", 5);

        yamaha.addNewModel("ninja 400", 4999.99);
        yamaha.editPriceByModelName("ninja 400", 5000);
        yamaha.editNameModel("ninja 400", "ninja 300");

        Transport decoratedTransport = new StaticTools().synchronizedTransport(yamaha);
        StaticTools.printModelsAndPrices(decoratedTransport);
    }
}