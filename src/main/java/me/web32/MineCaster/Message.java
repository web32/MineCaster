/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.web32.MineCaster;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;

/**
 *
 * @author maximiliansollner
 */
public class Message {
    private List<String> Words;
    
    public Message(String message) {
        setMessage(message);
    }
    
    
    
    public void setMessage(String message) {
        String[] wordString;
        wordString = message.split(" ");
        
        for (int i = 0; i < wordString.length; i++) {
            Words.add(wordString[i]);
            
        }
      
        
        
      /*  if(message.contains("$")) {
            for (int i = 0; i < wordString.length; i++) {
                if(wordString[i].startsWith("$")) {
                    if(wordString[i].contains("MOTD")) {
                        Words.add(Bukkit.getServer().getMotd().toString());
                    } else {
                        Words.add(wordString[i]);
                    }
                } else{
                    Words.add(wordString[i]);
                }
            }
        } else {
            for (int i = 0; i < wordString.length; i++) {
                Words.add(wordString[i].toString());
            }
        } */
    }
    
    public String getMessage() {
        String message = null;
        for(int i = 0; i < Words.size(); i++) {
            if(message.isEmpty()) {
                message = Words.get(i);               
            } else {
                message = message + " " + Words.get(i);
            }
        }
        return message;
    }
    
}