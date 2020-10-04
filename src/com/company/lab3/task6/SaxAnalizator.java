package com.company.lab3.task6;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SaxAnalizator implements IAnalizator{
    @Override
    public void fixAverage(String inputFile, String outputFile) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            SaxHandler saxHandler = new SaxHandler();
            saxParser.parse(inputFile, saxHandler);

            saxHandler.writeXml(outputFile);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}


