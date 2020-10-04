package com.company.lab2.task2;

import com.company.lab1.task2.abstraction.BaseModel;
import com.company.lab1.task2.exceptions.*;
import com.company.lab1.task2.abstraction.Transport;

public class TransportDecorator implements Transport {

    private Transport transport;

    public TransportDecorator(Transport transport) {
        this.transport = transport;
    }

    @Override
    public synchronized String getMark() {
        return transport.getMark();
    }

    @Override
    public synchronized void setMark(String mark) {
        transport.setMark(mark);
    }

    @Override
    public synchronized void addNewModel(String name, double price) throws DuplicateModelNameException {
        transport.addNewModel(name,price);
    }

    @Override
    public synchronized double getPriceByModelName(String name) throws NoSuchModelNameException {
        return transport.getPriceByModelName(name);
    }

    @Override
    public synchronized void editPriceByModelName(String name, double newPrice) throws NoSuchModelNameException {
        transport.editPriceByModelName(name,newPrice);
    }

    @Override
    public synchronized String[] getArrayOfModelNames() {
        return transport.getArrayOfModelNames();
    }

    @Override
    public synchronized double[] getArrayOfModelPrice() {
        return transport.getArrayOfModelPrice();
    }

    @Override
    public synchronized void deleteModelByNameAndPrice(String name, double price) throws NoSuchModelNameException {
        transport.deleteModelByNameAndPrice(name,price);
    }

    @Override
    public synchronized void editNameModel(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        transport.editNameModel(oldName, newName);
    }

    @Override
    public synchronized int getArrayModelLength() {
        return transport.getArrayModelLength();
    }
}