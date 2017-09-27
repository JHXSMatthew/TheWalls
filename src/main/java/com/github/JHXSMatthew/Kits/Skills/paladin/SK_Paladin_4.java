package com.github.JHXSMatthew.Kits.Skills.paladin;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import com.github.JHXSMatthew.Main;
import com.github.JHXSMatthew.Utils.ItemFactory;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Matthew on 15/07/2016.
 */
public class SK_Paladin_4 extends SkillBasic {


    private long last = 0;

    public SK_Paladin_4(KitBasic kit, int innerLevel) {
        super(kit, innerLevel, SkillType.Paladin_4);


    }


    @EventHandler
    public void onFriendlyDamaged(EntityDamageByEntityEvent evt){
        if(evt.getDamager() instanceof Player && evt.getEntity() instanceof Player){
            if(evt.getDamager().equals(getPlayer())) {
                if(((Player) evt.getDamager()).getItemInHand() != null && ((Player) evt.getDamager()).getItemInHand().getType() == Material.IRON_SWORD && ((Player) evt.getDamager()).getItemInHand().hasItemMeta() && ((Player) evt.getDamager()).getItemInHand().getItemMeta().getDisplayName().equals(SK_Paladin_1.itemName)){
                    if (Main.getPc().getGamePlayer((Player) evt.getDamager()).getTeam().isInTeam(Main.getPc().getGamePlayer((Player) evt.getEntity()))) {
                        if(last < System.currentTimeMillis()){
                            last = System.currentTimeMillis() + 1000 * 30;
                            double current = ((Player) evt.getEntity()).getHealth();
                            if(current + getInnerLevel()  + 3 >= ((Player) evt.getEntity()).getMaxHealth()){
                                ((Player) evt.getEntity()).setHealth(((Player) evt.getEntity()).getMaxHealth());
                            }else{
                                ((Player) evt.getEntity()).setHealth(current + getInnerLevel()  + 3 );
                            }
                            skillTriggered((Player) evt.getEntity());
                        }else{
                            sendNotYet(last);
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
