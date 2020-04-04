package com.github.JHXSMatthew.Controllers;

import com.github.JHXSMatthew.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Random;

public class ChestControl {
    Main plugin;
    HashMap<Integer, ItemStack> loot = new HashMap<Integer, ItemStack>();


    HashMap<Integer, ItemStack> lucky = new HashMap<Integer, ItemStack>();


    private int index;
    private int indexLucky;

    public ChestControl(Main main) {
        this.plugin = main;
        this.index = 0;
        ItemStack item = new ItemStack(Material.WOOD_AXE);
        ItemMeta meta = item.getItemMeta();


        meta.setDisplayName(ChatColor.AQUA + "火鸟之心");
        meta.addEnchant(Enchantment.FIRE_ASPECT, 1, false);
        addItem(Material.WOOD_SWORD, (short) 0, 1, meta);


        addItem(Material.DIAMOND, (short) 0, 1, null);

        addItem(Material.IRON_INGOT, (short) 0, 3, null);

        addItem(Material.WOOD, (short) 0, 12, null);

        addItem(Material.PISTON_BASE, (short) 0, 2, null);

        addItem(Material.DIAMOND_HELMET, (short) 0, 1, null);

        addItem(Material.BREAD, (short) 0, 4, null);

        addItem(Material.CAKE, (short) 0, 1, null);

        addItem(Material.ARROW, (short) 0, 16, null);

        addItem(Material.LEATHER_BOOTS, (short) 0, 1, null);

        addItem(Material.LEATHER_CHESTPLATE, (short) 0, 1, null);

        addItem(Material.LEATHER_HELMET, (short) 0, 1, null);

        addItem(Material.LEATHER_LEGGINGS, (short) 0, 1, null);

        addItem(Material.SUGAR, (short) 0, 4, null);

        addItem(Material.GRAVEL, (short) 0, 16, null);

        addItem(Material.PORK, (short) 0, 4, null);

        addItem(Material.BUCKET, (short) 0, 1, null);

        addItem(Material.APPLE, (short) 0, 3, null);

        addItem(Material.APPLE, (short) 0, 3, null);

        addItem(Material.APPLE, (short) 0, 3, null);

        addItem(Material.APPLE, (short) 0, 3, null);

        addItem(Material.APPLE, (short) 0, 3, null);


        addItem(Material.GOLD_AXE, (short) 0, 1, null);


        addItem(Material.BOW, (short) 0, 1, null);


        addItem(Material.POTATO, (short) 0, 1, null);


        addItem(Material.POTION, (short) 16417, 1, null);

        addItem(Material.POTION, (short) 16420, 1, null);


        addItem(Material.POTION, (short) 16459, 1, null);


        addItem(Material.POTION, (short) 16424, 1, null);

        addItem(Material.POTION, (short) 16428, 1, null);

        addItem(Material.MILK_BUCKET, (short) 0, 1, null);

        addItem(Material.CHAINMAIL_BOOTS, (short) 0, 1, null);

        addItem(Material.CHAINMAIL_CHESTPLATE, (short) 0, 1, null);

        addItem(Material.CHAINMAIL_HELMET, (short) 0, 1, null);

        addItem(Material.CHAINMAIL_LEGGINGS, (short) 0, 1, null);


        item = new ItemStack(Material.DIAMOND);
        item.setAmount(1);
        this.lucky.put(0, item);
        indexLucky++;

        item = new ItemStack(Material.IRON_INGOT);
        item.setAmount(2);
        this.lucky.put(1, item);
        indexLucky++;

        item = new ItemStack(Material.APPLE);
        item.setAmount(3);
        this.lucky.put(2, item);
        indexLucky++;


        item = new ItemStack(Material.GOLD_INGOT);
        item.setAmount(3);
        this.lucky.put(3, item);
        indexLucky++;

        this.indexLucky = this.index;
        this.lucky = this.loot;
    }


