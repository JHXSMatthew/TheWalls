package com.github.JHXSMatthew.Kits.Skills.knight;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import com.github.JHXSMatthew.Main;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * Created by Matthew on 5/06/2016.
 */
public class SK_Knight_1 extends SkillBasic {


    private Horse h;
    private long coolDown = 0;

    public SK_Knight_1(KitBasic kit, int innerLevel) {
        super(kit, innerLevel, SkillType.Knight_1);
    }


    private void summon() {
        try {
            if (getPlayer().getVehicle() != null) {
                getPlayer().getVehicle().eject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (h == null) {
            if (!h.isDead()) {
                h.remove();
            }
            h = null;
        }
        h = (Horse) getPlayer().getLocation().getWorld().spawnEntity(getPlayer().getLocation(), EntityType.HORSE);
        h.setCustomName(getPlayer().getDisplayName() + "的战马");
        h.setMetadata("HORSE", new FixedMetadataValue(Main.get(), getPlayer().getName()));
        h.setMaxHealth(20 + 2 * getInnerLevel());
        h.setHealth(h.getMaxHealth());
        h.setAdult();
        h.setPassenger(getPlayer());
        getPlayer().playSound(getPlayer().getLocation(), Sound.HORSE_ANGRY, 1F, 1F);
    }

    @EventHandler
    public void onTriggerHorse(PlayerToggleSneakEvent evt) {
        if (evt.getPlayer().equals(getPlayer())) {
            if (h != null
                    && evt.getPlayer().getVehicle() != null
                    && evt.getPlayer().getVehicle().getType() == EntityType.HORSE
            ) {
                try {
                    h.getPassenger().getVehicle().eject();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                h.remove();
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
        if (evt.getAction() == Action.RIGHT_CLICK_AIR || evt.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (evt.getItem() != null) {
                ItemStack item = evt.getItem();
                if (item.getType() == Material.SADDLE) {
                    if (coolDown < System.currentTimeMillis()) {
                        coolDown = System.currentTimeMillis() + 1000 * 300;
                        summon();
                        skillTriggered(null);
                    } else {
                        sendNotYet(coolDown);
                    }
                }
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
