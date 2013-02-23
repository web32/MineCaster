/* The file Main.java was created.
* Copyright (c) 2013 Maximilian Söllner alias web32. All rights reserved.
*/

package me.web32.MineCaster;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.xml.stream.XMLStreamException;
import me.web32.MineCaster.utility.xmlConfigurationManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.mcstats.Metrics;

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
        
        //Activate MC-Metrics
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
        }
        
    }

    @Override
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
                    if(messages.size() < 1) return;
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
