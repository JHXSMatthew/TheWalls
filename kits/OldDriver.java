package com.github.JHXSMatthew.Kits.allKits;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.KitType;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

/**
 * Created by Matthew on 2016/5/21.
 */
public class OldDriver extends KitBasic implements Listener {


    public OldDriver(UUID uuid, int level) {
        super(uuid, level, KitType.oldDriver);
    }

    @Override
    protected void onInteract(PlayerInteractEvent evt) {

    }

    @Override
    protected void onKill(PlayerDeathEvent evt) {

    }

    @Override
    protected void onStart() {
        getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED,20 * 60,0));
    }
}
