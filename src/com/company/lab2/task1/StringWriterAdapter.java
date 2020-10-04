package com.company.lab2.task1;

import java.io.IOException;
import java.io.OutputStream;

public class StringWriterAdapter implements StringWriter {
    private OutputStream stream;

    public StringWriterAdapter(OutputStream stream) {
        this.stream = stream;
    }

    @Override
    public void write(String[] lines) throws IOException {
        for (int i = 0; i < lines.length; i++) {
            String line = (i == lines.length - 1) ? lines[i] : lines[i] + "\n";
            stream.write(line.getBytes());
        }
        stream.flush();
    }
}