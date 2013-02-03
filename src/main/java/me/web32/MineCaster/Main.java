/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.web32.MineCaster;

import com.sun.corba.se.impl.logging.OMGSystemException;
import java.io.IOException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.mcstats.Metrics;
import sun.security.timestamp.TSRequest;

/**
 *
 * @author web32
 */
public class Main extends JavaPlugin{
    public int interval;
    public String AnnouncerPrefix;
    public Object[] messages;
    public int messagePointer = 0;
    
    private BukkitTask task;
    
    @Override
    public void onEnable() {
        //TEST
        Message test = new Message("&1Test &2 Test &3 Test");
        System.out.println(test.getMessage());
        
        //Check for the default configuration
        this.saveDefaultConfig();
        
        if(!getConfig().getBoolean("enabled")) {
            this.getPluginLoader().disablePlugin(this);
        }
        loadConfig();
        
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
            if(args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                this.loadConfig();
                sender.sendMessage("The configuration file was reloaded!");
                return true;
            }
            //In-Game Setting-Commands
            //Interval
            if(args.length == 2 && args[0].equalsIgnoreCase("interval")) {
                int newInterval = new Integer(args[1]);
                getConfig().set("interval", newInterval);
                this.saveConfig();
                task.cancel();
                scheduleTimer();
                sender.sendMessage("The broadcasting interval is now " + getConfig().getString("interval") + " seconds.");
                return true;
            }
            //Prefix
            if(args.length == 2 && args[0].equalsIgnoreCase("prefix")) {
                getConfig().set("prefix", args[1]);
                this.saveConfig();
                sender.sendMessage("The broadcasting prefix was set " + getConfig().getString("prefix") + ".");
                return true;
            }
  
            //Manual announce Command
            if(args.length > 1 && args[0].equalsIgnoreCase("announce")) {
                Broadcaster.broadcast(AnnouncerPrefix, args);
                return true;
            }
        }
       return false;
    }
        
    
    public void loadConfig() {
        interval = getConfig().getInt("interval");
        AnnouncerPrefix = getConfig().getString("prefix");
        messages = getConfig().getStringList("messages").toArray();
    }
    
    public void scheduleTimer() {
            int repeatingInterval = interval*20;
            task = this.getServer().getScheduler().runTaskTimerAsynchronously(this, new Runnable() {
                @Override  
                public void run() {
                    if(getConfig().getBoolean("random")) {
                        int random = (int) ((Math.random() * messages.length) - 1);
                        Broadcaster.broadcast(AnnouncerPrefix, messages[random].toString());
                    } else {
                        if(messagePointer == messages.length) {
                            messagePointer = 0;
                        }
                        Broadcaster.broadcast(AnnouncerPrefix, messages[messagePointer].toString());
                        messagePointer++;
                        
                    }
               }
            }, 120L, repeatingInterval);
    }
}
