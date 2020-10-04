package com.company.lab4.task2;

import com.company.lab1.task2.abstraction.Transport;
import com.company.lab4.task2.abstraction.TransportSource;

import java.io.IOException;
import java.util.List;

public class TransportDao {
    private TransportSource source;

    public TransportDao(TransportSource source) {
        if (source == null)
            throw new NullPointerException("Transport Source = null");
        this.source = source;
    }

    public void updateTransports(List<? extends Transport> transports) throws IOException {
        source.startWriteTransaction();
        source.rewrite(transports);
        source.closeWriteTransaction();
    }

    public List<? extends Transport> getTransports() throws Exception {
        source.startReadTransaction();
        List<? extends Transport> transports = source.read();
        source.closeReadTransaction();
        return transports;
    }
}
