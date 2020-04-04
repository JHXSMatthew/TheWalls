package com.github.JHXSMatthew.Kits.Skills;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Main;
import com.github.JHXSMatthew.Utils.MathUtils;
import com.github.JHXSMatthew.Utils.Randomizer.IRandomizer;
import com.github.JHXSMatthew.Utils.StringUtils;

import com.github.JHXSMatthew.event.GameEndEvent;
import com.github.JHXSMatthew.event.GameStartEvent;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Matthew on 23/05/2016.
 */
@Getter
public abstract class SkillBasic implements Listener {

    private KitBasic kit;
    private int innerLevel = 0; // innerLevel!!!
    private SkillType type;
    private HashMap<Integer, ItemStack> items = new HashMap<Integer, ItemStack>();
    private IRandomizer random = null;
    private List<ItemStack> undroppable = new ArrayList<ItemStack>();

    public SkillBasic(KitBasic kit, int innerLevel, SkillType type) {
        this.kit = kit;
        this.innerLevel = innerLevel;
        this.type = type;
        Main.get().getServer().getPluginManager().registerEvents(this, Main.get());

    }

    protected boolean random(int max, int chance) {
        if (random == null)
            random = MathUtils.getRandom();
        return random.random(max, chance);
    }


    public void addItemWithSlot(int slot, ItemStack item) {
        items.put(slot, item);
    }

    public void giveStartItem() {
        Player player = getPlayer();
        for (Map.Entry<Integer, ItemStack> em : items.entrySet()) {
            player.getInventory().setItem(em.getKey(), em.getValue());
        }
    }

    public void addUnDroppable(ItemStack item) {
        this.undroppable.add(item);
    }

    public SkillType getType() {
        return this.type;
    }

    public int getInnerLevel() {
        return innerLevel;
    }

    public Player getPlayer() {
        return kit.getPlayer();
    }

    public String getPlayerName() {
        return getPlayer().getName();
    }

    public void dispose() {
        HandlerList.unregisterAll(this);
    }

    public String getDescription() {
        return ChatColor.GREEN + "- " + StringUtils.calSkillPlaceHolders(type.getDescription(), getInnerLevel());
    }

    /**
     * send skill names to each players
     */
    protected void skillTriggered(Player p) {
        if (p != null) {
            p.sendMessage(getPlayer().getDisplayName() + "对你释放了技能" + ChatColor.RED + getType().getSkillName());
        }
        sendMessage(ChatColor.AQUA + "提示 >> " + ChatColor.GRAY + getType().getSkillName() + "释放成功!");

    }

    protected void sendAffected(Player p) {
        p.sendMessage(ChatColor.AQUA + "提示 >> " + ChatColor.GRAY + getPlayer().getDisplayName() + "对你释放了技能" + getType().getSkillName() + ChatColor.GRAY + "!");

    }


    @EventHandler
    public void onDrop(PlayerDropItemEvent evt) {
        for (ItemStack item : undroppable) {
            if (evt.getItemDrop().getItemStack().equals(item)) {
                evt.setCancelled(true);
            }
        }
    }


    @EventHandler
    public void onGameStart(GameStartEvent evt) {
        giveStartItem();
        onStart();
    }

    @EventHandler
    public void onGameEnd(GameEndEvent evt) {
        dispose();
    }

    @EventHandler
    public void onKillEvent(PlayerDeathEvent evt) {
        if (evt.getEntity().getKiller() == null) {
            return;
        }
        if (evt.getEntity().getKiller().equals(getPlayer())) {
            onKill(evt);
        }
    }

    @EventHandler
    public void onKillEntityEvent(EntityDeathEvent evt) {
        if (evt.getEntity().getKiller() == null) {
            return;
        }
        if (evt.getEntity().getKiller().equals(getPlayer())) {
            onKillEntity(evt);
        }
    }

    protected void sendNotYet(long last) {
        getPlayer().sendMessage(ChatColor.GREEN + getType().getSkillName() + ChatColor.RED + ":冷却尚未结束,还有" + (last - System.currentTimeMillis()) / 1000 + "秒!");
    }

