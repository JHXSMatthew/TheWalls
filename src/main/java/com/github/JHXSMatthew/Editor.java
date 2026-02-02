package com.github.JHXSMatthew;

import lombok.Data;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
@Data
public class Editor {
    private boolean Bound;
    private boolean PlayerCount;
    private boolean Walltime;
    private boolean Percentage;
    private boolean Lobby;
    private boolean Builder;
    private int Wall;
    private int Spawn;

    public Editor() {
        Bound = false;
        PlayerCount = false;
        Walltime = false;
        Percentage = false;  // 修复：默认应该是false，表示未设置
        Lobby = false;
        Builder = false;
        Wall = 0;
        Spawn = 0;
    }

    public void sendEditor(Player p){
        p.sendMessage(ChatColor.AQUA+"=========[TheWall]=========");
        p.sendMessage(ChatColor.YELLOW+"区域设置: "+B2S(Bound));
        p.sendMessage(ChatColor.YELLOW+"玩家数量设置: "+B2S(PlayerCount));
        p.sendMessage(ChatColor.YELLOW+"等待时间: "+B2S(Walltime));
        p.sendMessage(ChatColor.YELLOW+"大厅设置: "+B2S(Lobby));
        p.sendMessage(ChatColor.YELLOW+"建筑师设置: "+B2S(Builder));
        p.sendMessage(ChatColor.YELLOW+"城墙数量: "+Wall);
        p.sendMessage(ChatColor.YELLOW+"出声点数量: "+Spawn+"/4"+ChatColor.GRAY+"(顺序请按红,黄,蓝,绿)");
        p.sendMessage(ChatColor.AQUA+"===========================");
    }

    private String B2S(boolean b){
        if(b) {
            return "§a完成";
        }else {
            return "§c未完成";
        }
    }
    
    public void setPercentage(boolean percentage) {
        Percentage = percentage;
    }

}
