/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.web32.MineCaster;

import org.bukkit.ChatColor;

/**
 *
 * @author web32
 */
public class Message {
    private String text;
    private String[] words;
    
    public int length;
    public boolean colored;
    public boolean variables;
    
    public Message(String message) {
        //To Array
        words = message.split(" ");
        //To String
        text = message;
    }
    
    public String getMessage() {
        String message = " ";
        
        for (int i = 0; i < words.length; i++) {
            if(words[i].startsWith("&")) {
                String CC = words[i].substring(1, 2);
                message = message + ChatColor.getByChar(CC);
                message = message + " " + words[i].substring(2);
            } else {
               message = message + " " + words[i];
            }
        }
        return message;
    }
    
    
}
