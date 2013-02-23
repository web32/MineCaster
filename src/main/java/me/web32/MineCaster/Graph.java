/* The file Graph.java was created 09.02.2013
* at 14:13:49. Copyrightholder is Maximilian Söllner alias web32.
*/

package me.web32.MineCaster;

import java.util.ArrayList;
import org.bukkit.Bukkit;

/**
 *
 * @author web32
 */
public class Graph {
    private ArrayList<Integer> values = new ArrayList<Integer>();
    private String header;
    private int highestValue;

    public Graph(String headString) {
        header = headString;
    }
    
    public void addData(int value) {
        values.add(value);
        int i;
        if(values.size() > 24) {
        i = values.size() - 24;
    } else {
        i = 0;
    }
        while (i < values.size()) {
            if(values.get(i) > highestValue) {
                highestValue = value;
            }
            i++;
        }
    }
    
    private String getLine(int line) {
    String output = "";
    int i;
    if(values.size() > 24) {
        i = values.size() - 24;
    } else {
        i = 0;
    }
    while(i < values.size()) {
            if(values.get(i) > (highestValue / 4) * (line - 1)) {
                output = output + "O";
            } else {
                output = output + "-";
            }     
        i++;
    }
    return output;
    }
    
    public void drawGraph() {
        Broadcaster.broadcast(Main.prefix.getMessage(),header);
        Broadcaster.broadcast(Main.prefix.getMessage(),highestValue + "|" + getLine(4));
        Broadcaster.broadcast(Main.prefix.getMessage(),highestValue/4*3 + "|" + getLine(3));
        Broadcaster.broadcast(Main.prefix.getMessage(),highestValue/2 + "|" + getLine(2));
        Broadcaster.broadcast(Main.prefix.getMessage(),highestValue/4*1 + "|" + getLine(1));
    }
    
    /*
     * DataCollectors
     */
    //PlayerCount data collector
    public void getPlayerCountDataCollector() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("MineCaster"), new Runnable() {
            public void run() {
                addData(Bukkit.getOnlinePlayers().length);
            }
        }, 120L, 60 * 60 * 20L);
    }
    
    
    
}
