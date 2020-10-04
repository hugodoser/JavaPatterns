package com.company.lab3.task6;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Context {
    private IAnalizator analizator;

    public Context() {}

    public Context(IAnalizator analizator) {
        this.analizator = analizator;
    }

    public IAnalizator getAnalizator() {
        return analizator;
    }

    public void setAnalizator(IAnalizator analizator) {
        this.analizator = analizator;
    }

    public void process(String inputFile, String outputFile) throws IOException, SAXException, ParserConfigurationException {
        analizator.fixAverage(inputFile, outputFile);
    }
}
