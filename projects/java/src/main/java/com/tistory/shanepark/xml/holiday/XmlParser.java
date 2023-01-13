package com.tistory.shanepark.xml.holiday;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlParser {

    public XmlResult parse(String xml) throws IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try (InputStream xmlInput = new ByteArrayInputStream(xml.getBytes())) {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            Document doc = dbf.newDocumentBuilder().parse(xmlInput);

            String errMsg = findOneValue(doc, "errMsg");
            if (errMsg != null) {
                String errorCode = findOneValue(doc, "returnReasonCode");
                String authMsg = findOneValue(doc, "returnAuthMsg");
                return XmlResult.ofFail(errMsg + ": " + authMsg, errorCode);
            }

            String resultCode = findOneValue(doc, "resultCode", "200");
            int rumOfRows = findOneIntValue(doc, "numOfRows", -1);
            int pageNo = findOneIntValue(doc, "pageNo", -1);
            int totalCount = findOneIntValue(doc, "totalCount", -1);

            NodeList nodes = doc.getElementsByTagName("item");
            int length = nodes.getLength();
            List<Map<String, String>> items = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                Map<String, String> item = new HashMap<>();
                Node node = nodes.item(i);
                NodeList children = node.getChildNodes();

                for (int j = 0; j < children.getLength(); j++) {
                    Node attr = children.item(j);
                    item.put(attr.getNodeName(), attr.getTextContent());
                }
                items.add(item);
            }
            return XmlResult.ofSuccess(resultCode, rumOfRows, pageNo, totalCount, items);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new IOException(e);
        }
    }

    private String findOneValue(Document doc, String name, String defaultValue) {
        NodeList numOfRowsNode = doc.getElementsByTagName(name);
        if (numOfRowsNode.getLength() > 0) {
            return numOfRowsNode.item(0).getTextContent();
        }
        return defaultValue;
    }

    private String findOneValue(Document doc, String name) {
        return findOneValue(doc, name, null);
    }

    private int findOneIntValue(Document doc, String name, int defaultValue) {
        String oneValue = findOneValue(doc, name);
        if (oneValue == null)
            return defaultValue;
        try {
            return Integer.parseInt(oneValue);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

}
