package com.github.JHXSMatthew.Listeners;

import com.github.JHXSMatthew.Config.Message;
import com.github.JHXSMatthew.Game.Game;
import com.github.JHXSMatthew.Game.GamePlayer;
import com.github.JHXSMatthew.Main;
import com.github.JHXSMatthew.Objects.PotionUlt;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.Random;

public class BlockListener implements Listener {


    //control

    @EventHandler
    public void onPistonExtension(BlockPistonExtendEvent evt) {
        for (Block b : evt.getBlocks()) {
            if (!Main.getGc().getGame().isBuildAllow(b.getLocation())) {
                evt.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler
    public void onPistonRestrict(BlockPistonRetractEvent evt) {
        for (Block b : evt.getBlocks()) {
            if (!Main.getGc().getGame().isBuildAllow(b.getLocation())) {
                evt.setCancelled(true);
                return;
            }
        }

    }

    @EventHandler
    public void onEntityExplore(EntityExplodeEvent evt) {
        Iterator<Block> iter = evt.blockList().iterator();
        while (iter.hasNext()) {
            Location l = iter.next().getLocation();
            if (!Main.getGc().getGame().isBuildAllow(l)) {
                iter.remove();
            }
        }
    }


    @EventHandler(priority = EventPriority.HIGH)
    public void onControlPlaceBlock(BlockPlaceEvent evt) {
        Player p = evt.getPlayer();
        GamePlayer gp = Main.getPc().getGamePlayer(p);
        Game g = gp.getGame();

        if (!g.isBuildAllow(evt.getBlock().getLocation())) {
            evt.setCancelled(true);
            return;
        }

        if (g.getGameState() == 2) {
            Block b = evt.getBlock();
            if (b.getType().equals(Material.LAVA) || p.getItemInHand().getType().equals(Material.BUCKET) || p.getItemInHand().getType().equals(Material.LAVA_BUCKET)) {
                evt.setCancelled(true);
                return;
            }
        }


    }

    @EventHandler
    public void onHopperEvent(InventoryMoveItemEvent evt) {
        if (Main.getGc().getGame() != null && Main.getGc().getGame().getGameState() == 2) {
            if (evt.getDestination().getType().equals(InventoryType.HOPPER) && evt.getSource().getType().equals(InventoryType.FURNACE)) {
                evt.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onControlBreakBlock(BlockBreakEvent evt) {
        Player p = evt.getPlayer();
        GamePlayer gp = Main.getPc().getGamePlayer(p);
        Game g = gp.getGame();


        if (!g.isBuildAllow(evt.getBlock().getLocation())) {
            evt.setCancelled(true);
        }
    }


    // feature
    @EventHandler(priority = EventPriority.NORMAL)
    public void onFeaturePlace(BlockPlaceEvent evt) {
        if (evt.isCancelled()) {
            return;
        }

        Player p = evt.getPlayer();
        GamePlayer gp = Main.getPc().getGamePlayer(p);
        Game g = gp.getGame();

        if (evt.getBlock().getState() instanceof Furnace) {
            if (g.getGameState() != 2) {
                return;
            }
            if (gp.howManyFur() > 1) {

                p.sendMessage(Message.prefix + Main.getMsg().getMessage("too-much-fur"));
                return;
            }

            gp.addFur(evt.getBlock().getLocation());
            p.sendMessage(Message.prefix + Main.getMsg().getMessage("fur-place-success"));

        }
    }


    @EventHandler(priority = EventPriority.NORMAL)
    public void onFeatureBreak(BlockBreakEvent evt) {
        if (evt.isCancelled()) {
            return;
        }

        if (evt.getBlock().getState() instanceof Furnace) {
            Location l = evt.getBlock().getLocation();
            Player p = evt.getPlayer();
            GamePlayer gp = Main.getPc().getGamePlayer(p);
            Game g = gp.getGame();


            if (g.getGameState() == 2 && !gp.isMyFur(l)) {
                evt.setCancelled(true);
                p.sendMessage(Main.getMsg().getMessage("not-your-fur"));
                return;
            }

            gp.removeFur(l);
            return;
        }


        if (evt.getBlock().getType().equals(Material.STONE)) {
            Random r = new Random();

            if (r.nextInt(140) == 1) {
                evt.getBlock().setType(Material.CHEST);
                Chest c = (Chest) evt.getBlock().getState();
                PotionUlt.firework(evt.getPlayer());
                evt.getPlayer().sendMessage(Message.prefix + Main.getMsg().getMessage("lucky-chest-found"));
                evt.setCancelled(true);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        setLoot(c);
                        cancel();
                    }
                }.runTaskLater(Main.get(), 2);

            }
            return;
        }
    }

    private void setLoot(Chest c) {
        c.getBlockInventory().setContents(Main.getCc().generateLoot(c.getBlockInventory().getSize(), 0));
    }


}
