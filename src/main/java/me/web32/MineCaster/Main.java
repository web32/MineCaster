/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.web32.MineCaster;

import java.io.IOException;
import javax.swing.SwingUtilities;
import org.bukkit.Bukkit;
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
    public static Graph playerGraph;
    public static Message AnnouncerPrefix;
    public static Message[] messages = new Message[50];
    public int messagePointer = 0;
    public static String PluginVersion = "0.4pre";
    public static GUI gui;
    
    private BukkitTask task;
    
    //Settings
    public static boolean enabled;
    public static boolean Random;
    public static int interval;
    public static String prefix;   
    
    @Override
    public void onEnable() {
        //Initilize Graphs
        playerGraph = new Graph("Number of " + ChatColor.DARK_RED + "players " + ChatColor.WHITE + "in the last " + ChatColor.GOLD + "24h");
        schedulePlayerGraphEngine();
        
        //Save the default configuration file
        this.saveDefaultConfig();
        
        if(!getConfig().getBoolean("enabled")) {
            this.getPluginLoader().disablePlugin(this);
        }
        loadConfiguration();
        
        if(getConfig().getBoolean("automated")) {
            scheduleTimer();
        }
        
        //Activate MC-Metrics
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
        }
    }
    
    @Override
    public void onDisable() {
        task.cancel();
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("minecaster") || cmd.getName().equalsIgnoreCase("mc")) {
            //Configuration Reloading
            if(args.length == 1 && args[0].equalsIgnoreCase("reload") && sender.hasPermission("minecaster.manage.reload")) {
                this.loadConfiguration();
                sender.sendMessage("The configuration file was reloaded!");
                return true;
            }
            //In-Game Setting-Commands
            //Interval
            if(args.length == 2 && args[0].equalsIgnoreCase("interval") && sender.hasPermission("minecaster.manage.interval")) {
                int newInterval = new Integer(args[1]);
                getConfig().set("interval", newInterval);
                this.saveConfig();
                task.cancel();
                scheduleTimer();
                sender.sendMessage("The broadcasting interval is now " + getConfig().getString("interval") + " seconds.");
                return true;
            }
            //Prefix
            if(args.length == 2 && args[0].equalsIgnoreCase("prefix") && sender.hasPermission("minecaster.manage.prefix")) {
                getConfig().set("prefix", args[1]);
                this.saveConfig();
                sender.sendMessage("The broadcasting prefix was set " + getConfig().getString("prefix") + ".");
                return true;
            }
  
            //Manual announce Command
            if(args.length > 1 && args[0].equalsIgnoreCase("announce") && sender.hasPermission("minecaster.announce")) {
                Broadcaster.broadcast(AnnouncerPrefix.getMessage(), args);
                return true;
            }
            //Help Command
            if(args.length == 1 && args[0].equalsIgnoreCase("help")) {
                sender.sendMessage(ChatColor.AQUA + "MineCaster Help:");
                sender.sendMessage("/mc announce <message>");
                sender.sendMessage("/mc interval <interval>");
                sender.sendMessage("/mc prefix <prefix>");
                sender.sendMessage("/mc reload");
                return true;
            }
            if(args.length == 1 && args[0].equalsIgnoreCase("save")) {
                saveConfiguration();
                reloadConfig();
            }
            //GUI COMMANDS
            //gui open Command
            if(args.length == 1 && args[0].equalsIgnoreCase("gui")) {
                SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    gui = new GUI();
                    gui.setVisible(true);
                }
            });
            return true;   
            }
        }
       return false;
    }
        
    
    public void loadConfiguration() {
        interval = getConfig().getInt("interval");
        AnnouncerPrefix = new Message(getConfig().getString("prefix"));
        Random = getConfig().getBoolean("random");
        enabled = getConfig().getBoolean("enabled");
        Object[] announcements = getConfig().getStringList("messages").toArray();
        messages = new Message[announcements.length];
        for (int i = 0; i < announcements.length; i++) {
            messages[i] = new Message(announcements[i].toString());
        }
    }
    
    public void saveConfiguration() {
        getConfig().set("automated", enabled);
        getConfig().set("random", Random);
        getConfig().set("interval", interval);
        getConfig().set("prefix", AnnouncerPrefix.text);
        saveConfig();
    }
    
    public void scheduleTimer() {
            int repeatingInterval = interval*20;
            task = this.getServer().getScheduler().runTaskTimerAsynchronously(this, new Runnable() {
                @Override  
                public void run() {
                    Random = getConfig().getBoolean("random");
                    if(getConfig().getBoolean("random")) {
                        int random = (int) Math.random() * messages.length;
                        Broadcaster.broadcast(AnnouncerPrefix.getMessage(), messages[random].getMessage());
                    } else {
                        if(messagePointer == messages.length) {
                            messagePointer = 0;
                        }
                        Broadcaster.broadcast(AnnouncerPrefix.getMessage(), messages[messagePointer].getMessage());
                        messagePointer++;
                    }
               }
            }, 120L, repeatingInterval);
    }
    
    public void schedulePlayerGraphEngine() {
                BukkitTask graphTask = this.getServer().getScheduler().runTaskTimerAsynchronously(this, new Runnable() {
                @Override  
                public void run() {
                    playerGraph.addData(Bukkit.getOnlinePlayers().length);
               }
            }, 120L, 20 * 60 * 60);
    }
}
