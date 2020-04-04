package com.github.JHXSMatthew.Controllers;

import com.github.JHXSMatthew.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIController {
    public static String teamChoseString = "选择队伍";
    private Inventory teamChoose;

    public GUIController() {
        try {
            register();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void register() {

        teamChoose = Bukkit.createInventory(null, 9, teamChoseString);
        for (int i = 0; i < 4; i++) {
            ItemStack wool = new ItemStack(Material.WOOL, 0, Main.getMsg().parseDyeColor(Main.getMsg().parseTeamName(i)).getData());
            ItemMeta meta = wool.getItemMeta();
            meta.setDisplayName(Main.getMsg().parseTeamName(i));
            wool.setItemMeta(meta);
            teamChoose.setItem(i + 1, wool);
        }
    }

    public void addTeamCount(String name) {
        for (ItemStack it : teamChoose.getContents()) {
            if (it != null && it.hasItemMeta() && it.getItemMeta().hasDisplayName()) {
                if (it.getItemMeta().getDisplayName().contains(name)) {
                    it.setAmount(it.getAmount() + 1);
                    break;
                }
            }
        }
    }

    public void subTeamCount(String name) {
        for (ItemStack it : teamChoose.getContents()) {
            if (it != null && it.hasItemMeta() && it.getItemMeta().hasDisplayName()) {
                if (it.getItemMeta().getDisplayName().contains(name)) {
                    if (it.getAmount() == 1) {
                        short data = it.getDurability();
                        int place = teamChoose.first(it);
                        String displayName = it.getItemMeta().getDisplayName();
                        it = new ItemStack(Material.WOOL, 0);
                        ItemMeta meta = it.getItemMeta();
                        meta.setDisplayName(displayName);
                        it.setItemMeta(meta);
                        it.setDurability(data);
                        teamChoose.setItem(place, it);
                    } else {
                        it.setAmount(it.getAmount() - 1);
                    }
                    break;
                }
            }
        }
    }

    public void returnToZero() {
        for (ItemStack it : teamChoose.getContents()) {
            if (it != null && it.hasItemMeta() && it.getItemMeta().hasDisplayName()) {

                it.setAmount(0);
            }
        }
    }

    public Inventory getTeamChoose() {

        return teamChoose;
    }
}
