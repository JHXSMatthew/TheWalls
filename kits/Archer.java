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
public class Archer extends KitBasic implements Listener {

    public Archer(UUID uuid, int level) {
        super(uuid, level, KitType.archer);
        addItemWithSlot(0,new ItemStack(Material.BOW));
        addItemWithSlot(1,new ItemStack(Material.ARROW,5*level));
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
