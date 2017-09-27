package com.github.JHXSMatthew.Kits.Skills.fish;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Matthew on 5/06/2016.
 */
public class SK_Fish_4 extends SkillBasic {


    public SK_Fish_4(KitBasic kit, int innerLevel) {
        super(kit, innerLevel, SkillType.Fish_4);
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
        ItemStack item  = getPlayer().getItemInHand();
        if(item != null
                && (item.getType() == Material.RAW_FISH

        )){
            if(getRandom().random(100,10 + getInnerLevel())){
                if(evt.getEntity() instanceof Player){
                    skillTriggered((Player) evt.getEntity());
                    getPlayer().getLocation().getWorld().playSound(getPlayer().getLocation(), Sound.GLASS,1F,1F);
                    evt.getEntity().setVelocity(evt.getEntity().getLocation().getDirection().multiply(-1));
                }
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
