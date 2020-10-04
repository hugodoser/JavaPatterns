package com.company.lab3.task4;

import com.company.lab1.task2.abstraction.BaseModel;
import com.company.lab1.task2.abstraction.ThrowingError;
import com.company.lab1.task2.abstraction.Transport;
import com.company.lab1.task2.exceptions.*;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

public class Car implements Transport, Serializable{
    private String carMark;
    private Model[] models;

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
        models = new Model[0];
        for (int i = 0; i < arraySize; i++) {
            String name = "car_" + i;
            double price = mediumPrice + new Random().nextInt((int) mediumPrice / 2);
            addNewModel(name, price);
        }
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
        for (int i = 0; i < models.length; i++) {
            if (models[i].name.equals(name)) {
                return models[i].price;
            }
        }
        String err = String.format("Модель {0} не найдена!", name);
        throw new NoSuchModelNameException(err);
    }

    //метод для модификации значения цены модели по её названию
    public void editPriceByModelName(String name, double newPrice) throws NoSuchModelNameException {
        for (int i = 0; i < models.length; i++) {
            ThrowingError.checkNewPrice(name, newPrice);
            if (models[i].name.equals(name) ) {
                models[i].price = newPrice;
                System.out.println(getMark() + " " + name + " цена измнена на " + newPrice);
                return;
            }
        }
        ThrowingError.throwNoSuchModelName(name);
    }

    //метод, возвращающий массив названий всех моделей
    public String[] getArrayOfModelNames() {
        String[] arrayOfModelNames = new String[models.length];
        for (int i = 0; i < arrayOfModelNames.length; i++) {
            if (models[i] != null) {
                arrayOfModelNames[i] = models[i].name;
            }
        }
        return arrayOfModelNames;
    }

    //метод, возвращающий массив значений цен моделей
    public double[] getArrayOfModelPrice() {
        double[] arrayOfModelPrice = new double[models.length];
        for (int a = 0; a < arrayOfModelPrice.length; a++) {
            if (models[a] != null) {
                arrayOfModelPrice[a] = models[a].price;
            }
        }
        return arrayOfModelPrice;
    }

    //метод добавления названия модели и её цены (путем создания
    //нового массива Моделей), использовать метод Arrays.copyOf(),
    public void addNewModel(String name, double price) throws DuplicateModelNameException {
        for (int i = 0; i < models.length; i++) {
            ThrowingError.checkDuplicateModelName(models[i], name);
        }

        ThrowingError.checkNewPrice(name, price);

        models = Arrays.copyOf(models, models.length + 1);
        models[models.length - 1] = new Model(name, price);

        System.out.println(getMark() + " " + name + " " + price + " добавлена!");

    }

    //метод для модификации значения названия модели
    public void editNameModel(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        for (int i = 0; i < models.length; i++) {
            ThrowingError.checkDuplicateModelName(models[i], newName);
            if (models[i].name.equals(oldName)) {
                models[i].name = newName;
                System.out.println(getMark() + " " + oldName + " измнена на " + newName);
                return;
            }
        }
        ThrowingError.throwNoSuchModelName(oldName);
    }

    //метод удаления модели с заданным именем и её цены, использовать методы System.arraycopy, Arrays.copyOf(),
    public void deleteModelByNameAndPrice(String name, double price) throws NoSuchModelNameException {
        Model[] newModels;
        for (int i = 0; i < models.length; i++) {
            if (models[i].name.equals(name) && models[i].price == price) {
                newModels = Arrays.copyOf(models, models.length - 1);
                System.arraycopy(models, i + 1, newModels, i, newModels.length - i);
                models = newModels;
                System.out.println(getMark() + " " + name + " " + price + " удалена!");
                return;
            }
        }
        ThrowingError.throwNoSuchModelName(name);
    }

    //метод для получения размера массива Моделей
    public int getArrayModelLength() {
        return models.length;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Car result = (Car)super.clone();
        result.models = models.clone();

        for (int i = 0; i < models.length; i++) {
            result.models[i] = new Model(models[i].name,models[i].price);
        }

        return result;
    }

    public class Model extends BaseModel implements Serializable {

        public Model(String name, double price) {
            super(name, price);
        }

    }

    public void setMemento(Car.CarMemento memento) {
        Car car = memento.getCar();
        copyState(car);
    }

    public Car.CarMemento createMemento() {
        Car.CarMemento memento = new Car.CarMemento(new ByteArrayOutputStream());
        memento.setCar(this);
        return memento;
    }

    private void copyState(Car car) {
        carMark = car.carMark;
        models = car.models;
    }

    public static class CarMemento {
        private ByteArrayOutputStream arrayOutputStream;

        public CarMemento(ByteArrayOutputStream arrayOutputStream) {
            this.arrayOutputStream = arrayOutputStream;
        }

        public void setCar(Car car) {
            try (ObjectOutputStream serObj = new ObjectOutputStream(arrayOutputStream)) {
                serObj.writeObject(car);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public Car getCar() {
            ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(arrayOutputStream.toByteArray());
            Car car = null;
            try (ObjectInputStream deserObj = new ObjectInputStream(arrayInputStream)) {
                car = (Car) deserObj.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return car;
        }
    }
}
