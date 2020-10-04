package com.company.lab3.task6;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface IAnalizator {
    void fixAverage(String inputFile, String outputFile) throws ParserConfigurationException, SAXException, IOException;
}
