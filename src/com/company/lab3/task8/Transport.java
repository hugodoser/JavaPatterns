package com.company.lab3.task8;

import com.company.lab1.task2.exceptions.DuplicateModelNameException;
import com.company.lab1.task2.exceptions.NoSuchModelNameException;

public interface Transport {

        String getMark();

        void setMark(String mark);

        void addNewModel(String name, double price) throws DuplicateModelNameException;

        double getPriceByModelName(String name) throws NoSuchModelNameException;

        void editPriceByModelName(String name, double newPrice) throws NoSuchModelNameException;

        String[] getArrayOfModelNames();

        double[] getArrayOfModelPrice();

        void deleteModelByNameAndPrice(String name, double price) throws NoSuchModelNameException;

        void editNameModel(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException;

        int getArrayModelLength();

        void accept(Visitor visitor);
}
