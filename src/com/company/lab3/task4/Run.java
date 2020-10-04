package com.company.lab3.task4;

import com.company.lab1.task2.StaticTools;
import com.company.lab1.task2.exceptions.DuplicateModelNameException;

public class Run {

    public static void main(String[] args) throws DuplicateModelNameException {
        Car mazda = new Car("Mazda", 4);

        System.out.println("Before");
        StaticTools.printModelsAndPrices(mazda);

        Car.CarMemento memento = mazda.createMemento();

        System.out.println("After add");
        mazda.addNewModel("3", 400);
        StaticTools.printModelsAndPrices(mazda);

        System.out.println("After set memento");
        mazda.setMemento(memento);
        StaticTools.printModelsAndPrices(mazda);
    }
}
