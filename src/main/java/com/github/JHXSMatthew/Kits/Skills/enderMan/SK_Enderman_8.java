package com.github.JHXSMatthew.Kits.Skills.enderMan;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import com.github.JHXSMatthew.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 * Created by Matthew on 5/06/2016.
 */
public class SK_Enderman_8 extends SkillBasic {

    ItemStack item;
    BukkitTask task;
    public SK_Enderman_8(KitBasic kit, int innerLevel) {
        super(kit, innerLevel, SkillType.Enderman_8);
        item = com.github.JHXSMatthew.Utils.ItemFactory.create(Material.COMPASS,(byte)0, ChatColor.DARK_PURPLE+ "末影追踪器", "右击指向最近敌人所在位置.","所有者: " + ChatColor.RED + getPlayerName());
        addItemWithSlot(1,item);
        addUnDroppable(item);

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
        if(evt.getItem() == null || evt.getItem().getType().equals(Material.AIR)){
            return;
        }
        if(evt.getItem().isSimilar(item)){
            if(task == null){
                task = new BukkitRunnable(){
                    @Override
                    public void run() {
                        for(Player p : Bukkit.getOnlinePlayers()){
                            if(!Main.getPc().getGamePlayer(p.getPlayer()).getTeam().isInTeam(Main.getPc().getGamePlayer(getPlayer()))){
                                getPlayer().setCompassTarget(p.getLocation());
                                break;
                            }
                        }
                    }
                }.runTaskTimer(Main.get(),0,10);
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

    @Override
    public void dispose() {
        super.dispose();
        if(task != null){
            task.cancel();
        }
    }
}
