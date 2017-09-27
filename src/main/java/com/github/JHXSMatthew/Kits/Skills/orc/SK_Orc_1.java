package com.github.JHXSMatthew.Kits.Skills.orc;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import org.bukkit.inventory.*;
import org.bukkit.Material;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Matthew on 15/07/2016.
 */
public class SK_Orc_1 extends SkillBasic {
    public SK_Orc_1(KitBasic kit, int innerLevel) {
        super(kit, innerLevel, SkillType.Orc_1);

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
        if(evt.getItem().getType() == Material.MILK_BUCKET){
            evt.setCancelled(true);
            evt.setItem(new ItemStack(Material.BUCKET));
        }
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
        getPlayer().setMaxHealth(getInnerLevel() * 3);
        getPlayer().setHealth(getPlayer().getMaxHealth());
        getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW,Integer.MAX_VALUE,0));
        getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,Integer.MAX_VALUE,0));
        skillTriggered(null);
    }
}
