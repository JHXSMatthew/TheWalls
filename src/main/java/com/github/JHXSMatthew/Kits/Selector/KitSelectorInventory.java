package com.github.JHXSMatthew.Kits.Selector;

import com.github.JHXSMatthew.Game.GamePlayer;
import com.github.JHXSMatthew.Kits.KitPrice;
import com.github.JHXSMatthew.Kits.KitType;
import com.github.JHXSMatthew.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Matthew on 2016/5/21.
 */
public class KitSelectorInventory implements Listener {
    GamePlayer owner;
    Inventory inv;

    public KitSelectorInventory(GamePlayer gp) {
        owner = gp;
        Bukkit.getPluginManager().registerEvents(this, Main.get());
    }


    @EventHandler
    public void onClick(InventoryClickEvent evt) {
        if (!evt.getInventory().equals(inv) || !evt.getWhoClicked().equals(owner.get())) {
            return;
        }
        if (evt.getCurrentItem() == null || evt.getCurrentItem().getType() == Material.AIR) {
            return;
        }
        evt.setCancelled(true);
        for (KitType type : KitType.values()) {
            if (type.getDisplayNameWithColor().equals(evt.getCurrentItem().getItemMeta().getDisplayName())) {
                if (owner != null && owner.getKitLevels() != null && owner.getKitLevels().containsKey(type) && owner.getKitLevels().get(type) > 0) {
                    if (owner.getKit() != null)
                        owner.getKit().dispose();

                    owner.setKit(type.getKit(owner.get(), owner.getKitLevels().get(type)));
                    owner.sendTitle(ChatColor.GREEN + "选择职业 " + ChatColor.GOLD + type.getDisplayName() + "(" + owner.getKitLevels().get(type) + ")" + ChatColor.GREEN + " 成功");
                    Main.getGc().getGame().updateScorebaordLobby(owner);
                    Main.getSql().setCurrentKit(owner);
                    owner.get().getPlayer().closeInventory();
                    break;
                } else {
                    owner.sendTitle(ChatColor.GREEN + "尚未拥有", ChatColor.YELLOW + "在大厅通过代币可以购买升级该职业!");
                    owner.get().getPlayer().closeInventory();
                    break;
                }
            }
        }
    }

    @EventHandler
    public void closeEvent(InventoryCloseEvent evt) {
        if (evt.getInventory().equals(inv)) {
            HandlerList.unregisterAll(this);
        }
    }

    public void open() {
        inv = Bukkit.createInventory(null, 54, "选择职业");
        boolean allNull = false;
        if (owner.getKitLevels() == null)
            allNull = true;

        for (KitType type : KitType.values()) {
            if (allNull || !owner.getKitLevels().containsKey(type) || owner.getKitLevels().get(type) <= 0) {
                inv.addItem(fillInfo(type.getItem(), 0, KitPrice.getMax(type), null));
            } else {
                inv.addItem(fillInfo(type.getItem(), owner.getKitLevels().get(type), KitPrice.getMax(type), type.getDescription(owner.getKitLevels().get(type))));
            }
        }
        owner.get().openInventory(inv);
    }


    private ItemStack fillInfo(ItemStack source, int current, int max, List<String> currentDes) {
        if (current == 0 || currentDes == null) {
            ItemMeta meta = source.getItemMeta();
            List<String> lore = meta.getLore();
            lore.add(" ");
            lore.add(ChatColor.YELLOW + "当前等级: " + ChatColor.GREEN + current);
            lore.add(ChatColor.YELLOW + "最高等级: " + ChatColor.GREEN + max);
            lore.add(ChatColor.RED + "未拥有");
            meta.setLore(lore);
            source.setItemMeta(meta);
            return source;
        }
        ItemMeta meta = source.getItemMeta();
        List<String> lore = meta.getLore();
        lore.add(" ");
        lore.add(ChatColor.YELLOW + "当前等级: " + ChatColor.GREEN + current);
        lore.add(ChatColor.YELLOW + "最高等级: " + ChatColor.GREEN + max);
        lore.add(ChatColor.YELLOW + "当前技能表:");
        lore.addAll(currentDes);
        meta.setLore(lore);
        source.setItemMeta(meta);
        return source;
    }


    private String getPlaceholder(String original) {
        Pattern pattern = Pattern.compile(".*(%.*%).*");
        Matcher matcher = pattern.matcher(original);
        matcher.matches();
        return matcher.group(1);
    }
}
