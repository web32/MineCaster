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

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import me.web32.MineCaster.utility.xmlConfigurationManager;
import me.web32.mineVisual.StatisticEngine;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

/**
 *
 * @author web32
 */
public class Main extends JavaPlugin{
    //Configuration Variables
    public static boolean enabled = false;
    public static boolean random = false;
    public static int interval;
    
    public static Message prefix;
    public static ArrayList<Message> messages = new ArrayList<Message>();;
    private static int messagePointer = 0;
    public static ArrayList<Message> realTimeMessages = new ArrayList<Message>();;
    public static Graph playerCountGraph;
    
    public static GUI gui;
    public static xmlConfigurationManager xml;
    public static BukkitTask broadcastingSchedueler;
    
    @Override
    public void onEnable() {
        
        //Save default configuration files
       new File("plugins/MineCaster").mkdir();
       File config = new File("plugins/MineCaster/config.xml");
        if(!config.exists()) {
            InputStream is = getResource("config.xml");
            try {
                OutputStream os = new FileOutputStream(config);

                int read = 0;
                byte[] bytes = new byte[1024];
                try {
                    while ((read = is.read(bytes)) != -1) {
                        try {
                            os.write(bytes, 0, read);
                        } catch (IOException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }  
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           
        //Initialize and load XMl-CONFIGURATION-HANDLER
        xml = new xmlConfigurationManager();
        try {
            xml.loadConfiguration("plugins/MineCaster/config.xml");
        } catch (XMLStreamException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        //Activate Timer
        scheduleMessageTimer();
        
        //Initialize GUI
        gui = new GUI();

        /*
         * Initialize Graphs
         */
        //PlayerCount Graph
        playerCountGraph = new Graph("Number of " + ChatColor.DARK_RED + "players " + ChatColor.WHITE + "in the last " + ChatColor.GOLD + "24h");
        playerCountGraph.getPlayerCountDataCollector();
        
        //Activate MC.Statistics
        StatisticEngine engine = new StatisticEngine(this);   
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("minecaster") || cmd.getName().equalsIgnoreCase("mc")) {
            //Configuration Reloading
            if(args.length == 1 && args[0].equalsIgnoreCase("reload") && sender.hasPermission("minecaster.manage.reload")) { 
                try {
                    xml.loadConfiguration("plugins/MineCaster/config.xml");
                } catch (XMLStreamException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                sender.sendMessage("The configuration file was reloaded!");
                return true;
            }
            //In-Game Setting-Commands
            /*
             * In-Game Message Managing System
             */
            //List
            if(args.length >= 1 && args[0].equalsIgnoreCase("list") && sender.hasPermission("minecaster.message.list")) {
                //Read the messages which are loaded in the messages-array
                int page = 1;
                int pages = (Main.messages.size() / 5) + 1;
                if(args.length == 1) {
                    sender.sendMessage(ChatColor.AQUA + "Loaded messages (Page " + ChatColor.BOLD + page + ChatColor.RESET + ChatColor.AQUA + " of " + ChatColor.BOLD + pages + ChatColor.RESET + ChatColor.AQUA + ")");
                    for (int i = 0; i <= 5 && i < messages.size(); i++) {
                        sender.sendMessage(ChatColor.GOLD + String.valueOf(i+1) + " | " + ChatColor.RESET + ChatColor.GRAY + messages.get(i).getMessage());
                    }
                    return true;
                } else if(args.length == 2) {
                    page = Integer.parseInt(args[1]);
                    sender.sendMessage(ChatColor.AQUA + "Loaded messages (Page " + ChatColor.BOLD + page + ChatColor.RESET + ChatColor.AQUA + " of " + ChatColor.BOLD + pages + ChatColor.RESET + ChatColor.AQUA + ")");
                    for (int i = 0 + 5*(page-1); i <= 5 + 5*(page-1) && i < messages.size(); i++) {
                        sender.sendMessage(ChatColor.GOLD + String.valueOf(i+1) + " | " + ChatColor.RESET + ChatColor.GRAY + messages.get(i).getMessage());
                    }
                    return true;
                } else {
                    return false;
                }
            }
            //Add Message
            if(args.length >= 2 && args[0].equalsIgnoreCase("add") && sender.hasPermission("minecaster.manage.add")) {
                try {
                    String message = "";
                    for (int i = 1; i < args.length; i++) {
                        message = message + args[i];
                    }
                    messages.add(new Message(message));
                    xml.saveMessages(messages);
                    sender.sendMessage("Message added!");
                    return true;
                } catch (TransformerException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //Remove Message
            if(args.length == 2 && args[0].equalsIgnoreCase("remove") && sender.hasPermission("minecaster.manage.remove")) {
                try {
                    if(messages.size() < Integer.parseInt(args[1])) {
                            sender.sendMessage("The message you selected is empty!");
                            return false;
                    }
                    messages.remove(Integer.parseInt(args[1]) - 1);
                    xml.saveMessages(messages);
                    sender.sendMessage("Message " + args[1] + " was deleted.");
                    return true;
                } catch (TransformerException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    sender.sendMessage("Deletion of the selected message failed!");
                    return false;
                }
                
            }
            
            //Interval
            if(args.length == 2 && args[0].equalsIgnoreCase("interval") && sender.hasPermission("minecaster.manage.interval")) {
                int newInterval = new Integer(args[1]);
                Main.interval = newInterval;
                xml.saveSettings("plugins/MineCaster/config.xml");
                sender.sendMessage("The broadcasting interval is now " + getConfig().getString("interval") + " seconds.");
                return true;
            }
            //Prefix
            if(args.length == 2 && args[0].equalsIgnoreCase("prefix") && sender.hasPermission("minecaster.manage.prefix")) {
                Main.prefix.resetMessageText(args[1]);
                xml.saveSettings("plugins/MineCaster/config.xml");
                sender.sendMessage("The broadcasting prefix was set " + Main.prefix.getMessageText() + ".");
                return true;
            }
  
            //Manual announce Command
            if(args.length > 1 && args[0].equalsIgnoreCase("announce") && sender.hasPermission("minecaster.announce")) {
                Broadcaster.broadcast(prefix.getMessage(), args);
                return true;
            }
            //Help Command
            if(args.length == 1 && args[0].equalsIgnoreCase("help")) {
                sender.sendMessage(ChatColor.AQUA + "MineCaster Help:");
                sender.sendMessage("/mc gui");
                sender.sendMessage("/mc announce <message>");
                sender.sendMessage("/mc interval <interval>");
                sender.sendMessage("/mc prefix <prefix>");
                sender.sendMessage("/mc reload");
                return true;
            }
            //GUI COMMANDS
            //gui open Command
            if(args.length == 1 && args[0].equalsIgnoreCase("gui") && sender.hasPermission("minecaster.gui")) {
                SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    gui.setVisible(true);
                }
            });
            return true;   
            }
            //DEBUG Command
            if(args.length == 1 && args[0].equalsIgnoreCase("debug")) {
                sender.sendMessage("MineCaster Debug Information:");
                sender.sendMessage("Loaded Messages:");
                sender.sendMessage("Normal: " + messages.size());
                sender.sendMessage("RealTime: " + realTimeMessages.size());
                return true;
            }
        }
       return false;
    }
    
    public void scheduleMessageTimer() {
            int repeatingInterval = interval * 20;
            broadcastingSchedueler = this.getServer().getScheduler().runTaskTimerAsynchronously(this, new Runnable() {
                @Override  
                public void run() {
                    if(messages.size() < 1) {
                        return;
                    } else if (getServer().getOnlinePlayers().length == 0) {
                        return;
                    }
                    if(random) {
                        int randomInt = Math.round((float) Math.random() * (messages.size()-1));
                        Broadcaster.broadcast(prefix.getMessage(), messages.get(randomInt).getMessage());
                    } else {
                        if(messagePointer == messages.size()) {
                            messagePointer = 0;
                        }
                        Broadcaster.broadcast(prefix.getMessage(), messages.get(messagePointer).getMessage());
                        messagePointer++;
                    }
               }
            }, 200L, repeatingInterval);
    }
    
    public static void removeMessage(String message) {
        for (int i = 0; i < messages.size(); i++) {
            if(message.equalsIgnoreCase(messages.get(i).getMessageText())) {
                messages.remove(i);
            }           
        }       
    }
}
