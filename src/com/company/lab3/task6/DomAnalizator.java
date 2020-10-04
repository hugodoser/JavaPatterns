package com.company.lab3.task6;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DomAnalizator implements IAnalizator {
    @Override
    public void fixAverage(String inputFile, String outputFile) {
        ArrayList<Integer> marks = new ArrayList<>();
        try {
            File input = new File(inputFile);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(input);
            NodeList subjects = document.getElementsByTagName("subject");
            for (int i = 0; i < subjects.getLength(); i++) {
                NamedNodeMap atributes = subjects.item(i).getAttributes();
                marks.add(Integer.valueOf(atributes.getNamedItem("mark").getNodeValue()));
            }
            double avr = 0;
            for (int i = 0; i < marks.size(); i++) {
                avr += marks.get(i);
            }

            avr /= marks.size();
            NodeList aver = document.getElementsByTagName("average");
            aver.item(0).setTextContent(String.valueOf(avr));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(outputFile));
            transformer.transform(source, result);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
