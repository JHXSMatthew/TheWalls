package com.github.JHXSMatthew.Kits.Skills.fish;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Matthew on 5/06/2016.
 */
public class SK_Fish_6 extends SkillBasic {

    private long last = 0;
    private long notifiedTime = 0;

    public SK_Fish_6(KitBasic kit, int innerLevel) {
        super(kit, innerLevel, SkillType.Fish_6);
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
        if (evt.getAction() == Action.RIGHT_CLICK_AIR || evt.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (evt.getItem() != null) {
                ItemStack item = evt.getItem();
                if (item.getType() == Material.RAW_FISH) {
                    if (last < System.currentTimeMillis()) {
                        last = System.currentTimeMillis() + 100 * 1000;
                        skillTriggered(null);
                        getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * getInnerLevel(), 0));
                        getPlayer().playSound(getPlayer().getLocation(), Sound.SILVERFISH_KILL, 1F, 1F);
                    } else {
                        if (notifiedTime < System.currentTimeMillis()) {
                            notifiedTime = System.currentTimeMillis() + 5 * 1000;
                            getPlayer().playSound(getPlayer().getLocation(), Sound.CLICK, 1F, 1F);
                            getPlayer().sendMessage(ChatColor.RED + "冷却尚未结束,还需要" + ((last - System.currentTimeMillis()) / 1000) + "秒!");
                        }
                    }
                }
            }
        }
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
