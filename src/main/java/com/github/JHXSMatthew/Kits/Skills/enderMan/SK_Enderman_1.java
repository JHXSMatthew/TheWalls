package com.github.JHXSMatthew.Kits.Skills.enderMan;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.inventory.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

/**
 * Created by Matthew on 5/06/2016.
 */
public class SK_Enderman_1 extends SkillBasic {

    static int coolDown = 3; //in seconds
    int duration = 0;

    boolean magic = false;

    ItemStack item;
    long last;
    Location l;

    public SK_Enderman_1(KitBasic kit, int innerLevel) {
        super(kit, innerLevel, SkillType.Enderman_1);
        item = com.github.JHXSMatthew.Utils.ItemFactory.create(Material.SUGAR,(byte)0, ChatColor.DARK_PURPLE+ "末影", "右击记录当前位置", + (1+innerLevel*2) + "秒内可再次右击", "回到上次记录点." , "所有者: "+ ChatColor.RED + getPlayerName());
        addItemWithSlot(0,item);
        addUnDroppable(item);
        duration = 1+innerLevel*2;
    }



    @Override
    protected void onDamaged(EntityDamageEvent evt) {

    }

    @Override
    protected void onBlockDamaged(EntityDamageByBlockEvent evt) {

    }

    @Override
    protected void onEntityDamaged(EntityDamageByEntityEvent evt) {
        if(evt.getDamager() instanceof Arrow){
            if(System.currentTimeMillis() - last < 2000){
                evt.setDamage(0);
            }
        }
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
        if(evt.getItem() == null || evt.getItem().getType().equals(Material.AIR))
            return;
        if(evt.getItem().isSimilar(item)){
            if( System.currentTimeMillis()  > last + (duration + coolDown) * 1000  || l == null){
                l = getPlayer().getLocation();
                getPlayer().playSound(l, Sound.CLICK,1F,1F);
                last = System.currentTimeMillis();
                magic = true;
                getPlayer().sendMessage(ChatColor.AQUA + "末影记录.");
            }else if(System.currentTimeMillis() < last + duration * 1000   && magic){
                getPlayer().getLocation().getWorld().playEffect(getPlayer().getLocation(), Effect.ENDER_SIGNAL,0);
                getPlayer().getWorld().playSound(getPlayer().getLocation(),Sound.ENDERMAN_TELEPORT,1F,1F);
                getPlayer().teleport(l);
                getPlayer().getWorld().playSound(getPlayer().getLocation(),Sound.ENDERMAN_TELEPORT,1F,1F);
                magic = false;
                getPlayer().sendMessage(ChatColor.AQUA + "末影瞬移.");
            }else{
                sendNotYet(last);
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
