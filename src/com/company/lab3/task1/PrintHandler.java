package com.company.lab3.task1;

import com.company.lab1.task2.abstraction.Transport;

public abstract class PrintHandler {
    protected PrintHandler handler;

    public PrintHandler() { }

    public PrintHandler(PrintHandler handler) {
        this.handler = handler;
    }

    void setChainHandler(PrintHandler handler){
        this.handler = handler;
    }

    abstract void print(String path, Transport transport);
}
