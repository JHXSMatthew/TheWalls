package com.github.JHXSMatthew.Kits.allKits;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.KitType;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * Created by Matthew on 2016/5/21.
 */
public class Philosopher extends KitBasic implements Listener {

    public Philosopher(UUID uuid, int level) {
        super(uuid, level, KitType.philosopher);
        addItemWithSlot(0,new ItemStack(Material.ENCHANTMENT_TABLE,1));
    }

    @Override
    protected void onInteract(PlayerInteractEvent evt) {

    }

    @Override
    protected void onKill(PlayerDeathEvent evt) {

    }

    @Override
    protected void onStart() {

    }
}
