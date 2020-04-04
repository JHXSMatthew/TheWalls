package com.github.JHXSMatthew.Kits.Skills.bloodSeeker;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

/**
 * Created by Matthew on 5/06/2016.
 */
public class SK_BloodSeeker_1 extends SkillBasic {
    public SK_BloodSeeker_1(KitBasic kit, int innerLevel) {
        super(kit, innerLevel, SkillType.BloodSeeker_1);
    }

    @Override
    protected void onDamaged(EntityDamageEvent evt) {

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
        double after = getPlayer().getHealth() + getInnerLevel() * 2;

        if (after > getPlayer().getMaxHealth()) {
            getPlayer().setHealth(getPlayer().getMaxHealth());
        } else {
            try {
                getPlayer().setHealth(after);
            } catch (Exception e) {
                System.err.println("Try to set health to " + after);
            }
        }

        getPlayer().playSound(getPlayer().getLocation(), Sound.WOLF_HURT, 1F, 1F);
        getPlayer().sendMessage(ChatColor.AQUA + "嗜血狂魔!");
    }

    @Override
    protected void onStart() {

    }
}
