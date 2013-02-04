/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.web32.MineCaster;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    
   @Deprecated
   public String getMessageText() {
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
    
    public String getMessage() {
        String message = "";
        char[] chars = text.toCharArray();
        String[] s = new String[chars.length];
        
        for (int i = 0; i < chars.length; i++) {
            s[i] = String.valueOf(chars[i]);  
        }    
        for (int j = 0; j < s.length; j++) {
                if(s[j].equalsIgnoreCase("&")) {
                    message = message + ChatColor.getByChar(s[j+1]);
                    j++;
                } else {
                    message = message + s[j];
                }
            }
        return message;
    }
}
