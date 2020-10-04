package com.company.lab3.task8;

import com.company.lab1.task2.abstraction.BaseModel;
import com.company.lab1.task2.abstraction.ThrowingError;
import com.company.lab1.task2.exceptions.DuplicateModelNameException;
import com.company.lab1.task2.exceptions.NoSuchModelNameException;
import com.company.lab1.task3.Prototype;

import java.util.Random;

public class Motorcycle implements Transport, Prototype {

    private String motorcycleMark;
    private Model head = new Model();
    private int size = 0;

    {
        head.prev = head;
        head.next = head;
    }

    public Motorcycle(String name, int size) {
        try {
            setMark(name);
            setArraySize(size, 70);
        } catch (DuplicateModelNameException e) {
            e.printStackTrace();
        }
    }

    //метод для задания длины и заполнения
    public void setArraySize(int arraySize, double mediumPrice) throws DuplicateModelNameException {
        for (int i = 0; i < arraySize; i++) {
            String name = "motorcycle_" + i;
            double price = mediumPrice + new Random().nextInt((int) mediumPrice / 2);
            addNewModel(name, price);
        }
    }

    public String getMark() {
        return motorcycleMark;
    }

    public void setMark(String mark) {
        this.motorcycleMark = mark;
    }

    //метод добавления названия модели и её цены
    public void addNewModel(String name, double price) throws DuplicateModelNameException {
        ThrowingError.checkNewPrice(name, price);
        if (head != null) {
            String[] models = getArrayOfModelNames();
            for (int i = 0; i < models.length; i++) {
                ThrowingError.checkDuplicateModelName(models[i], name);
            }
        }

        if (head.next == head && head.prev == head) {
            Model temp = new Model(name, price);
            head.next = temp;
            head.prev = temp;
            temp.next = head;
            temp.prev = head;
        } else {
            Model temp = new Model(name, price);
            temp.next = head.next;
            temp.prev = head;
            head.next.prev = temp;
            head.next = temp;
        }
        size++;

    }

    //метод для получения размера массива Моделей
    public int getArrayModelLength() {
        return size;
    }

    //метод, возвращающий массив названий всех моделей
    public String[] getArrayOfModelNames() {
        String[] models = new String[size];

        if(head.next != head && head.prev != head){
            Model temp = head.next;

            int i = 0;
            while (i != size) {
                models[i] = temp.name;
                temp = temp.next;
                i++;
            }
        }
        return models;
    }

    //метод, возвращающий массив значений цен моделей
    public double[] getArrayOfModelPrice() {
        if(head.next != head && head.prev != head){
            Model temp = head.next;
            double[] prices = new double[size];
            int i = 0;
            while (i != size) {
                prices[i] = temp.price;
                temp = temp.next;
                i++;
            }
            return prices;
        }
        return null;
    }

    //метод для модификации значения цены модели по её названию
    public void editPriceByModelName(String name, double newPrice) throws NoSuchModelNameException {
        if(head.next != head && head.prev != head){
            ThrowingError.checkNewPrice(name, newPrice);
            Model temp = head.next;
            for (int i = 0; i < size; i++) {
                if (temp.name.equals(name)) {
                    temp.price = newPrice;
                    System.out.println(getMark() + " " + name + " цена измнена на " + newPrice);
                    break;
                } else if (i == size - 1) {
                    ThrowingError.throwNoSuchModelName(name);
                }
                temp = temp.next;
            }
        }
    }

    //метод для получения значения цены модели по её названию
    public double getPriceByModelName(String name) throws NoSuchModelNameException {
        if(head.next != head && head.prev != head){
            Model temp = head.next;
            for (int i = 0; i < size; i++) {
                if (temp.name.equals(name)) {
                    return temp.price;
                } else if (i == size - 1){
                    ThrowingError.throwNoSuchModelName(name);
                }
                temp = temp.next;
            }
        }
        return 0;
    }

    //метод удаления модели с заданным именем и её цены
    public void deleteModelByNameAndPrice(String name, double price) throws NoSuchModelNameException {
        if(head.next != head && head.prev != head) {
            Model temp = head.next;
            for (int a = 0; a < size; a++) {
                if (temp.name.equals(name)) {
                    temp.name = null;
                    temp.price = Double.NaN;
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                    System.out.println(getMark() + " " + name + " " + price + " удалена!");
                    size--;
                    break;
                } else if (a == size - 1) {
                    ThrowingError.throwNoSuchModelName(name);
                }
                temp = temp.next;
            }
        }
    }

    //метод для модификации значения названия модели
    public void editNameModel(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        if(head.next != head && head.prev != head) {
            String[] models = getArrayOfModelNames();
            for (int i = 0; i < models.length; i++) {
                ThrowingError.checkDuplicateModelName(models[i], newName);
            }
            Model temp = head.next;

            for (int i = 0; i < size; i++) {
                if (temp.name.equals(oldName)) {
                    temp.name = newName;
                    break;
                } else if (i == size - 1) {
                    ThrowingError.throwNoSuchModelName(oldName);
                }
                temp = temp.next;
            }
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        Motorcycle motorcycle = (Motorcycle) super.clone();
        motorcycle.head = new Model();
        motorcycle.head.prev = motorcycle.head;
        motorcycle.head.next = motorcycle.head;
        motorcycle.size = 0;
        Model h = this.head.prev;
        for (int i = 0; i < size; i++) {
            try {
                motorcycle.addNewModel(h.name, h.price);
                h = h.prev;
            } catch (DuplicateModelNameException e) {
                e.printStackTrace();
            }
        }
        return motorcycle;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public class Model extends BaseModel {
        Model prev = null;
        Model next = null;

        public Model() {
            super();
        }

        public Model(String name, double price) {
            super(name, price);
        }
    }

}
