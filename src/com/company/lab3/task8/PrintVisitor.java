package com.company.lab3.task8;

public class PrintVisitor implements Visitor {
    @Override
    public void visit(Car car) {
        System.out.print(StaticTools.transportToRowString(car));
    }

    @Override
    public void visit(Motorcycle motorcycle) {
        System.out.print(StaticTools.transportToColumnString(motorcycle));
    }
}
