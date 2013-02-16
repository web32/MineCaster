/* The file xmlConfigurationManager.java was created 16.02.2013 at 13:01:16.
* Copyright (c) 2013 Maximilian Söllner alias web32. All rights reserved.
*/

package me.web32.MineCaster.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import me.web32.MineCaster.Main;
import me.web32.MineCaster.Message;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author web32
 */
public class xmlConfigurationManager {
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;

    public xmlConfigurationManager() {
        try {
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(xmlConfigurationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadConfiguration(String pathToFile) throws XMLStreamException, FileNotFoundException {
        try {
            Document doc = dBuilder.parse(new File(pathToFile));
            doc.getDocumentElement().normalize();
            
            Main.enabled = Boolean.valueOf(doc.getElementsByTagName("enabled").item(0).getTextContent());
            Main.random = Boolean.valueOf(doc.getElementsByTagName("random").item(0).getTextContent());
            
            Main.interval = Integer.valueOf(doc.getElementsByTagName("interval").item(0).getTextContent());
            
            Main.prefix = new Message(doc.getElementsByTagName("prefix").item(0).getTextContent());
            
            for (int i = 0; i < doc.getElementsByTagName("message").getLength() ; i++) {
                Node messageNode = doc.getElementsByTagName("message").item(i);
                if(messageNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) messageNode;
                    
                    //Define message-text
                    String text = "";
                    text = e.getElementsByTagName("text").item(0).getTextContent();
                    
                    //Define realTime-array
                    String[] realTime = new String[e.getElementsByTagName("realTime").getLength()];
                    for (int j = 0; j < e.getElementsByTagName("realTime").getLength(); j++) {
                        realTime[j] = e.getElementsByTagName("realTime").item(j).getTextContent();   
                    }
                    
                    System.out.println(text);
                    //Output Message
                    if(realTime.length > 0) {
                        Message output = new Message(text, realTime);
                        Main.realTimeMessages.add(output);
                    } else {
                        Message output = new Message(text);
                        Main.messages.add(output);
                    }
                }
                
            }
            
        } catch (SAXException ex) {
            Logger.getLogger(xmlConfigurationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(xmlConfigurationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}