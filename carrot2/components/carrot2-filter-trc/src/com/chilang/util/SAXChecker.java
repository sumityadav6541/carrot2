package com.chilang.util;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class SAXChecker {

  public static void main(String[] args) {

    if (args.length <= 0) {
      System.out.println("Usage: java SAXChecker URL");
      return;
    }

    try {
      SAXParser factory = javax.xml.parsers.SAXParserFactory.newInstance().newSAXParser();
      XMLReader parser = factory.getXMLReader();
        ContentHandler handler = new DefaultHandler() {
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                System.out.println("El:"+qName);
            }
        };
      parser.setContentHandler(handler);
      parser.parse(args[0]);
      System.out.println(args[0] + " is well-formed.");
    }
    catch (SAXException e) {
        System.out.println("Error: "+e);
      System.out.println(args[0] + " is not well-formed.");
    }
    catch (IOException e) {
        System.out.println("Error: "+e);
      System.out.println(
       "Due to an IOException, the parser could not check "
       + args[0]
      );
    } catch (ParserConfigurationException e) {
        e.printStackTrace();  //To change body of catch statement use Options | File Templates.
    }

  }

}