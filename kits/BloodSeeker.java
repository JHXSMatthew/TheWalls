package com.github.JHXSMatthew.Kits.allKits;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.KitType;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

/**
 * Created by Matthew on 2016/5/21.
 */
public class BloodSeeker extends KitBasic implements Listener {

    public BloodSeeker(UUID uuid, int level) {
        super(uuid, level, KitType.bloodSeeker);
    }

    @Override
    protected void onInteract(PlayerInteractEvent evt) {

    }

    @Override
    protected void onKill(PlayerDeathEvent evt) {
        getPlayer().setHealth(getPlayer().getHealth() + 1 +(level -1) * 2);
    }

    @Override
    protected void onStart() {

    }
}
