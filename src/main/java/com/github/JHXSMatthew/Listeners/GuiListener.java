package com.github.JHXSMatthew.Listeners;

import com.github.JHXSMatthew.Game.Game;
import com.github.JHXSMatthew.Game.GamePlayer;
import com.github.JHXSMatthew.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GuiListener implements Listener {
    @EventHandler
    public void onGuiClick(InventoryClickEvent evt) {
        Player p = (Player) evt.getWhoClicked();
        GamePlayer gp = Main.getPc().getGamePlayer(p);
        Game g = gp.getGame();
        if (g == null) {
            return;
        }
        if (g.getGameState() >= 2) {
            return;
        }
        evt.setCancelled(true);
        Inventory click = evt.getClickedInventory();
        if (click == null) {
            return;
        }
        if (click.getTitle() == null) {
            return;
        }
        if (click.getTitle().contains("队伍")) {
            ItemStack item = evt.getCurrentItem();
            if (item == null) {
                return;
            }
            if (!item.hasItemMeta()) {
                return;
            }
            g.joinTeamRequest(gp, item.getItemMeta().getDisplayName());
            p.closeInventory();
            return;
        }

        if (click.getTitle().contains("职业")) {
            ItemStack item = evt.getCurrentItem();
            if (item == null) {
                return;
            }
            if (!item.hasItemMeta()) {
                return;
            }

            p.closeInventory();
        }
    }
}
