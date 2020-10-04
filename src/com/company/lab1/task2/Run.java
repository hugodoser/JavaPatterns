package com.company.lab1.task2;

import com.company.lab1.task2.abstraction.Transport;
import com.company.lab1.task2.abstraction.TransportFactory;
import com.company.lab1.task2.exceptions.*;
import com.company.lab1.task2.factory.AutoFactory;
import com.company.lab1.task2.factory.MotoFactory;

public class Run {
    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException, CloneNotSupportedException {

        Motorcycle kawasaki = new Motorcycle("kawasaki", 5);

        kawasaki.addNewModel("ninja 400", 4999.99);
        double[] prices = kawasaki.getArrayOfModelPrice();
        kawasaki.editPriceByModelName("ninja 400", 5000);
        System.out.println(kawasaki.getPriceByModelName("ninja 400"));

        System.out.println(StaticTools.getAveragePrice(kawasaki));
        StaticTools.printModelsAndPrices(kawasaki);

        StaticTools.setTransportFactory(new MotoFactory());
        TransportFactory transportFactory = StaticTools.getTransportFactory();
        Motorcycle motorcycle = (Motorcycle)transportFactory.createInstance("harley davidson", 5);

        Transport transport = StaticTools.createInstnce("impala 1967", 3);
        System.out.println(transport.getClass());


//        Transport transportMoto = new MotoFactory().createInstance("vanvan 200", 5);
//        Motorcycle suzuki = (Motorcycle) transportMoto;
//
//        System.out.println("\nTest clone motorcycle");
//
//        Motorcycle kawasakiClone = (Motorcycle)kawasaki.clone();
//        kawasakiClone.editNameModel("ninja 400", "ninja 300");
//        kawasakiClone.deleteModelByNameAndPrice("motorcycle_0", 5917.0);
//        StaticTools.printModelsAndPrices(kawasaki);
//        StaticTools.printModelsAndPrices(kawasakiClone);

        Transport transportCar = new AutoFactory().createInstance("mustang 2019", 4);
        System.out.println(transportCar.getClass());
        Car ford = (Car) transportCar;
        StaticTools.printModelsAndPrices(ford);

        System.out.println("\nAdd new car\n");

        ford.addNewModel("car_add", 67.0);

        System.out.println("\nTest clone car\n");

        Car fordClone = (Car)ford.clone();
        StaticTools.printModelsAndPrices(fordClone);
        fordClone.editNameModel("car_add", "car_del");
        fordClone.editPriceByModelName("car_del", 88);
        StaticTools.printModelsAndPrices(fordClone);

        fordClone.deleteModelByNameAndPrice("car_del", 88);

        System.out.println();
        StaticTools.printModelsAndPrices(ford);
        StaticTools.printModelsAndPrices(fordClone);
    }
}