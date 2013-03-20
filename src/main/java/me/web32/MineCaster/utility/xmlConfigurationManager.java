/*
 * Copyright 2013 Maximilian Soellner. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ''AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those of the
 * authors and contributors and should not be interpreted as representing official policies,
 * either expressed or implied, of anybody else.
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
    
    public void saveMessages(List<Message> messageList) throws TransformerException {
    try {
            Document doc = dBuilder.parse(new File("plugins/MineCaster/config.xml"));
            
            Node config = doc.getElementsByTagName("config").item(0);
            
            NodeList messages = doc.getElementsByTagName("messages");
            
            for (int i = 0; i < messages.getLength(); i++) {
                Node node = messages.item(i);
                
                config.removeChild(node);	              
            }
                    
            Node messagesNode = config.appendChild(doc.createElement("messages"));
            for (int i = 0; i < messageList.size(); i++) {
                Node message = messagesNode.appendChild(doc.createElement("message"));
                Node textNode = message.appendChild(doc.createElement("text"));
                textNode.appendChild(doc.createTextNode(messageList.get(i).getMessageText()));
                if(messageList.get(i).getRealTime() != null) {
                    Node realTimeNode = message.appendChild(doc.createElement("realTime"));
                    realTimeNode.appendChild(doc.createTextNode(messageList.get(i).getRealTime()));
                }          
            }
                    
                //Save result to file
                try {
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer;
                    transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(new File("plugins/MineCaster/config.xml"));
                    transformer.transform(source, result); 
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