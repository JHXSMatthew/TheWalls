package com.github.JHXSMatthew.Kits.Skills.enderMan;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import com.github.JHXSMatthew.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 * Created by Matthew on 5/06/2016.
 */
public class SK_Enderman_4 extends SkillBasic {

    private BukkitTask timer;
    private int reduce = 0;
    private boolean damaged = false;

    public SK_Enderman_4(KitBasic kit, int innerLevel) {
        super(kit, innerLevel, SkillType.Enderman_4);
    }
    @Override
    protected void onDamaged(EntityDamageEvent evt) {
        if(evt.isCancelled()){
            return;
        }
        damaged = true;

        if(reduce > 0){
            if(getPlayer().getEquipment().getChestplate() != null && !getPlayer().getEquipment().getChestplate().getType().equals(Material.AIR)){
                reduce = 0;
                getPlayer().sendMessage(ChatColor.AQUA + "检测到穿戴其他胸甲,粒子胸甲能量消失.");
                return;
            }
            reduce --;
            getPlayer().getLocation().getWorld().playSound(getPlayer().getLocation(),Sound.ENDERMAN_STARE,0.5F,0.5F);
            getPlayer().sendMessage(ChatColor.AQUA + "粒子胸甲能量剩余 " + ChatColor.GREEN + reduce + "/" +getInnerLevel());
            evt.setDamage(0);

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
        timer = new BukkitRunnable(){
            @Override
            public void run() {
                if(reduce > 0)
                    return;

                reduce = 0;
                if(damaged)
                    return;
                if(getPlayer().getEquipment().getChestplate() == null || getPlayer().getEquipment().getChestplate().getType() == Material.AIR){
                    reduce = getInnerLevel();
                    getPlayer().sendMessage(ChatColor.AQUA + "粒子护甲生效!");
                    getPlayer().playSound(getPlayer().getLocation(),Sound.BAT_HURT,1F,1F);
                }

            }
        }.runTaskTimer(Main.get(),0,7 * 20);
    }

    @Override
    public void dispose() {
        super.dispose();
        if(timer != null) {
            timer.cancel();
        }
    }
}
