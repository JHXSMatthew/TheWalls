package com.github.JHXSMatthew.Kits.Skills.archer;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Matthew on 5/06/2016.
 */
public class SK_Archer_8 extends SkillBasic {

    public SK_Archer_8(KitBasic kit, int innerLevel) {
        super(kit, innerLevel, SkillType.Archer_8 );
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
        if(evt.getDamager() instanceof Arrow){
            if(random(100,5*getInnerLevel())){
                if(evt.getEntity() instanceof LivingEntity){
                    ((LivingEntity)evt.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.WITHER,40,0));
                    getPlayer().sendMessage(ChatColor.AQUA + "涂毒射击!");
                    evt.getEntity().getWorld().playSound(evt.getEntity().getLocation(), Sound.WITHER_IDLE,1F,1F);
                }
            }
        }

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
