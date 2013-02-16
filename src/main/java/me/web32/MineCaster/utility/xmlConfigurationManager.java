/* The file xmlConfigurationManager.java was created 16.02.2013 at 13:01:16.
* Copyright (c) 2013 Maximilian Söllner alias web32. All rights reserved.
*/

package me.web32.MineCaster.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author web32
 */
public class xmlConfigurationManager {
    private XMLInputFactory inputFactory;

    public xmlConfigurationManager() {
        inputFactory = XMLInputFactory.newInstance();
    }
    
    public List<String> readXMLFile(String pathToFile) throws XMLStreamException, FileNotFoundException {
        //TODO Big try-Block
        InputStream is = new FileInputStream(pathToFile);
        XMLEventReader eventReader = inputFactory.createXMLEventReader(is);
        
        while(eventReader.hasNext()) {
            
        }   
        return null;
        
    }
    
    

}
