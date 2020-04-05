package com.github.JHXSMatthew.Kits.Skills.cook;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Matthew on 24/05/2016.
 */
public class SK_Cook_4 extends SkillBasic {
    public SK_Cook_4(KitBasic kit, int innerLevel) {
        super(kit, innerLevel, SkillType.Cook_4);

    }

    @Override
    protected void onCraft(CraftItemEvent evt) {
        if (evt.getCurrentItem().getType().isEdible() && random(100, 30)) {
            ItemStack item = evt.getCurrentItem();
            item.setAmount(evt.getCurrentItem().getAmount() * getInnerLevel());
            evt.setCurrentItem(item);
            getPlayer().sendMessage(ChatColor.AQUA + "制作食物翻" + getInnerLevel() + "倍.");
            getPlayer().playSound(getPlayer().getLocation(), Sound.FIREWORK_LAUNCH, 0.5F, 0.5F);
        }
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
