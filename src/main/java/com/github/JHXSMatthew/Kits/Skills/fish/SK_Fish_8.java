package com.github.JHXSMatthew.Kits.Skills.fish;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import org.bukkit.Sound;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Matthew on 5/06/2016.
 */
public class SK_Fish_8 extends SkillBasic {


    private boolean triggered = false;

    public SK_Fish_8(KitBasic kit, int innerLevel) {
        super(kit, innerLevel, SkillType.Fish_8);
    }


    @Override
    protected void onDamaged(EntityDamageEvent evt) {
        if (!triggered) {
            double current = getPlayer().getHealth();
            if (current - evt.getFinalDamage() < 4) {
                getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, getInnerLevel(), 1));
                triggered = true;
                skillTriggered(null);
                getPlayer().playSound(getPlayer().getLocation(), Sound.PISTON_RETRACT, 1F, 1F);
            }
        }
    }

    @Override
    protected void onBlockDamaged(EntityDamageByBlockEvent evt) {

    }

    @Override
    protected void onEntityDamaged(EntityDamageByEntityEvent evt) {

    }

    @Override
    protected void onDealDamage(EntityDamageByEntityEvent evt) {

    }

    @Override
    protected void onDealProjectileDamage(EntityDamageByEntityEvent evt) {

    }

    @Override
    protected void onFoodLevelChanged(FoodLevelChangeEvent evt) {

    }

    @Override
    protected void onProjectileLaunch(ProjectileLaunchEvent evt) {

    }

    @Override
    protected void onProjectileHit(ProjectileHitEvent evt) {

    }

    @Override
    protected void onConsume(PlayerItemConsumeEvent evt) {

    }

    @Override
    protected void onCraft(CraftItemEvent evt) {

    }

    @Override
    protected void onInteract(PlayerInteractEvent evt) {

    }


    @Override
    protected void onKill(PlayerDeathEvent evt) {

    }


    @Override
    protected void onKillEntity(EntityDeathEvent evt) {

    }

    @Override
    protected void onStart() {

    }
}
