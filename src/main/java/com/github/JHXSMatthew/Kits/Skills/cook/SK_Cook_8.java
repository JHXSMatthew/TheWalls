package com.github.JHXSMatthew.Kits.Skills.cook;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import com.github.JHXSMatthew.Main;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.List;

/**
 * Created by Matthew on 24/05/2016.
 */
public class SK_Cook_8 extends SkillBasic {
    public SK_Cook_8(KitBasic kit, int innerLevel) {
        super(kit, innerLevel, SkillType.Cook_8);
    }


    @Override
    protected void onFoodLevelChanged(FoodLevelChangeEvent evt) {
        int current = evt.getFoodLevel();
        int old = getPlayer().getFoodLevel();
        int inc = current - old;
        if(inc > 0){
            if(random(100,10*getInnerLevel())){
                getPlayer().sendMessage(ChatColor.AQUA + "食神至尊,恢复饱食度!");
                List<Entity> e = getPlayer().getNearbyEntities(10,10,10);
                for(Entity p : e){
                    if(p instanceof Player){
                        if(Main.getPc().getGamePlayer((Player)p).getTeam().isInTeam(Main.get().getPc().getGamePlayer(getPlayer()))){
                            ((Player) p).setFoodLevel(((Player) p).getFoodLevel() + inc > 20 ? 20 : ((Player) p).getFoodLevel()+inc);
                            ((Player) p).playSound(p.getLocation(), Sound.EAT,1F,1F);
                            p.sendMessage(ChatColor.AQUA + "受到来自队友 " + getPlayer().getName() + " 食神效果影响,恢复饱食度!");
                        }
                    }
                }
            }
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
