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

package me.web32.MineCaster;

import java.util.Calendar;
import java.util.List;
import org.bukkit.*;

/**
 *
 * @author web32
 */
public class Message {
    //RealTime Scheduling
    private boolean scheduledRealTime = false;

    public String getRealTime() {
        return realTime;
    }
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
            String[] time = realTime[i].split(":");
            int hours = Integer.valueOf(time[0]);
            int minutes = Integer.valueOf(time[1]);
            
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, hours);
            c.set(Calendar.MINUTE, minutes);
            c.set(Calendar.SECOND, 0);
            if(c.getTimeInMillis() < System.currentTimeMillis()) {
                c.add(Calendar.DAY_OF_MONTH, 1);
            }
            int initialTime = (int) ((c.getTimeInMillis() - System.currentTimeMillis())/ 1000);
            Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("MineCaster"), new Runnable() {
                public void run() {
                    broadcastMessage();
                }
            }, initialTime * 20L, 60 * 60 * 24 * 20L);            
        }
    }
    
    //Schedule Message with RealTime as a list
    public Message(String text, List<String> realTime) {
        //Save plaintext
        this.text = text;
        
        //Save WordArray
        this.words = text.split(" ");
        
        //Set booleans
        if(this.text.contains("&")) usingColor = true;
        if(this.text.contains("$")) usingVariable = true;
        
        //Schedule Annoucement
        //TODO Change for multi-realTime Support
        this.realTime = realTime.get(0);
        for (int i = 0; i < realTime.size(); i++) {
            String[] time = realTime.get(i).split(":");
            int hours = Integer.valueOf(time[0]);
            int minutes = Integer.valueOf(time[1]);
            
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, hours);
            c.set(Calendar.MINUTE, minutes);
            if(c.getTimeInMillis() < System.currentTimeMillis()) {
                c.add(Calendar.DAY_OF_MONTH, 1);
            }
            int initialTime = (int) ((c.getTimeInMillis() - System.currentTimeMillis())/ 1000);
            Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("MineCaster"), new Runnable() {
                public void run() {
                    broadcastMessage();
                }
            }, initialTime * 20L, 60 * 60 * 24 * 20L);            
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
        Broadcaster.broadcast(Main.prefix.getMessage(), message);
   }
    
   
   @Deprecated
   public String getMessageText() {
        return this.text;
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
   
    public void resetMessageText(String text) {
        //Save plaintext
        this.text = text;
        
        //Save WordArray
        this.words = text.split(" ");
        
        //Set booleans
        if(this.text.contains("&")) usingColor = true;
        if(this.text.contains("$")) usingVariable = true;
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
