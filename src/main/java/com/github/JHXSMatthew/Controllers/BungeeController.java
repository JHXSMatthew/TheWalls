package com.github.JHXSMatthew.Controllers;

import com.github.JHXSMatthew.Main;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class BungeeController implements Listener {


    public void quitSend(Player p) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(p.getName());
        p.sendPluginMessage(Main.get(), "LobbyConnect", out.toByteArray());
    }

    @EventHandler
    public void motdChanger(ServerListPingEvent evt) {
        if (Main.getGc().getGame() == null) {
            evt.setMotd(ChatColor.RED + "游戏中");
            return;
        }

        evt.setMotd(Main.getGc().getGame().getGameStateString());
    }
}
