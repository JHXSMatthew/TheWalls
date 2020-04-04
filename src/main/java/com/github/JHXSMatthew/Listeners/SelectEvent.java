package com.github.JHXSMatthew.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.logging.Logger;


public class SelectEvent implements Listener {

    public Location left;
    public Location right;
    ItemStack wandinfo;
    Logger logger = Logger.getLogger("Minecraft");
    boolean reg = false;


    public boolean isReg() {
        return reg;
    }

    //Wand creation and gives
    public ItemStack createWand() {
        ItemStack wand = new ItemStack(Material.DIAMOND_AXE, 1);
        ItemMeta wandMeta = wand.getItemMeta();
        ArrayList<String> wandArray = new ArrayList<String>();
        wandMeta.setDisplayName(ChatColor.GOLD + "Walls Wand");
        wandMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
        wandArray.add(ChatColor.GREEN + "The magic wand");
        wandArray.add(ChatColor.GREEN + "Use it to select area");
        wandArray.add(ChatColor.RED + "Left Click/Right click");
        wandMeta.setLore(wandArray);
        wand.setItemMeta(wandMeta);
        this.wandinfo = wand;
        return wand;
    }

    public void getWand(Player player) {
        player.getInventory().addItem(new ItemStack(createWand()));
    }

    public void clear() {
        left = null;
        right = null;
    }

    //select events
    @EventHandler
    public void OnPlayerInteract(PlayerInteractEvent evt) {
        try {
            //this.logger.info("Interact-wand");
            //	this.logger.info(this.wandinfo.toString());
            //	this.logger.info(evt.getItem().toString());
            if (!evt.getAction().equals(Action.LEFT_CLICK_BLOCK) && !evt.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                return;
            }
            if (evt.getPlayer().getItemInHand() == null) {
                return;
            }
            if (evt.getItem().equals(this.wandinfo)) {
                //		this.logger.info("Interact-wand-itemstackpass");
                if (evt.getAction() == Action.LEFT_CLICK_BLOCK) {
                    this.left = evt.getClickedBlock().getLocation();
                    evt.getPlayer().sendMessage(ChatColor.DARK_RED + "First Point Set, position:" + left.getBlockX() + " " + left.getBlockY() + " " + left.getBlockZ());
                } else if (evt.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    this.right = evt.getClickedBlock().getLocation();
                    evt.getPlayer().sendMessage(ChatColor.DARK_RED + "Second Point Set, position:" + right.getBlockX() + " " + right.getBlockY() + " " + right.getBlockZ());

                }
                evt.setCancelled(true);
            }
        } catch (Exception e) {

        }
    }
}
