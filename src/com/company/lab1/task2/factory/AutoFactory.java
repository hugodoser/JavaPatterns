package com.company.lab1.task2.factory;

import com.company.lab1.task2.Car;
import com.company.lab1.task2.abstraction.Transport;
import com.company.lab1.task2.abstraction.TransportFactory;
import com.company.lab1.task2.exceptions.*;

public class AutoFactory implements TransportFactory {
    @Override
    public Transport createInstance(String mark, int size) throws DuplicateModelNameException {
        return new Car(mark,size);
    }
}