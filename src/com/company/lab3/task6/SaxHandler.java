package com.company.lab3.task6;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaxHandler extends DefaultHandler {
    private List<Integer> marks = new ArrayList<>();
    private static String displayText[] = new String[1000];
    private static int numberLines = 0;

    private double getAvg(){
        double avr = 0;
        for (int i = 0; i < marks.size(); i++) {
            avr += marks.get(i);
        }
        return avr/marks.size();
    }

    public void writeXml(String outputFile) throws IOException {
        FileWriter filewriter = new FileWriter(outputFile);
        for(int i = 0; i < numberLines; i++){
            filewriter.write(displayText[i].toCharArray());
            filewriter.write('\n');
        }
        filewriter.close();
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        displayText[numberLines] = "";
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("subject")) {
            String mark = attributes.getValue("mark");
            marks.add(Integer.parseInt(mark));
        }

        if (qName.equalsIgnoreCase("subject") || qName.equalsIgnoreCase("average"))
            displayText[numberLines] = "    ";
        displayText[numberLines] += '<';
        displayText[numberLines] += qName;
        if (attributes != null) {
            int numberAttributes = attributes.getLength();
            for (int i = 0; i < numberAttributes; i++){
                displayText[numberLines] += ' ';
                displayText[numberLines] += attributes.getQName(i);
                displayText[numberLines] += "=\"";
                displayText[numberLines] += attributes.getValue(i);
                displayText[numberLines] += '"';
            }
        }
        if (qName.equalsIgnoreCase("subject"))
            displayText[numberLines] += "/>";
        else displayText[numberLines] += '>';
        if (!qName.equalsIgnoreCase("average"))
            numberLines++;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String characterData = (new String(ch, start, length)).trim();
        if(characterData.indexOf("\n") < 0 && characterData.length() > 0) {
            displayText[numberLines] += getAvg();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if(qName.equalsIgnoreCase("average"))
            displayText[numberLines++] += "</" + qName + ">";
        else if(qName.equalsIgnoreCase("student"))
            displayText[numberLines++] = "</" + qName + ">";
    }
}

