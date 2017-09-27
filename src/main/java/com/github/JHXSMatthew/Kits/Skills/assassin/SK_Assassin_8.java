package com.github.JHXSMatthew.Kits.Skills.assassin;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Matthew on 5/06/2016.
 */
public class SK_Assassin_8 extends SkillBasic implements Listener {
    private ItemStack item ;
    private static String NAME = ChatColor.DARK_PURPLE + "隐身斗篷";
    public SK_Assassin_8(KitBasic kit, int innerLevel) {
        super(kit, innerLevel, SkillType.Assassin_8);
        item = com.github.JHXSMatthew.Utils.ItemFactory.create(Material.WATCH,(byte)0, NAME,"右击隐身短暂时间.", ChatColor.RED + "丢弃将消失!");
        addItemWithSlot(0,item);
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
        getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
    }

    @EventHandler
    public void onThrowItem(PlayerDropItemEvent evt){
        if(evt.getItemDrop().getItemStack().equals(item)){
            evt.getItemDrop().remove();
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
        if(evt.getPlayer().getItemInHand() == null || evt.getPlayer().getItemInHand().getType() != Material.WATCH){
            return;
        }
        if(evt.getPlayer().getItemInHand().hasItemMeta() && evt.getPlayer().getItemInHand().getItemMeta().hasDisplayName()){
            if(evt.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(NAME)) {
                evt.getPlayer().setItemInHand(new ItemStack(Material.AIR));
                evt.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,getInnerLevel() * 30 ,1,false));
                evt.getPlayer().playSound(getPlayer().getLocation(), Sound.WITHER_DEATH,1F,1F);
                evt.getPlayer().sendMessage(ChatColor.AQUA + "隐身!");
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
