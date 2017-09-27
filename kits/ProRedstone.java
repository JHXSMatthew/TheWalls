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
public class ProRedstone extends KitBasic implements Listener {

    public ProRedstone(UUID uuid, int level) {
        super(uuid, level, KitType.proRedstone);
        addItemWithSlot(0,new ItemStack(Material.REDSTONE,15));
        addItemWithSlot(1,new ItemStack(Material.DIODE,12));
        if (level == 2){
            addItemWithSlot(2,new ItemStack(Material.PISTON_STICKY_BASE,12));
        }
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
