package com.github.JHXSMatthew.Gui;

import com.github.JHXSMatthew.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class GuiClick implements Listener {
    private GuiManager guimanager;

    public GuiClick(GuiManager arg) {
        this.guimanager = arg;

    }


    public void excuteTask(Player p, int invID, int slot) {
        switch (this.guimanager.command.get(invID).get(slot)) {
            case "team0":
                if (Main.getGc().getGame() != null) {
                    Main.getGc().getGame().changeTeamTo(p, 0);
                } else {
                    p.sendMessage(com.github.JHXSMatthew.Config.Message.prefix + org.bukkit.ChatColor.RED + "游戏未初始化");
                }
                break;
            case "team1":
                if (Main.getGc().getGame() != null) {
                    Main.getGc().getGame().changeTeamTo(p, 1);
                } else {
                    p.sendMessage(com.github.JHXSMatthew.Config.Message.prefix + org.bukkit.ChatColor.RED + "游戏未初始化");
                }
                break;
            case "team2":
                if (Main.getGc().getGame() != null) {
                    Main.getGc().getGame().changeTeamTo(p, 2);
                } else {
                    p.sendMessage(com.github.JHXSMatthew.Config.Message.prefix + org.bukkit.ChatColor.RED + "游戏未初始化");
                }
                break;
            case "team3":
                if (Main.getGc().getGame() != null) {
                    Main.getGc().getGame().changeTeamTo(p, 3);
                } else {
                    p.sendMessage(com.github.JHXSMatthew.Config.Message.prefix + org.bukkit.ChatColor.RED + "游戏未初始化");
                }
                break;
            case "leave":
                Main.getBc().quitSend(p);
                break;
        }

    }

    @EventHandler
    public void onClick(InventoryClickEvent evt) {
        int i = 0;
        for (Inventory each : this.guimanager.getInventory()) {
            if (evt.getInventory().getName().toLowerCase().contains(each.getName())) {
                evt.setCancelled(true);
                if (evt.getRawSlot() < evt.getInventory().getSize()) {
                    if (!this.guimanager.command.get(i).get(evt.getSlot()).equals("")) {
                        //		Bukkit.dispatchCommand(evt.getWhoClicked(), this.guimanager.command.get(i).get(evt.getSlot()));
                        excuteTask((Player) (evt.getWhoClicked()), i, evt.getSlot());
                        evt.getWhoClicked().closeInventory();
                    }
                }
            }
            i++;
        }
    }


}