    public ChestControl() {
        this.index = 0;
        ItemStack item = new ItemStack(Material.WOOD_AXE);
        ItemMeta meta = item.getItemMeta();


        meta.setDisplayName(ChatColor.AQUA + "火鸟之心");
        meta.addEnchant(Enchantment.FIRE_ASPECT, 1, false);
        addItem(Material.WOOD_SWORD, (short) 0, 1, meta);


        addItem(Material.DIAMOND, (short) 0, 1, null);

        addItem(Material.IRON_INGOT, (short) 0, 3, null);

        addItem(Material.WOOD, (short) 0, 12, null);

        addItem(Material.PISTON_BASE, (short) 0, 2, null);

        addItem(Material.DIAMOND_HELMET, (short) 0, 1, null);

        addItem(Material.BREAD, (short) 0, 4, null);

        addItem(Material.CAKE, (short) 0, 1, null);

        addItem(Material.ARROW, (short) 0, 16, null);

        addItem(Material.LEATHER_BOOTS, (short) 0, 1, null);

        addItem(Material.LEATHER_CHESTPLATE, (short) 0, 1, null);

        addItem(Material.LEATHER_HELMET, (short) 0, 1, null);

        addItem(Material.LEATHER_LEGGINGS, (short) 0, 1, null);

        addItem(Material.SUGAR, (short) 0, 4, null);

        addItem(Material.GRAVEL, (short) 0, 16, null);

        addItem(Material.PORK, (short) 0, 4, null);

        addItem(Material.BUCKET, (short) 0, 1, null);

        addItem(Material.APPLE, (short) 0, 3, null);

        addItem(Material.APPLE, (short) 0, 3, null);

        addItem(Material.APPLE, (short) 0, 3, null);

        addItem(Material.APPLE, (short) 0, 3, null);

        addItem(Material.APPLE, (short) 0, 3, null);


        addItem(Material.GOLD_AXE, (short) 0, 1, null);


        addItem(Material.BOW, (short) 0, 1, null);


        addItem(Material.POTATO, (short) 0, 1, null);


        addItem(Material.POTION, (short) 16417, 1, null);

        addItem(Material.POTION, (short) 16420, 1, null);


        addItem(Material.POTION, (short) 16459, 1, null);


        addItem(Material.POTION, (short) 16424, 1, null);

        addItem(Material.POTION, (short) 16428, 1, null);

        addItem(Material.MILK_BUCKET, (short) 0, 1, null);

        addItem(Material.CHAINMAIL_BOOTS, (short) 0, 1, null);

        addItem(Material.CHAINMAIL_CHESTPLATE, (short) 0, 1, null);

        addItem(Material.CHAINMAIL_HELMET, (short) 0, 1, null);

        addItem(Material.CHAINMAIL_LEGGINGS, (short) 0, 1, null);


        item = new ItemStack(Material.DIAMOND);
        item.setAmount(1);
        this.lucky.put(0, item);
        indexLucky++;

        item = new ItemStack(Material.IRON_INGOT);
        item.setAmount(2);
        this.lucky.put(1, item);
        indexLucky++;

        item = new ItemStack(Material.APPLE);
        item.setAmount(3);
        this.lucky.put(2, item);
        indexLucky++;


        item = new ItemStack(Material.GOLD_INGOT);
        item.setAmount(3);
        this.lucky.put(3, item);
        indexLucky++;

        this.indexLucky = this.index;
        this.lucky = this.loot;
    }


    public void addItem(Material material, short durability, int amount, ItemMeta meta) {
        ItemStack item = new ItemStack(material);
        item.setAmount(amount);
        if (durability != 0) {
            item.setDurability(durability);
        }
        if (meta != null) {
            item.setItemMeta(meta);
        }
        this.loot.put(this.index, item);
        this.index++;
    }

    public ItemStack randomItem(int which) {
        Random r = new Random();
        ItemStack i;
        if (which == 1) {
            i = this.loot.get(r.nextInt(this.loot.size()));

        } else {
            i = this.loot.get(r.nextInt(this.lucky.size()));

        }
        return i;

    }


    public ItemStack[] generateLoot(int size, int level) {
        int k = 0;
        int levelC = 0;
        int i = 0;

        HashMap<Integer, ItemStack> returnValue = new HashMap<Integer, ItemStack>();
        ItemStack air = new ItemStack(Material.AIR);
        for (int var = 0; var <= size; var++) {
            returnValue.put(var, air);
            //	 System.out.print("put " + var +  " Material " + air.getTypeId());
        }


        if (level == 1) {
            levelC = 4;
        } else {
            levelC = 2;
        }


        while (i < levelC) {

            Random r = new Random();

            //	System.out.print("=================");
            //	System.out.print("size :" + size);
            //	System.out.print("k :" + k);
            //	System.out.print("i :" + i);
            //	System.out.print("item :" + returnValue.get(k).getTypeId());
            //	System.out.print("=================");


            if (k < size) {
                if (k == r.nextInt(size)) {
                    if (returnValue.get(k).getType().equals(Material.AIR)) {

                        returnValue.replace(k, randomItem(level));
                        //				System.out.print("-----------------");
                        //				System.out.print("size :" + size);
                        //				System.out.print("k :" + k);
                        //				System.out.print("i :" + i);
                        //				System.out.print("item :" + returnValue.get(k).getTypeId());
                        //				System.out.print("-----------------");
                        i++;
                    }

                }
            } else {
                k = 0;
            }
            k++;

        }


        ItemStack[] s = new ItemStack[returnValue.size() - 1];
        for (int count = 0; count < returnValue.size() - 1; count++) {


            s[count] = returnValue.get(count);

            //	System.out.print("++++++++++++++");
            //	System.out.print("Item " + s[count].getTypeId());
            //	System.out.print("++++++++++++++");

        }
        return s;
    }


}


