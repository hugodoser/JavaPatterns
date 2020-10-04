package com.company.lab3.task8;

import java.util.ArrayList;
import java.util.List;

public class TransportPark {
    List<Transport> transports = new ArrayList<>();

    public TransportPark(){ }

    public void add(Transport transport){
        transports.add(transport);
    }

    public void remove(Transport transport){
        transports.remove(transport);
    }

    public void accept(Visitor visitor){
        for(Transport t: transports){
            t.accept(visitor);
        }
    }
}
