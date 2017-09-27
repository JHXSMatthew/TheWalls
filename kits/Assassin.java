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
public class Assassin extends KitBasic implements Listener {


    public Assassin(UUID uuid, int level) {
        super(uuid, level, KitType.assassin);
    }

    @Override
    protected void onInteract(PlayerInteractEvent evt) {

    }

    @Override
    protected void onKill(PlayerDeathEvent evt) {
        getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED,20 * (3+(level -1) *2),1));

    }

    @Override
    protected void onStart() {

    }
}
