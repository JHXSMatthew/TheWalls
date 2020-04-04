package com.github.JHXSMatthew.Kits.Skills.paladin;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import com.github.JHXSMatthew.Utils.ItemFactory;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Matthew on 15/07/2016.
 */
public class SK_Paladin_1 extends SkillBasic {

    public static String itemName = ChatColor.GOLD + "圣愈光剑";

    public SK_Paladin_1(KitBasic kit, int innerLevel) {
        super(kit, innerLevel, SkillType.Paladin_1);
        int level = getInnerLevel();
        ItemStack item = ItemFactory.create(Material.IRON_SWORD, (byte) 0, itemName, "所有者: " + ChatColor.RED + getPlayerName());
        net.minecraft.server.v1_8_R3.ItemStack stack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setBoolean("Unbreakable", true);
        stack.setTag(nbt);
        item = CraftItemStack.asCraftMirror(stack);


        if (level >= 1) {
            item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
        }
        if (level >= 2) {
            item.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
        }
        if (level >= 3) {
            item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
        }

        addUnDroppable(item);
        addItemWithSlot(0, item);
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
