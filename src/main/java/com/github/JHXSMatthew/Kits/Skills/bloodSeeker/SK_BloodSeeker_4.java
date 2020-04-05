package com.github.JHXSMatthew.Kits.Skills.bloodSeeker;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

/**
 * Created by Matthew on 5/06/2016.
 */
public class SK_BloodSeeker_4 extends SkillBasic {
    public SK_BloodSeeker_4(KitBasic kit, int innerLevel) {
        super(kit, innerLevel, SkillType.BloodSeeker_4);
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
        if (evt.isCancelled()) {
            return;
        }
        if (getPlayer().getItemInHand() != null && getPlayer().getItemInHand().getType().equals(Material.STONE_AXE)) {
            evt.setDamage(0);
            double diff = getPlayer().getMaxHealth() - getPlayer().getHealth();
            getPlayer().setHealth(getPlayer().getHealth() + diff * getInnerLevel() * 0.01);
            getPlayer().getWorld().playEffect(getPlayer().getEyeLocation(), Effect.STEP_SOUND, Material.REDSTONE_WIRE);
        }
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
