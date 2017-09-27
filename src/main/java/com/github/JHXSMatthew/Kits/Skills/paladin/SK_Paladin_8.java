package com.github.JHXSMatthew.Kits.Skills.paladin;

import com.github.JHXSMatthew.Game.GamePlayer;
import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import com.github.JHXSMatthew.Main;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashSet;

/**
 * Created by Matthew on 15/07/2016.
 */
public class SK_Paladin_8 extends SkillBasic {


    private boolean triggered = false;

    public SK_Paladin_8(KitBasic kit, int innerLevel) {
        super(kit, innerLevel, SkillType.Paladin_8);


    }

    @Override
    protected void onDamaged(EntityDamageEvent evt) {
        if(triggered)
            return;

        if(getPlayer().getHealth()  - evt.getFinalDamage() <= 0){
            triggered = true;
            GamePlayer owner = Main.getPc().getGamePlayer(getPlayer());
            for(GamePlayer gp : owner.getTeam().getPlayers()){
                if(gp.get().getGameMode().equals(GameMode.SURVIVAL)){
                    gp.get().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,8 * getInnerLevel() + 20,0));
                    sendAffected(gp.get());
                    skillTriggered(null);
                }
            }
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

    }
}
