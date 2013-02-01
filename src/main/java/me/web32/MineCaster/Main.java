/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.web32.MineCaster;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author maximiliansollner
 */
public class Main extends JavaPlugin{
    public int interval;
    public String AnnouncerPrefix;
    public Object[] messages;
    public int messagePointer;
    
    @Override
    public void onEnable() {
        //Check for the default configuration
        this.saveDefaultConfig();
        
        if(!getConfig().getBoolean("enabled")) {
            this.getPluginLoader().disablePlugin(this);
        }
        loadConfig();
               
        
    }
    
    @Override
    public void onDisable() {
        
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("minecaster") || cmd.getName().equalsIgnoreCase("mc")) {
            if(args.length > 1 && args[0].equalsIgnoreCase("announce")) {
                String message = null;
                for(int i = 0; (args.length - 1) > 0; i++) {
                    message = message + " " + args[i]; 
                }
                Broadcaster.broadcast(message);
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
}
