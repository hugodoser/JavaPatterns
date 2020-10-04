package com.company.lab2.task3.client;

import java.io.IOException;

public class ProxyProcessor implements Processor {
    private Processor client = null;

    public double multiple(double first, double second) throws IOException {
        if (client == null)
            client = new NetworkProcessor();
        return client.multiple(first, second);
    }
}