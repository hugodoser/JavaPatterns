package com.company.lab3.task5.Observer;

import com.company.lab3.task5.Observable.Observable;

public interface Observer {
    void update(Object obj);
    void subscribeOn(Observable observable);
}
