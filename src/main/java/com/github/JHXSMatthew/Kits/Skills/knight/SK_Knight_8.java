package com.github.JHXSMatthew.Kits.Skills.knight;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

/**
 * Created by Matthew on 5/06/2016.
 */
public class SK_Knight_8 extends SkillBasic {


    private long time = 0;

    public SK_Knight_8(KitBasic kit, int innerLevel) {
        super(kit, innerLevel, SkillType.Knight_8);
    }


    @Override
    protected void onDamaged(EntityDamageEvent evt) {
        if ((evt.getFinalDamage() >= getPlayer().getHealth() && time == 0) || time - System.currentTimeMillis() >= 0) {
            if (time == 0) {
                time = System.currentTimeMillis() + 1000 * getInnerLevel();
                skillTriggered(null);
                getPlayer().playSound(getPlayer().getLocation(), Sound.HORSE_DEATH, 1F, 1F);
            }
            getPlayer().sendMessage(ChatColor.AQUA + "无敌时间剩余 " + (time - System.currentTimeMillis()) / 1000 + " 秒");
            evt.setCancelled(true);
            if (evt instanceof EntityDamageByEntityEvent) {
                if (((EntityDamageByEntityEvent) evt).getDamager() instanceof Player) {
                    ((Player) ((EntityDamageByEntityEvent) evt).getDamager()).playSound(((EntityDamageByEntityEvent) evt).getDamager().getLocation(), Sound.HORSE_ARMOR, 1F, 1F);
                    ((EntityDamageByEntityEvent) evt).getDamager().sendMessage(ChatColor.RED + "坚不可摧效果生效中,您无法对" + getPlayer().getDisplayName() + "造成伤害!");
                }
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
