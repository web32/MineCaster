/* The file MEssage.java was created.
* Copyright (c) 2013 Maximilian Söllner alias web32. All rights reserved.
*/

package me.web32.MineCaster;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.*;

/**
 *
 * @author web32
 */
public class Message {
    //RealTime Scheduling
    private boolean scheduledRealTime = false;
    private String realTime;
    
    //MC-Time Scheduling
    private boolean scheduledMCTime = false;
    private String MCTime;
    
    //Additional Features
    private boolean usingColor = false;
    private boolean usingVariable = false;
    
    //Message Content
    private String text;
    private String[] words;

    
    //Schedule Message with interval
    public Message(String text) {
        //Save plaintext
        this.text = text;
        
        //Save WordArray
        this.words = text.split(" ");
        
        //Set booleans
        if(this.text.contains("&")) usingColor = true;
        if(this.text.contains("$")) usingVariable = true;
    }
    
    //Schedule Message with RealTime
    public Message(String text, String[] realTime) {
        //Save plaintext
        this.text = text;
        
        //Save WordArray
        this.words = text.split(" ");
        
        //Set booleans
        if(this.text.contains("&")) usingColor = true;
        if(this.text.contains("$")) usingVariable = true;
        
        //Schedule Annoucement
        for (int i = 0; i < realTime.length; i++) {
            DateFormat time = new SimpleDateFormat();
            try {
                time.parse(realTime[i]);
            } catch (ParseException ex) {
                Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
            }
            int initialTime = (int) (time.getCalendar().getTimeInMillis() - System.currentTimeMillis());
            System.out.println(initialTime);
            Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("MineCaster"), new Runnable() {
                public void run() {
                    
                }
            }, initialTime * 20L, 1000 * 60 * 60 * 24 * 20L);            
        }
    }
    /*
     * Message Output
     */
   public void broadcastMessage() {
       if(text.equals("<PlayerCount>")) {
            Main.playerCountGraph.drawGraph();
            Broadcaster.broadcast(Main.prefix.getMessageText(), "-------------------------");
            return;
        } 
       //TODO Refurbish this part
       String message = "";
        char[] chars = text.toCharArray();
        String[] s = new String[chars.length];
        
        for (int i = 0; i < chars.length; i++) {
            s[i] = String.valueOf(chars[i]);  
        }    
        for (int j = 0; j < s.length; j++) {
                //Check if ColorCode
                if(s[j].equalsIgnoreCase("&")) {
                    message = message + ChatColor.getByChar(s[j+1]);
                    j++;
                //Check if Variable
                } else if(s[j].equalsIgnoreCase("$")) {
                    String variable = "";
                    for (int i = 1; (i+j) < s.length && !s[i+j].equalsIgnoreCase(" "); i++) {
                        variable = variable + s[i+j];
                    }
                    message = message + replaceVariable(variable);
                    j = j + variable.length();               
                } else {
                    message = message + s[j];
                }   
            }
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
    
   @Deprecated
    public String getMessage() {
        if(text.equals("<PlayerCount>")) {
            Main.playerCountGraph.drawGraph();
            return "----------------------------";
        }
        String message = "";
        char[] chars = text.toCharArray();
        String[] s = new String[chars.length];
        
        for (int i = 0; i < chars.length; i++) {
            s[i] = String.valueOf(chars[i]);  
        }    
        for (int j = 0; j < s.length; j++) {
                //Check if ColorCode
                if(s[j].equalsIgnoreCase("&")) {
                    message = message + ChatColor.getByChar(s[j+1]);
                    j++;
                //Check if Variable
                } else if(s[j].equalsIgnoreCase("$")) {
                    String variable = "";
                    for (int i = 1; (i+j) < s.length && !s[i+j].equalsIgnoreCase(" "); i++) {
                        variable = variable + s[i+j];
                    }
                    message = message + replaceVariable(variable);
                    j = j + variable.length();               
                } else {
                    message = message + s[j];
                }
                
                
            }
        return message;
    }
    
    private String replaceVariable(String variable) {
        if(variable.equalsIgnoreCase("Port")) {
            return String.valueOf(Bukkit.getPort());
        } else if (variable.equalsIgnoreCase("IP")) {
            return String.valueOf(Bukkit.getIp());
        } else if (variable.equalsIgnoreCase("BukkitVersion")) {
            return String.valueOf(Bukkit.getBukkitVersion());
        } else if (variable.equalsIgnoreCase("MOTD")) {
            return Bukkit.getMotd();
        } else if (variable.equalsIgnoreCase("PlayerCount")) {
            return String.valueOf(Bukkit.getOnlinePlayers().length);
        } else {
            return "{NOT DEFINED}";
        }
    }
}
