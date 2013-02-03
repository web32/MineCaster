/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.web32.MineCaster;

import org.bukkit.Bukkit;

/**
 *
 * @author web32
 */
public class Broadcaster {
    public static void broadcast(String prefix, String[] words) {
        String message = prefix;
        for(int i = 1; i < words.length; i++) {
            message = message + " " + words[i];
        }
        Bukkit.getServer().broadcastMessage(message);
    }
    
    public static void broadcast(String prefix, String message) {
        Bukkit.getServer().broadcastMessage(prefix + " " + message);
    }
    
}
