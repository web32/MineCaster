/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.web32.MineCaster;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 *
 * @author maximiliansollner
 */
public class Broadcaster {
    public static void broadcast(String message) {
        Player[] players = Bukkit.getOnlinePlayers();
        for(int i = 0; players[i] != null; i++) {
            players[i].chat(message);
        }
    }
    
}
