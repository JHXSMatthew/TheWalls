package com.github.JHXSMatthew.Kits.Skills.cook;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import org.bukkit.ChatColor;
import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

/**
 * Created by Matthew on 24/05/2016.
 */
public class SK_Cook_6 extends SkillBasic {

    public SK_Cook_6(KitBasic kit, int innerLevel) {
        super(kit, innerLevel, SkillType.Cook_6);
    }


    @Override
    protected void onInteract(PlayerInteractEvent evt) {
        if(evt.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(evt.getPlayer().getItemInHand() != null &&
                    !(evt.getPlayer().getItemInHand().getType().equals(Material.GOLD_HOE)
                            ||evt.getPlayer().getItemInHand().getType().equals(Material.DIAMOND_HOE)
                            ||evt.getPlayer().getItemInHand().getType().equals(Material.IRON_HOE)
                            ||evt.getPlayer().getItemInHand().getType().equals(Material.STONE_HOE)
                            ||evt.getPlayer().getItemInHand().getType().equals(Material.WOOD_HOE)
                        )){
                return;
            }
            if(evt.getClickedBlock()!= null && evt.getClickedBlock().getType() == Material.CROPS && evt.getClickedBlock().getData() != CropState.RIPE.getData()){
                short current = evt.getPlayer().getItemInHand().getDurability();
                short max = evt.getPlayer().getItemInHand().getType().getMaxDurability();

                if(current == 0 || current >= max - max/getInnerLevel()){
                    evt.getPlayer().sendMessage(ChatColor.AQUA + "耐久度不足.");
                    return;
                }
                evt.getPlayer().getItemInHand().setDurability((short)(current + max/getInnerLevel()));
                evt.getClickedBlock().setData(CropState.RIPE.getData());
                evt.getPlayer().playSound(getPlayer().getLocation(), Sound.LEVEL_UP,1F,1F);
                evt.getPlayer().sendMessage(ChatColor.AQUA+"催熟成功!");

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
    protected void onKill(PlayerDeathEvent evt) {

    }

    @Override
    protected void onKillEntity(EntityDeathEvent evt) {

    }

    @Override
    protected void onStart() {

    }
}
