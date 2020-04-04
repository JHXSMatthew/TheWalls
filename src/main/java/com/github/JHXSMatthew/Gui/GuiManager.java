package com.github.JHXSMatthew.Gui;

import com.github.JHXSMatthew.Main;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GuiManager {
    public ItemManager manager;
    public ArrayList<ArrayList<String>> command = new ArrayList<ArrayList<String>>();
    private ArrayList<Inventory> inv = new ArrayList<Inventory>();
    private GuiClick ge;

    public GuiManager(ItemManager arg) {
        //	System.out.println("1-");
        this.manager = arg;
        //	System.out.println("2-");
        this.ge = new GuiClick(this);
        //	System.out.println("3-");
        Main.get().getServer().getPluginManager().registerEvents(this.ge, Main.get());
        //	System.out.println("4-");
    }


    public ArrayList<Inventory> getInventory() {
        return this.inv;
    }

    public int addInv(int size, String name) {
        this.inv.add(Bukkit.createInventory(null, size, name.replace("&", "§")));
        int s = this.command.size();
        this.command.add(new ArrayList<String>());
        for (int i = 0; i < size; i++) {
            this.command.get(s).add("");
        }
        return inv.size() - 1;
    }

    public void addItem(int invID, int slot, Material material, int num, DyeColor dyeColor, String name, ArrayList<String> lore, String command) {

        ItemStack item;
        System.out.println("1");
        if (material.equals(Material.WOOL)) {
            item = new ItemStack(material, num, dyeColor.getData());
        } else {
            item = new ItemStack(material, num);
        }

        ItemMeta meta = item.getItemMeta();
        //	System.out.println("2");
        meta.setLore(lore);
        //	System.out.println("3");
        meta.setDisplayName(name);
        //System.out.println("4");
        item.setItemMeta(meta);
        //System.out.println("5");
        this.inv.get(invID).setItem(slot, item);
        //	System.out.println("6");
        this.command.get(invID).set(slot, command);
        //	System.out.println("7");
    }

    public void openGui(int invID, Player p) {
        p.openInventory(this.inv.get(invID));
    }


    public int getInvID(String invName) {
        int i = 0;
        for (Inventory each : this.inv) {
            if (each.getName().contains(invName)) {
                return i;
            }
            i++;
        }
        return -1;
    }


}
