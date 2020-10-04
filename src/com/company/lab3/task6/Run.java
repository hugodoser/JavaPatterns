package com.company.lab3.task6;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Run {
    public static void main(String[] args) {
        Context context = new Context();
        context.setAnalizator(new DomAnalizator());
        //context.setAnalizator(new SaxAnalizator());
        try {
            context.process(args[0], args[1]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }


}
