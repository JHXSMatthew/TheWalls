package com.github.JHXSMatthew.Kits.Skills.paladin;

import com.github.JHXSMatthew.Game.GamePlayer;
import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import com.github.JHXSMatthew.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.HashSet;

/**
 * Created by Matthew on 15/07/2016.
 */
public class SK_Paladin_6 extends SkillBasic {


    private long last = 0;

    public SK_Paladin_6(KitBasic kit, int innerLevel) {
        super(kit, innerLevel, SkillType.Paladin_6);


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
            if (last < System.currentTimeMillis()) {
                if (evt.getItem() != null && evt.getItem().hasItemMeta() && evt.getItem().getItemMeta().getDisplayName().equals(SK_Paladin_1.itemName) && evt.getItem().getType() == Material.IRON_SWORD) {
                    Block b = getPlayer().getTargetBlock((HashSet<Material>) null, 32);
                    if (b == null) {
                        sendMessage("目标指向太远.");
                        return;
                    }
                    last = System.currentTimeMillis() + 1000 * 50;
                    GamePlayer owner = Main.getPc().getGamePlayer(getPlayer());
                    for (Entity i : getPlayer().getWorld().getEntities()) {
                        if (i instanceof Player) {
                            if (owner.getTeam().isInTeam(Main.getPc().getGamePlayer((Player) i))) {
                                continue;
                            }
                            if (i.getLocation().distanceSquared(b.getLocation()) < 5) {
                                ((Player) i).damage(getInnerLevel(), getPlayer());
                                sendAffected((Player) i);
                            }
                        }
                    }
                    b.getLocation().getWorld().strikeLightningEffect(b.getLocation());
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
