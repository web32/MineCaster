/* The file ymlConfigurationManager.java was created 16.02.2013 at 16:43:40.
* Copyright (c) 2013 Maximilian Söllner alias web32. All rights reserved.
*/

package me.web32.MineCaster.utility;

import me.web32.MineCaster.Main;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author web32
 */
public class ymlConfigurationManager {
    public static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("MineCaster");

    public ymlConfigurationManager() {
    }
 
    
    
    public void loadConfiguration() {
        Main.enabled = plugin.getConfig().getBoolean("enabled");
        Main.random = plugin.getConfig().getBoolean("random");
        
        Main.interval = plugin.getConfig().getInt("interval");   
    }

}
