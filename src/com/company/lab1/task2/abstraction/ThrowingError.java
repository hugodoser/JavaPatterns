package com.company.lab1.task2.abstraction;

import com.company.lab1.task2.exceptions.DuplicateModelNameException;
import com.company.lab1.task2.exceptions.ModelPriceOutOfBoundsException;
import com.company.lab1.task2.exceptions.NoSuchModelNameException;

public interface ThrowingError {
    static void checkNewPrice(String name, double price){
        if (price < 0) {
            String err = String.format("Новая цена меньше 0!", name);
            throw new ModelPriceOutOfBoundsException(err);
        }
    }

    static void throwNoSuchModelName(String name) throws NoSuchModelNameException {
        String err = String.format("Модель {0} не найдена! Название не было изменено!", name);
        throw new NoSuchModelNameException(err);
    }

    static void checkDuplicateModelName(BaseModel model, String name) throws DuplicateModelNameException {
        if (model != null && model.name.equals(name)) {
            String err = String.format("Модель {0} уже есть! Добавление не было выполнено !", name);
            throw new DuplicateModelNameException(err);
        }
    }

    static void checkDuplicateModelName(String existName, String name) throws DuplicateModelNameException {
        if (existName.equals(name)) {
            String err = String.format("Модель {0} уже есть! Добавление не было выполнено!", name);
            throw new DuplicateModelNameException(err);
        }
    }
}