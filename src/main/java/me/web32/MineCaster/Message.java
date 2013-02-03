/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.web32.MineCaster;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/**
 *
 * @author web32
 */
public class Message {
    private String text;
    private List<String> words;
    
    public int length;
    public boolean colored;
    public boolean variables;
    
    public Message(String message) {
        //To List
        String[] wordsArray = message.split(" ");
        words.addAll(Arrays.asList(wordsArray));
        
        //To String
        text = message;
    }
    
    public String getMessage() {
        String message = " ";
        
        for (int i = 0; i < words.size(); i++) {
            if(words.get(i).startsWith("&")) {
                String CC = words.get(i).substring(1,2);
                message = message + ChatColor.getByChar(CC);
                message = message + words.get(i).substring(2);
            } else {
               message = message + words.get(i);
            }
        }
        return message;
    }
    
    
}
