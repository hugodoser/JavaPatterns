package com.company.lab1.task2.abstraction;

import com.company.lab1.task2.exceptions.*;

public interface TransportFactory {
    Transport createInstance(String mark, int size) throws DuplicateModelNameException;
}