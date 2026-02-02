package com.github.JHXSMatthew.Gui;

import com.github.JHXSMatthew.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClickItem implements Listener {
    private ItemManager im;

    public ClickItem(ItemManager arg) {
        this.im = arg;
    }


    @EventHandler
    public void onClick(PlayerInteractEvent evt) {

        if (evt.getItem() != null) {
            if (Main.getGc().getGame() == null || Main.getGc().getGame().getGameState().ordinal() < 2) {


                //	System.out.print("Item not Null");
                if (evt.getItem().hasItemMeta()) {
                    if (!evt.getItem().getItemMeta().getLore().isEmpty()) {
                        //	System.out.print("Not Null lore");
                        int i = im.isGuiItem(evt.getItem().getItemMeta());

                        //	System.out.print("Event has been triggered");
                        if (i != -1) {
                            //		System.out.print("This item has been detected as GUI item!"+ i);
                            this.im.invManager.openGui(i, evt.getPlayer());
                        }
                    }
                }
            }
        }
    }
}
