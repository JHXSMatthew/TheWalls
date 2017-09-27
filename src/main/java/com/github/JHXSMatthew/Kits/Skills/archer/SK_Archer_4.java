package com.github.JHXSMatthew.Kits.Skills.archer;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Matthew on 5/06/2016.
 */
public class SK_Archer_4 extends SkillBasic {

    public SK_Archer_4(KitBasic kit, int innerLevel) {
        super(kit, innerLevel, SkillType.Archer_4);
        addItemWithSlot(2,new ItemStack(Material.TNT,innerLevel));

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
        if(evt.getEntity() instanceof Arrow) {
            if (getPlayer().getInventory().contains(Material.TNT)) {
                getPlayer().playSound(getPlayer().getLocation(), Sound.CREEPER_HISS,0.5F,0.5F);
            }
        }
    }

    @Override
    protected void onProjectileHit(ProjectileHitEvent evt) {
        if(evt.getEntity() instanceof Arrow){
            if(getPlayer().getInventory().contains(Material.TNT)){
                evt.getEntity().remove();
                TNTPrimed tnt = (TNTPrimed) evt.getEntity().getWorld().spawnEntity(evt.getEntity().getLocation(), EntityType.PRIMED_TNT);
                tnt.setFuseTicks(1);
                int position = getPlayer().getInventory().first(Material.TNT);
                if(position == -1)
                    return;
                if(getPlayer().getInventory().getItem(position).getAmount() == 1){
                    getPlayer().getInventory().setItem(position,new ItemStack(Material.AIR));
                }else{
                    getPlayer().getInventory().getItem(position).setAmount(getPlayer().getInventory().getItem(position).getAmount() - 1);
                }
            }
        }
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
