package com.github.JHXSMatthew.Kits.allKits;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.KitType;
import com.github.JHXSMatthew.Utils.ItemFactory;
import org.bukkit.*;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * Created by Matthew on 2016/5/21.
 */
public class Enderman extends KitBasic implements Listener {

    ItemStack item;
    long last;
    Location l;

    public Enderman(UUID uuid, int level) {
        super(uuid, level, KitType.enderman);
        item = ItemFactory.create(Material.SUGAR,(byte)0, ChatColor.DARK_PURPLE+ "末影【特殊物品】", "右击记录当前位置", + (5+level*2) + "秒内可再次右击", "回到上次记录点.");
        addItemWithSlot(0,item);
    }

    @Override
    protected void onInteract(PlayerInteractEvent evt) {
        if(evt.getItem() == null || evt.getItem().getType().equals(Material.AIR)){
            return;
        }
        if(evt.getItem().isSimilar(item)){
            if(last + (level * 2 + 5) * 1000 > System.currentTimeMillis()  || l == null){
                l = getPlayer().getLocation();
                getPlayer().playSound(l, Sound.CLICK,1F,1F);
            }else{
                getPlayer().getLocation().getWorld().playEffect(getPlayer().getLocation(), Effect.ENDER_SIGNAL,0);
                getPlayer().getWorld().playSound(getPlayer().getLocation(),Sound.ENDERMAN_TELEPORT,1F,1F);
                getPlayer().teleport(l);
                getPlayer().getWorld().playSound(getPlayer().getLocation(),Sound.ENDERMAN_TELEPORT,1F,1F);
                l = null;
            }
        }
    }

    @Override
    protected void onKill(PlayerDeathEvent evt) {

    }

    @Override
    protected void onStart() {

    }
}
