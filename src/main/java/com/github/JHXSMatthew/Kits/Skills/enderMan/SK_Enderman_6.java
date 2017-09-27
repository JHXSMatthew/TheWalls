package com.github.JHXSMatthew.Kits.Skills.enderMan;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
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
public class SK_Enderman_6 extends SkillBasic {

    public SK_Enderman_6(KitBasic kit, int innerLevel) {
        super(kit, innerLevel, SkillType.Enderman_6);
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
        if(!(evt.getEntity() instanceof LivingEntity)){
            return;
        }
        if(getPlayer().getItemInHand().getType().equals(Material.IRON_SWORD)){
            if(random(100,5+3*getInnerLevel())){
                ((LivingEntity) evt.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW,20,10));
                evt.getEntity().getWorld().playEffect(((LivingEntity) evt.getEntity()).getEyeLocation(), Effect.SPELL , 0);
                getPlayer().playSound(getPlayer().getLocation(), Sound.ENDERDRAGON_HIT,1F,1F);
                getPlayer().sendMessage(ChatColor.AQUA + "粒子武器生效!");
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