    protected void sendMessage(String message) {
        getPlayer().sendMessage(ChatColor.AQUA + getType().getSkillName() + " >> " + ChatColor.GRAY + message);
    }

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent evt) {
        if (evt.getAction() != Action.PHYSICAL) {
            if (evt.getPlayer().equals(getPlayer())) {
                onInteract(evt);
            }
        }
    }

    @EventHandler
    public void onMake(CraftItemEvent evt) {
        if (evt.isCancelled()) {
            return;
        }
        if (evt.getWhoClicked().equals(getPlayer())) {
            onCraft(evt);
        }
    }

    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent evt) {
        if (evt.isCancelled()) {
            return;
        }
        if (evt.getPlayer().equals(getPlayer())) {
            onConsume(evt);
        }
    }

    @EventHandler
    public void onProjectileHitE(ProjectileHitEvent evt) {
        if (evt.getEntity().getShooter().equals(getPlayer())) {
            onProjectileHit(evt);
        }
    }

    @EventHandler
    public void ProjectileLaunchEvent(ProjectileLaunchEvent evt) {
        if (evt.isCancelled()) {
            return;
        }
        if (evt.getEntity().getShooter().equals(getPlayer())) {
            onProjectileLaunch(evt);
        }
    }


    @EventHandler
    public void onDamagedE(EntityDamageEvent evt) {
        if (evt.isCancelled()) {
            return;
        }
        if (evt.getEntity().equals(getPlayer())) {
            onDamaged(evt);
        }
    }

    @EventHandler
    public void onEntityDamagedE(EntityDamageByEntityEvent evt) {
        if (evt.isCancelled()) {
            return;
        }
        if (evt.getEntity().equals(getPlayer())) {
            onEntityDamaged(evt);
        }
    }

    @EventHandler
    public void onBlockDamagedE(EntityDamageByBlockEvent evt) {
        if (evt.isCancelled()) {
            return;
        }
        if (evt.getEntity().equals(getPlayer())) {
            onBlockDamaged(evt);
        }
    }

    @EventHandler
    public void onDealDamagedE(EntityDamageByEntityEvent evt) {
        if (evt.isCancelled()) {
            return;
        }
        if (evt.getDamager().equals(getPlayer())) {
            onDealDamage(evt);
        }
    }

    @EventHandler
    public void onDealDamageProdE(EntityDamageByEntityEvent evt) {
        if (evt.isCancelled()) {
            return;
        }
        if (evt.getDamager() instanceof Projectile && ((Projectile) evt.getDamager()).getShooter().equals(getPlayer())) {
            onDealProjectileDamage(evt);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent evt) {
        if (evt.isCancelled()) {
            return;
        }
        if (evt.getEntity().equals(getPlayer())) {
            onFoodLevelChanged(evt);
        }
    }


    //being damaged
    protected abstract void onDamaged(EntityDamageEvent evt);

    protected abstract void onBlockDamaged(EntityDamageByBlockEvent evt);

    protected abstract void onEntityDamaged(EntityDamageByEntityEvent evt);

    //damaged others
    protected abstract void onDealDamage(EntityDamageByEntityEvent evt);

    protected abstract void onDealProjectileDamage(EntityDamageByEntityEvent evt);

    protected abstract void onFoodLevelChanged(FoodLevelChangeEvent evt);

    protected abstract void onProjectileLaunch(ProjectileLaunchEvent evt);

    protected abstract void onProjectileHit(ProjectileHitEvent evt);

    protected abstract void onConsume(PlayerItemConsumeEvent evt);

    protected abstract void onCraft(CraftItemEvent evt);

    protected abstract void onInteract(PlayerInteractEvent evt);

    protected abstract void onKill(PlayerDeathEvent evt);

    protected abstract void onKillEntity(EntityDeathEvent evt);

    protected abstract void onStart();


}
