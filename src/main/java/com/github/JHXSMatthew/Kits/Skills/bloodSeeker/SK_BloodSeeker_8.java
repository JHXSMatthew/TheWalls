package com.github.JHXSMatthew.Kits.Skills.bloodSeeker;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

/**
 * Created by Matthew on 5/06/2016.
 */
public class SK_BloodSeeker_8 extends SkillBasic {
    public SK_BloodSeeker_8(KitBasic kit, int innerLevel) {
        super(kit, innerLevel, SkillType.BloodSeeker_8);
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
        if (evt.getEntity() instanceof LivingEntity) {
            if (((LivingEntity) evt.getEntity()).getHealth() < 2 * getInnerLevel()) {
                evt.setDamage(2000);
                getPlayer().getLocation().getWorld().playSound(getPlayer().getLocation(), Sound.ZOMBIE_DEATH, 1F, 1F);
                getPlayer().sendMessage(ChatColor.RED + "血腥屠杀-秒杀");
                if (evt.getEntity() instanceof Player)
                    evt.getEntity().sendMessage(ChatColor.RED + "血腥屠杀-秒杀");
            }
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
