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

package me.web32.mineVisual;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

/**
 *
 * @author web32
 */
public class StatisticEngine {
    /*
     * Time beetween the data is updated
     */
    private static final int UPDATE_TIME = 5;
    /*
     * The plugin the StatisticEngine is running in
     */
    private Plugin PLUGIN;
    /*
     * The StatisticEngine's version
     */
    private final int VERSION = 0;
    /*
    * The URL the data is loaded to
    */
    private final String CONNECTION_URL = "46.16.219.104";
    /*
    * The file on the server the data is loaded to
    */
    private final String DATA_HANDLER = "mine-visual/post.php";
    /*
     * The BukkitTask responsible for timing the uploadData method
     */
    private BukkitTask task;
    
    /*
     * Statistic Data
     */
    
    //SERVER RELATED
    private int PLAYER_COUNT = 0;
    private int ENTITY_COUNT = 0;
    private final String SERVER_ID = Bukkit.getServerId();
    private int WORLD_COUNT;
    private int PLUGIN_COUNT;
    private String SERVER_VERSION = Bukkit.getVersion();
    private int PORT = Bukkit.getServer().getPort();
    private boolean start = true;
    
    //SYSTEM RELATED
    private String OS_NAME = System.getProperty("os.name");
    private String OS_ARCH = System.getProperty("os.arch");
    private String OS_VERSION = System.getProperty("os.version");
    private int OS_PC_COUNT = Runtime.getRuntime().availableProcessors();
    private String JAVA_VERSION = System.getProperty("java.version");
    private String JAVA_LANGUAGE = System.getProperty("user.language");
    
    
    
    /*
     * Get a new StatisticEngine object
     */

