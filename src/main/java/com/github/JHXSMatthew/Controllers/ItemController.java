package com.github.JHXSMatthew.Controllers;

import com.github.JHXSMatthew.Game.GamePlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemController implements Listener {

    private ItemStack optionItem;
    private ItemStack quitItem;
    private ItemStack kitsItem;


    public ItemController() {

        try {
            register();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    public boolean isTeamChoseItem(ItemStack item) {
        return optionItem.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName());
    }

    public boolean isQuitItem(ItemStack item) {
        //	System.out.print(quitItem.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName()));
        return quitItem.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName());
    }

    public boolean isKitsItem(ItemStack item) {
        return kitsItem.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName());
    }

    public void giveLobbyItem(GamePlayer p) {

        p.get().getInventory().setItem(0, optionItem);
        p.get().getInventory().setItem(1, kitsItem);
        p.get().getInventory().setItem(8, quitItem);
    }


    private void register() {
        //option item
        optionItem = new ItemStack(Material.WATCH);
        ItemMeta meta = optionItem.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "选择队伍");
        optionItem.setItemMeta(meta);

        //leave Item
        quitItem = new ItemStack(Material.BARRIER);
        meta = optionItem.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "离开");
        quitItem.setItemMeta(meta);

        //kits Item
        kitsItem = new ItemStack(Material.GOLD_SWORD);
        meta = kitsItem.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "职业");
        kitsItem.setItemMeta(meta);

    }


}
