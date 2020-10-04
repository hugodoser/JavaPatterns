package com.company.lab1.task2.abstraction;

import java.io.Serializable;

public class BaseModel implements Serializable {
    public String name = null;
    public double price = Double.NaN;

    public BaseModel() {}

    public BaseModel(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}