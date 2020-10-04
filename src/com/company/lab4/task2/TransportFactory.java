package com.company.lab4.task2;

import com.company.lab1.task2.abstraction.Transport;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TransportFactory {
    private TransportFactory() {}

    public static Transport createReflectiveTransport(String name, String nameClass) {
        Transport newTransport = null;
        try {
            Class selectedClass = Class.forName(nameClass);
            Constructor constructor = selectedClass.getConstructor(new Class[]{String.class});
            newTransport = (Transport) constructor.newInstance(new Object[]{name});
        } catch (ClassNotFoundException e) {
            System.out.println("Класс не найден");
        } catch (NoSuchMethodException e) {
            System.out.println("Конструктор не найден");
        } catch (InvocationTargetException e) {
            System.out.println("При вызове возникло исключение");
        } catch (Exception e) {
            System.out.println("Неверный ввод");
        }
        return newTransport;
    }
}
