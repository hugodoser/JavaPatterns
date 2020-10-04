package com.company.lab3.task3;

import com.company.lab1.task2.abstraction.BaseModel;
import com.company.lab1.task2.abstraction.ThrowingError;
import com.company.lab1.task2.abstraction.Transport;
import com.company.lab1.task2.exceptions.*;
import com.company.lab1.task3.Prototype;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class Car implements Transport, Prototype, Serializable, Iterable<Car.Register>{
    private String carMark;
    private Car.Register[] registers;

    public Car(String markName, int arraySize) {
        try {
            setMark(markName);
            setArraySize(arraySize, 50);
        } catch (DuplicateModelNameException e) {
            e.printStackTrace();
        }
    }

    //метод для задания длины и заполнения
    public void setArraySize(int arraySize, double mediumPrice) throws DuplicateModelNameException {
        registers = new Register[0];
        for (int i = 0; i < arraySize; i++) {
            String name = "car_" + i;
            double price = mediumPrice + new Random().nextInt((int) mediumPrice / 2);
            addNewModel(name, price);
        }
    }

    public void setRegisters(Register[] registers) {
        this.registers = registers;
    }

    //метод для получения марки автомобиля
    public String getMark() {
        return carMark;
    }

    //метод для модификации марки автомобиля
    public void setMark(String mark) {
        this.carMark = mark;
    }

    //метод для получения значения цены модели по её названию
    public double getPriceByModelName(String name) throws NoSuchModelNameException {
        for (int i = 0; i < registers.length; i++) {
            if (registers[i].name.equals(name)) {
                return registers[i].price;
            }
        }
        String err = String.format("Модель {0} не найдена!", name);
        throw new NoSuchModelNameException(err);
    }

    //метод для модификации значения цены модели по её названию
    public void editPriceByModelName(String name, double newPrice) throws NoSuchModelNameException {
        for (int i = 0; i < registers.length; i++) {
            ThrowingError.checkNewPrice(name, newPrice);
            if (registers[i].name.equals(name) ) {
                registers[i].price = newPrice;
                System.out.println(getMark() + " " + name + " цена измнена на " + newPrice);
                return;
            }
        }
        ThrowingError.throwNoSuchModelName(name);
    }

    //метод, возвращающий массив названий всех моделей
    public String[] getArrayOfModelNames() {
        String[] arrayOfModelNames = new String[registers.length];
        for (int i = 0; i < arrayOfModelNames.length; i++) {
            if (registers[i] != null) {
                arrayOfModelNames[i] = registers[i].name;
            }
        }
        return arrayOfModelNames;
    }

    //метод, возвращающий массив значений цен моделей
    public double[] getArrayOfModelPrice() {
        double[] arrayOfModelPrice = new double[registers.length];
        for (int a = 0; a < arrayOfModelPrice.length; a++) {
            if (registers[a] != null) {
                arrayOfModelPrice[a] = registers[a].price;
            }
        }
        return arrayOfModelPrice;
    }

    //метод добавления названия модели и её цены (путем создания
    //нового массива Моделей), использовать метод Arrays.copyOf(),
    public void addNewModel(String name, double price) throws DuplicateModelNameException {
        for (int i = 0; i < registers.length; i++) {
            ThrowingError.checkDuplicateModelName(registers[i], name);
        }

        ThrowingError.checkNewPrice(name, price);

        registers = Arrays.copyOf(registers, registers.length + 1);
        registers[registers.length - 1] = new Register(name, price);

        System.out.println(getMark() + " " + name + " " + price + " добавлена!");

    }

    //метод для модификации значения названия модели
    public void editNameModel(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        for (int i = 0; i < registers.length; i++) {
            ThrowingError.checkDuplicateModelName(registers[i], newName);
            if (registers[i].name.equals(oldName)) {
                registers[i].name = newName;
                System.out.println(getMark() + " " + oldName + " измнена на " + newName);
                return;
            }
        }
        ThrowingError.throwNoSuchModelName(oldName);
    }

    //метод удаления модели с заданным именем и её цены, использовать методы System.arraycopy, Arrays.copyOf(),
    public void deleteModelByNameAndPrice(String name, double price) throws NoSuchModelNameException {
        Register[] newModels;
        for (int i = 0; i < registers.length; i++) {
            if (registers[i].name.equals(name) && registers[i].price == price) {
                newModels = Arrays.copyOf(registers, registers.length - 1);
                System.arraycopy(registers, i + 1, newModels, i, newModels.length - i);
                registers = newModels;
                System.out.println(getMark() + " " + name + " " + price + " удалена!");
                return;
            }
        }
        ThrowingError.throwNoSuchModelName(name);
    }

    //метод для получения размера массива Моделей
    public int getArrayModelLength() {
        return registers.length;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Car result = (Car)super.clone();
        result.registers = registers.clone();

        for (int i = 0; i < registers.length; i++) {
            result.registers[i] = new Register(registers[i].getName(),registers[i].getPrice());
        }

        return result;
    }

    @Override
    public Iterator<Car.Register> iterator() {
        return new Car.CarIterator();
    }

    static class Register extends BaseModel implements Serializable, Cloneable {

        public Register(String name, double price) {
            super(name, price);
        }
        @Override
        public String toString() {
            return getName() + " - " + getPrice();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this)
                return true;
            if (obj == null)
                return false;
            if (!(obj instanceof Car.Register))
                return false;
            Car.Register register = (Car.Register) obj;
            return getPrice() == register.getPrice() && !register.getName().equals(getName());
        }

        @Override
        public int hashCode() {
            double result = getPrice();
            result = 31 * result + (getName() != null ? getName().hashCode() : 0);
            return (int)result;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    class CarIterator implements Iterator<Car.Register> {
        private int cursor;

        public CarIterator() {
            cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor != registers.length;
        }

        @Override
        public Car.Register next() {
            return registers[cursor++];
        }
    }

}
