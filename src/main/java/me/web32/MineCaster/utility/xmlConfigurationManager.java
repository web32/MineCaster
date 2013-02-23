/* The file xmlConfigurationManager.java was created 16.02.2013 at 13:01:16.
* Copyright (c) 2013 Maximilian Söllner alias web32. All rights reserved.
*/

package me.web32.MineCaster.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import me.web32.MineCaster.Main;
import me.web32.MineCaster.Message;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
                    List<String> realTime = new ArrayList<String>();
                    for (int j = 0; j < e.getElementsByTagName("realTime").getLength(); j++) {
                        realTime.add(e.getElementsByTagName("realTime").item(j).getTextContent());   
                    }
                    
                    //Output Message
                    if(realTime.size() > 0) {
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
    
    public void saveSettings(String pathToFile) {
        try {
            Document doc = dBuilder.parse(new File(pathToFile));
            Element config = (Element) doc.getElementsByTagName("config").item(0);
            
            config.getElementsByTagName("random").item(0).setTextContent(String.valueOf(Main.random));
            config.getElementsByTagName("enabled").item(0).setTextContent(String.valueOf(Main.enabled));
            config.getElementsByTagName("interval").item(0).setTextContent(String.valueOf("123"));
            config.getElementsByTagName("prefix").item(0).setTextContent(Main.prefix.getMessageText());
            
            //Save result to file
                try {
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer;
                    transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(new File(pathToFile));
                try { 
                    transformer.transform(source, result);
                } catch (TransformerException ex) {
                    Logger.getLogger(xmlConfigurationManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                } catch (TransformerConfigurationException ex) {
                    Logger.getLogger(xmlConfigurationManager.class.getName()).log(Level.SEVERE, null, ex);
                }
        } catch (SAXException ex) {
            Logger.getLogger(xmlConfigurationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(xmlConfigurationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void resetMessages(String pathToFile,TableModel model) throws TransformerException {
        try {
            Document doc = dBuilder.parse(new File(pathToFile));
            
            Node config = doc.getElementsByTagName("config").item(0);
            
            NodeList messages = doc.getElementsByTagName("messages");
            
            for (int i = 0; i < messages.getLength(); i++) {
                Node node = messages.item(i);
                
                config.removeChild(node);	              
            }
            
            Node messagesNode = config.appendChild(doc.createElement("messages"));
                for (int j = 0; j < model.getRowCount(); j++) {
                    Node message = messagesNode.appendChild(doc.createElement("message"));
                    for (int k = 0; k < model.getColumnCount(); k++) {
                        if(k==0) {
                            Node text = message.appendChild(doc.createElement("text"));
                            text.appendChild(doc.createTextNode(String.valueOf(model.getValueAt(j, k))));
                        } else {
                            String realTimeCell = (String) model.getValueAt(j, k);
                            if(!realTimeCell.contains("Announced")) {
                                Node realTime = message.appendChild(doc.createElement("realTime"));
                                realTime.appendChild(doc.createTextNode(String.valueOf(model.getValueAt(j, k))));
                            }
                        }
                        for (int i = 0; i < config.getChildNodes().getLength(); i++) {
                            config.getChildNodes().item(i);
                        }
                    }
            
                }
                //Save result to file
                try {
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer;
                    transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(new File(pathToFile));
                    transformer.transform(source, result); 
                } catch (TransformerConfigurationException ex) {
                    Logger.getLogger(xmlConfigurationManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //Delete old Messages
                Main.messages = new ArrayList<Message>();
                Main.realTimeMessages = new ArrayList<Message>();
            
            
        } catch (SAXException ex) {
            Logger.getLogger(xmlConfigurationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(xmlConfigurationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            loadConfiguration("plugins/MineCaster/config.xml");
        } catch (XMLStreamException ex) {
            Logger.getLogger(xmlConfigurationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(xmlConfigurationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}