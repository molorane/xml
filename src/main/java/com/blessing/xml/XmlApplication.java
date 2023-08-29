package com.blessing.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class XmlApplication {

    public static void main(String[] args) {

        try {
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            final DocumentBuilder db = dbf.newDocumentBuilder();

            final Document doc = db.parse(new File("yoruba.xml"));

            doc.getDocumentElement().normalize();

            System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
            System.out.println("------");

            final NodeList list = doc.getElementsByTagName("Hymn");

            for (int temp = 0; temp < list.getLength(); temp++) {
                final Node node = list.item(temp);

                final Path path = Paths.get(String.format("%s.txt", temp + 1));
                final NodeList hymnNodes = node.getChildNodes();

                for (int i = 0; i < hymnNodes.getLength(); i++) {
                    final Node hymnNode = hymnNodes.item(i);
                    if (hymnNode.getNodeType() == Node.ELEMENT_NODE) {
                        final Element element = (Element) hymnNode;
                        String first = "";
                        if (i == 1) {
                            System.out.print(element.getFirstChild().getNodeValue());
                            //writer.write(element.getFirstChild().getNodeValue());
                            //writer.newLine();
                        }
                        if (i == 3) {
                            //writer.write(" "+element.getFirstChild().getNodeValue());
                            //writer.newLine();
                            System.out.println(": " + "\"" + element.getFirstChild().getNodeValue() + "\",");
                        }
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