    public StatisticEngine(Plugin plugin) {
        //Check if another instance of the StatisticEngine is running already
        if(Bukkit.getPluginManager().getPermission("statistic.enabled") != null) {
            return;
        } else {
            Bukkit.getPluginManager().addPermission(new Permission("statistic.enabled"));
        }
        
        PLUGIN = plugin;
        
        start();
    }
    /*
     * Activate the Data Collector
     */
    public final void start() {
        //Schedule the BukkitTask
        task = Bukkit.getScheduler().runTaskTimer(PLUGIN, new Runnable() {
            public void run() {
                try {
                    saveData();
                    uploadData();
                } catch (MalformedURLException ex) {
                    Logger.getLogger(StatisticEngine.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(StatisticEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, 10 * 20L, UPDATE_TIME * 60 * 20L);
    }
    /*
     * Stop Data Collecting
     */
    public void stop() {
        task.cancel();
    }
    
    /*
     * Collect the data and save it
     */
    private void saveData() {
        PLAYER_COUNT = Bukkit.getOnlinePlayers().length;
        WORLD_COUNT = Bukkit.getWorlds().size();
        PLUGIN_COUNT = Bukkit.getPluginManager().getPlugins().length;
        //Set the ENTITY_COUNT
        ENTITY_COUNT = 0;
        for (World world : Bukkit.getWorlds()) {
            ENTITY_COUNT = ENTITY_COUNT + world.getLivingEntities().size();
        }
    }
    
    /*
     * 
     */
    private boolean uploadData() throws MalformedURLException, IOException {
        String output = "PC=" + URLEncoder.encode(String.valueOf(PLAYER_COUNT), "UTF-8") + 
                "&" + "V=" + URLEncoder.encode(String.valueOf(VERSION), "UTF-8") + 
                "&" + "SP=" + URLEncoder.encode(String.valueOf(PORT), "UTF-8") + 
                "&" + "EC=" + URLEncoder.encode(String.valueOf(ENTITY_COUNT), "UTF-8") + 
                "&" + "SID=" + URLEncoder.encode(SERVER_ID, "UTF-8") +
                "&" + "WC="  + URLEncoder.encode(String.valueOf(WORLD_COUNT), "UTF-8") +
                "&" + "PLC=" + URLEncoder.encode(String.valueOf(PLUGIN_COUNT), "UTF-8") +
                "&" + "SV=" + URLEncoder.encode(SERVER_VERSION, "UTF-8") +
                "&" + "ON=" + URLEncoder.encode(OS_NAME, "UTF-8") + 
                "&" + "OA=" + URLEncoder.encode(OS_ARCH, "UTF-8") + 
                "&" + "OV=" + URLEncoder.encode(OS_VERSION, "UTF-8") + 
                "&" + "OPC=" + URLEncoder.encode(String.valueOf(OS_PC_COUNT), "UTF-8") +
                "&" + "JV=" + URLEncoder.encode(JAVA_VERSION, "UTF-8") +
                "&" + "JL=" + URLEncoder.encode(JAVA_LANGUAGE, "UTF-8");
        
        for (int i = 0; i < Bukkit.getPluginManager().getPlugins().length; i++) {
            output = output + "&" + "PI" + i + "=" + URLEncoder.encode(Bukkit.getPluginManager().getPlugins()[i].getName(), "UTF-8");
            output = output + "&" + "PV" + i + "=" + URLEncoder.encode(Bukkit.getPluginManager().getPlugins()[i].getDescription().getVersion(), "UTF-8");
        }
        if(start) {
            output = output + "&" + "SS=" + URLEncoder.encode("true", "UTF-8");
            start = false;
        }
        
        //WIKI
        for(int i = 0;i < Bukkit.getPluginManager().getPlugins().length; i++) {
            if((int) Math.random() * 1 != 1) {
                output = output + "&" + "WKE=" + URLEncoder.encode("TRUE", "UTF-8");
                output = output + "&" + "WPN=" + URLEncoder.encode(String.valueOf(i), "UTF-8");
                
                //SET AUTHORS
                String authors = "";
                for (String s : Bukkit.getPluginManager().getPlugins()[i].getDescription().getAuthors()) {
                    if(authors.isEmpty()) {
                        authors = s;
                    } else {
                        authors = authors + ";" + s;
                    }
                }
                output = output + "&" + "WA=" + URLEncoder.encode(authors, "UTF-8");
                if(Bukkit.getPluginManager().getPlugins()[i].getDescription().getWebsite() != null) {
                    output = output + "&" + "WW=" + URLEncoder.encode(Bukkit.getPluginManager().getPlugins()[i].getDescription().getWebsite(), "UTF-8");
                }
                if(Bukkit.getPluginManager().getPlugins()[i].getDescription().getDescription() != null) {
                    output = output + "&" + "WD=" + URLEncoder.encode(Bukkit.getPluginManager().getPlugins()[i].getDescription().getDescription(), "UTF-8");
                }
                if(Bukkit.getPluginManager().getPlugins()[i].getDescription().getPrefix() != null) {
                    output = output + "&" + "WP=" + URLEncoder.encode(Bukkit.getPluginManager().getPlugins()[i].getDescription().getPrefix(), "UTF-8");
                }
                
                //COMMANDS
                Plugin WikiPlugin = Bukkit.getPluginManager().getPlugins()[i];
                
                Map<String, Map<String, Object>> commands =  WikiPlugin.getDescription().getCommands();
                int y = 0;
                for (String s : commands.keySet()) {
                    output = output + "&" + "WC" + y + "=" + URLEncoder.encode(s, "UTF-8");
                    Map<String, Object> attributes = commands.get(s);
                    for(String at : attributes.keySet()) {
                        if(at.equalsIgnoreCase("description")) {
                            output = output + "&" + "WCD" + y + "=" + URLEncoder.encode(String.valueOf(attributes.get(at)), "UTF-8");
                        }
                        if(at.equalsIgnoreCase("aliases")) {
                            output = output + "&" + "WCA" + y + "=" + URLEncoder.encode(String.valueOf(attributes.get(at)), "UTF-8");
                        }
                        if(at.equalsIgnoreCase("usage")) {
                            output = output + "&" + "WCU" + y + "=" + URLEncoder.encode(String.valueOf(attributes.get(at)), "UTF-8");
                        }
                        if(at.equalsIgnoreCase("permission")) {
                            if(Bukkit.getPluginCommand(s).getPermission() != null) {
                                output = output + "&" + "WCP" + y + "=" + URLEncoder.encode(String.valueOf(attributes.get(at)), "UTF-8");
                            } else {
                                output = output + "&" + "WCP" + y + "=" + URLEncoder.encode(String.valueOf(attributes.get(at)), "UTF-8");
                            }
                        }
                        
                    }
                    y++;
                }
                //break;
            }
        }
        System.out.println(output);
        
        URLConnection conn = new URL("http://" + CONNECTION_URL + "/" + DATA_HANDLER).openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setRequestProperty( "Content-Type","application/x-www-form-urlencoded" );
        conn.setRequestProperty( "Content-Length", String.valueOf(output.length()) );
        
        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
        
        writer.write(output);
        writer.flush();              
        writer.close();

        return true;
    }
}
