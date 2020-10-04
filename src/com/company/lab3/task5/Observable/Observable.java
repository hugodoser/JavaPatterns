package com.company.lab3.task5.Observable;

import com.company.lab3.task5.Observer.Observer;

public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Object object);
}
