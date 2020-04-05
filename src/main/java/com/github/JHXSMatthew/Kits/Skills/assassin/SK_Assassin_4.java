package com.github.JHXSMatthew.Kits.Skills.assassin;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import org.bukkit.ChatColor;
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
public class SK_Assassin_4 extends SkillBasic {
    public SK_Assassin_4(KitBasic kit, int innerLevel) {
        super(kit, innerLevel, SkillType.Assassin_4);

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
        if (!(evt.getEntity() instanceof LivingEntity))
            return;
        LivingEntity victim = (LivingEntity) evt.getEntity();

        double q = victim.getLocation().getDirection().dot(getPlayer().getLocation().getDirection());
        if (q > 0) {
            if (random(100, getInnerLevel() * 10)) {
                ((LivingEntity) evt.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 1));
                getPlayer().sendMessage(ChatColor.AQUA + "背刺!");
                getPlayer().getWorld().playSound(getPlayer().getLocation(), Sound.ZOMBIE_HURT, 0.5F, 0.5F);
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
