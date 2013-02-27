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
