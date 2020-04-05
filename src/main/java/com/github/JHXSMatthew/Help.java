package com.github.JHXSMatthew;

import org.bukkit.entity.Player;

public class Help {
    private String[] helps;

    public Help(String... helps) {
        this.helps = helps;
    }

    public void sendHelp(Player p){
        for (String help : helps) {
            p.sendMessage(help);
        }
    }
}
