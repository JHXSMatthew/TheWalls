package com.github.JHXSMatthew.Kits.allKits;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.KitType;
import com.github.JHXSMatthew.Utils.ItemFactory;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

/**
 * Created by Matthew on 2016/5/21.
 */
public class Alchemist extends KitBasic implements Listener {

    public Alchemist(UUID uuid, int level) {
        super(uuid, level, KitType.alchemist);
        for(int i = 0; i < level*3 ; i ++){
            addItemWithSlot(i, ItemFactory.create(Material.POTION,(byte)16428,"伤害药水"));
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
