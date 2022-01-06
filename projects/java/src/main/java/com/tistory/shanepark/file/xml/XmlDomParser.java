package com.tistory.shanepark.file.xml;

import ch.qos.logback.core.util.FileSize;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XmlDomParser {

//    private static final String FILENAME = "/home/shane/Downloads/임시저장_20220106.xml";
    private static final String FILENAME = "/home/shane/Downloads/이관완료_20220106.xml";

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(FILENAME));
        NodeList files = doc.getElementsByTagName("file");
        long sum = 0;
        for (int i = 0; i < files.getLength(); i++) {
            Node file = files.item(i);
            long size = Long.parseLong(file.getAttributes().getNamedItem("size").getTextContent());
            sum += size;
        }

        System.out.println(sum + "Bytes");
        System.out.printf("%.2fGB\n", (float) sum / FileSize.GB_COEFFICIENT);
        System.out.println(FileSize.valueOf(String.valueOf(sum)));

    }

}
