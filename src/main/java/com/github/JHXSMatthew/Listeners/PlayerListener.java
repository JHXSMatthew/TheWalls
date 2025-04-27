package com.github.JHXSMatthew.Listeners;

import com.github.JHXSMatthew.Config.Config;
import com.github.JHXSMatthew.Config.Message;
import com.github.JHXSMatthew.Game.Game;
import com.github.JHXSMatthew.Game.GamePlayer;
import com.github.JHXSMatthew.Game.GameState;
import com.github.JHXSMatthew.Game.GameTeam;
import com.github.JHXSMatthew.Kits.Selector.KitSelectorInventory;
import com.github.JHXSMatthew.Main;
import com.github.JHXSMatthew.Objects.PotionUlt;
import org.bukkit.*;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlayerListener implements Listener {
    private final ArrayList<String> noSpam;

    public PlayerListener() {
        noSpam = new ArrayList<String>();

        new BukkitRunnable() {
            @Override
            public void run() {
                noSpam.clear();
            }

        }.runTaskTimerAsynchronously(Main.get(), 20, 100);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent evt) {
        Player player = evt.getPlayer(); // The player who joined
        GamePlayer p = Main.getPc().createGamePlayer(player);
        Main.getGc().getGame().joinGame(p);
        evt.setJoinMessage("");

    }

    @EventHandler
    public void onMove(PlayerMoveEvent evt) {
        if (evt.getPlayer().getWorld().getName().equals("lobby")) return;

        if (Main.getGc().getGame() != null) {
            Game g = Main.getGc().getGame();
            double y = evt.getTo().getY();
            if (y >= g.getTop() || y <= g.getDown()) {
                if (evt.getPlayer().getGameMode().equals(GameMode.SPECTATOR)) {
                    evt.getPlayer().teleport(g.getFirstPlayer().get());
                } else {
                    evt.setCancelled(true);
                }
            }
            if (!g.isBuildAllow(evt.getTo())) {
                if (g.getGameState() == GameState.LOBBY || g.getGameState() == GameState.STARTING) {
                    evt.setCancelled(false);
                    return;
                }
                evt.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent evt) {
        Player player = evt.getPlayer(); // The player who joined
        GamePlayer p = Main.getPc().getGamePlayer(player);
        Game g = p.getGame();
        if (g == null) {
            return;
        }
        p.getGs().save();

        g.quitGame(p);
        Main.getPc().removeGamePlayer(p.get().getName());

        if (g.getGameState().ordinal() > 1 && g.getPlayerCount() == 0) {
            try {
                Main.getGc().removeGame(false);
            } catch (Exception ignored) {

            }
        }
        try {
            if (g.getGameStateString().contains("游戏") && !evt.getPlayer().getWorld().getName().contains("lobby")) {
                if (evt.getPlayer().getGameMode() != GameMode.SPECTATOR) {

                    for (ItemStack item : evt.getPlayer().getInventory().getContents()) {
                        if (item == null) continue;
                        if (item.getType() == Material.AIR) continue;
                        evt.getPlayer().getWorld().dropItemNaturally(evt.getPlayer().getLocation(), item);
                    }
                    for (ItemStack item : evt.getPlayer().getInventory().getArmorContents()) {
                        if (item == null) continue;
                        if (item.getType() == Material.AIR) continue;
                        evt.getPlayer().getWorld().dropItemNaturally(evt.getPlayer().getLocation(), item);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        evt.getPlayer().getInventory().clear();
        evt.getPlayer().getInventory().setArmorContents(null);
        evt.setQuitMessage("");
    }

    // control

    @EventHandler
    public void onCraft(CraftItemEvent evt) {
        if (evt.getRecipe() == null) {
            return;
        }
        if (evt.getRecipe().getResult() == null) {
            return;
        }
        if (evt.getRecipe().getResult().getType().equals(Material.GOLDEN_APPLE) && evt.getRecipe().getResult().getDurability() == 1) {
            evt.setCancelled(true);
        }
        if (evt.getRecipe().getResult().getType().equals(Material.BOAT)) {
            evt.setCancelled(true);
        }
        if (evt.getRecipe().getResult().getType().equals(Material.MINECART)) {
            evt.setCancelled(true);
        }
        if (evt.isCancelled()) {
            evt.getWhoClicked().sendMessage(Message.prefix + "该物品无法在战墙游戏内合成!");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onControlInteract(PlayerInteractEvent evt) {
        Player p = evt.getPlayer();
        GamePlayer gp = Main.getPc().getGamePlayer(p);
        Game g = gp.getGame();
        if (g == null) {
            evt.setCancelled(true);
            return;
        }

        if (g.getGameState() == GameState.WALL_NOT_FALL) {
            if (p.getItemInHand() == null) {
                return;
            }
            if (p.getItemInHand().getType().equals(Material.LAVA) || p.getItemInHand().getType().equals(Material.LAVA_BUCKET)) {
                p.sendMessage(Message.prefix + Main.getMsg().getMessage("no-before-wallfall"));
                evt.setCancelled(true);
            }
        }
		
		/*
		if(g.getGameState() == 0 || g.getGameState() == 1){
			if(!Main.isEditMode){
				evt.setCancelled(true);
			}
			return;
		}
		*/
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onControlPostionSplash(PotionSplashEvent evt) {
        ProjectileSource shooter = evt.getEntity().getShooter();
        if (!(shooter instanceof Player)) {
            return;
        }
        //System.out.print("event triggerd onControlPostionSplash");

        Player shooter_Player = (Player) shooter;
        GamePlayer shooter_GamePlayer = Main.getPc().getGamePlayer(shooter_Player);

        Collection<PotionEffect> ef = evt.getPotion().getEffects();
        Collection<LivingEntity> entity = evt.getAffectedEntities();
        for (LivingEntity e : entity) {
            if (!(e instanceof Player)) {
                continue;
            }

            Player p = (Player) e;
            GamePlayer gp = Main.getPc().getGamePlayer(p);
            //System.out.print("FOR " + p.getName());
            for (PotionEffect f : ef) {
                //System.out.print("FOR Effect" + f.getType().getName() );
                if (PotionUlt.isNegativePotion(f.getType())) {
                    //	System.out.print("Negative Effect");
                    if (gp.getTeam().isInTeam(shooter_GamePlayer)) {
                        evt.setIntensity(e, 0);
                        //System.out.print("No harm onControlPostionSplash");
                    }
                } else {
                    //System.out.print("Positive Effect");
                    if (!gp.getTeam().isInTeam(shooter_GamePlayer)) {
                        evt.setIntensity(e, 0);
                        //System.out.print("No good onControlPostionSplash");
                    }
                }
            }

        }
    }


    @EventHandler(priority = EventPriority.HIGH)
    public void onControlDamage(EntityDamageEvent evt) {
        Entity e = evt.getEntity();
        if (!(e instanceof Player)) {
            return;
        }
        Player p = (Player) e;
        GamePlayer gp = Main.getPc().getGamePlayer(p);
        Game g = gp.getGame();

        if (g == null) {
            evt.setCancelled(true);
            return;
        }

        if (g.getGameState() == GameState.FINISHING) {
            evt.setCancelled(true);
            return;
        }
        if (p.getGameMode() == GameMode.SPECTATOR) {
            evt.setCancelled(true);
            return;
        }

        if (g.getGameState() == GameState.LOBBY || g.getGameState() == GameState.STARTING) {
            evt.setCancelled(true);
            if (evt.getCause() == DamageCause.VOID) {
                gp.get().teleport(Main.getGc().getLobby());
            }
            return;
        }
        if (!(evt instanceof EntityDamageByEntityEvent)) {
            return;
        }
        EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) evt;
        Entity damager = event.getDamager();
        if (damager instanceof Player) {
            Player damager_Player = (Player) damager;
            GamePlayer damager_GamePlayer = Main.getPc().getGamePlayer(damager_Player);
            if (gp.getTeam().isInTeam(damager_GamePlayer) && damager_GamePlayer.getTeam().isInTeam(gp))
                event.setCancelled(true);
            return;
        }

        if (damager instanceof Arrow) {
            ProjectileSource shooter = ((Arrow) damager).getShooter();
            if (!(shooter instanceof Player)) {
                return;
            }
            Player damager_Player = (Player) shooter;
            GamePlayer damager_GamePlayer = Main.getPc().getGamePlayer(damager_Player);
            if (gp.getTeam().isInTeam(damager_GamePlayer) && damager_GamePlayer.getTeam().isInTeam(gp))
                event.setCancelled(true);
        }

    }

    @EventHandler
    public void handleItemDrop(PlayerDropItemEvent evt) {
        if (Main.getGc().getGame().getGameState().ordinal() < 2) {
            evt.setCancelled(true);
        }
    }


    //feature
    @EventHandler
    public void chat(AsyncPlayerChatEvent evt) {
        boolean isAll = false;
        evt.setCancelled(true);
        if (noSpam.contains(evt.getPlayer().getName())) {
            return;
        }

        noSpam.add(evt.getPlayer().getName());
        if (evt.getMessage().startsWith("!") || evt.getMessage().startsWith("！")) {
            evt.setMessage(evt.getMessage().substring(1));
            isAll = true;
        }

        String playerGroup = Main.chat.getPrimaryGroup(evt.getPlayer());
        String prefix = Main.chat.getGroupPrefix("", playerGroup).replace("&", "§");
        String playerPrefix = Main.chat.getPlayerPrefix(evt.getPlayer()).replace("&", "§");
        StringBuilder finalMessage = new StringBuilder();
        finalMessage.append(prefix);
        if (!playerPrefix.equals(prefix)) {
            finalMessage.append(playerPrefix);
        }
        finalMessage.append(evt.getPlayer().getDisplayName()).append(ChatColor.GOLD).append(" >> ").append(ChatColor.GRAY).append(evt.getMessage());


        GamePlayer gamePlayer = Main.getPc().getGamePlayer(evt.getPlayer());
        GameTeam gameTeam = gamePlayer.getTeam();
        Game game = gamePlayer.getGame();
        if (gameTeam == null || game == null) {
            return;
        }

        try {
            if (!gamePlayer.notified && !gamePlayer.isSpec() && gamePlayer.getGame().getGameState() == GameState.WALL_NOT_FALL) {
                gamePlayer.get().sendMessage(Message.prefix + Main.getMsg().getMessage("notify-chat-format1"));
                gamePlayer.get().sendMessage(Message.prefix + Main.getMsg().getMessage("notify-chat-format2"));
                gamePlayer.notified = true;
            }
        } catch (Exception ignored) {
        }

        // 全体发言 或

        if (isAll || game.getGameState().ordinal() < 2 || gamePlayer.isSpec() || game.getGameState() == GameState.FINISHING) {
            if (gamePlayer.isSpec() && game.getGameState() != GameState.FINISHING) {
                gameTeam.sendTeamMessage(finalMessage.toString());
                return;
            }
            gamePlayer.getGame().sendAllChatMessage(finalMessage.toString());
        } else {
            gameTeam.sendTeamMessage(finalMessage.toString());
        }

    }


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent evt) {
        Player p = evt.getEntity();
        GamePlayer gp = Main.getPc().getGamePlayer(p);
        Game g = gp.getGame();
        if (g == null) {
            System.out.print("BUG ON DEATH");
        }


        p.setHealth(p.getMaxHealth());
        p.getWorld().strikeLightningEffect(p.getLocation());
        if (p.getKiller() instanceof Player) {
            GamePlayer GKiller = Main.getPc().getGamePlayer(p.getKiller());
            GKiller.getGs().addKills();
            GKiller.getGs().giveMoney(Config.killMoney);
            p.getKiller().sendMessage(Message.prefix + Main.getMsg().getMessage("kill-coin-earn1") + Config.killMoney + Main.getMsg().getMessage("kill-coin-earn2"));
            g.sendToAllMessage(p.getKiller().getDisplayName() + Main.getMsg().getMessage("player-death-msg1") + p.getDisplayName() + Main.getMsg().getMessage("player-death-msg2"));
            gp.sendTitle(Main.getMsg().getMessage("player-death-title"));
            gp.getGs().addDeath();

        } else {
            g.sendToAllMessage(p.getDisplayName() + Main.getMsg().getMessage("player-death-msg3"));
        }

        g.joinSpec(gp);

    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent evt) {
        if (evt.getWorld().getName().contains("lobby")) {
            evt.setCancelled(true);
        }
    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void onFeatureInteract(PlayerInteractEvent evt) {
	/*	if(evt.isCancelled()){
			return;
		}
		*/

        Player p = evt.getPlayer();
        GamePlayer gp = Main.getPc().getGamePlayer(p);
        Game g = gp.getGame();

        if (g == null) {
            return;
        }
        if (g.getGameState().ordinal() < 2) {
            if (evt.getAction() == Action.LEFT_CLICK_BLOCK || evt.getAction() == Action.LEFT_CLICK_AIR || evt.getAction() == Action.RIGHT_CLICK_BLOCK || evt.getAction() == Action.RIGHT_CLICK_AIR) {
                if (p.getItemInHand() == null || evt.getPlayer().getItemInHand().equals(Material.AIR)) {
                    return;
                }
                ItemStack item = p.getItemInHand();
                if (!item.hasItemMeta()) {
                    return;
                }
                ItemMeta meta = item.getItemMeta();
                if (!meta.hasDisplayName()) {
                    return;
                }

                if (Main.getIc().isKitsItem(item)) {
                    KitSelectorInventory ki = new KitSelectorInventory(gp);
                    ki.open();
                    return;
                }
                if (Main.getIc().isQuitItem(item)) {
                    Main.getBc().quitSend(p);
                    return;
                }
                if (Main.getIc().isTeamChoseItem(item)) {

                    p.openInventory(Main.getGuic().getTeamChoose());
                    p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
                    return;
                }
            }
            evt.setCancelled(true);
            return;
        }

        if (g.getGameState() != GameState.WALL_NOT_FALL) {
            return;
        }

        if (evt.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (evt.getClickedBlock().getState() instanceof Furnace) {
                if (p.getItemInHand() == null || p.getItemInHand().getType().equals(Material.AIR)) {
                    if (gp.isMyFur(evt.getClickedBlock().getLocation())) {
                        gp.openFurGUI();
                    }
                }
            }

        } else if (evt.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (evt.getClickedBlock().getState() instanceof Furnace) {
                if (gp.isMyFur(evt.getClickedBlock().getLocation())) {
                    return;
                }

                for (GamePlayer temp : gp.getTeam().getPlayers()) {
                    if (temp.isMyFur(evt.getClickedBlock().getLocation())) {
                        if (temp.isTrust(p.getName())) return;
                        p.sendMessage(Message.prefix + Main.getMsg().getMessage("not-trust-fur"));
                        evt.setCancelled(true);
                        return;
                    }
                }
            }
        }

    }

    @EventHandler
    public void onFurAllow(InventoryClickEvent evt) {
        if (evt.getInventory().getName().contains("熔炉") && evt.getCurrentItem() != null) {
            evt.setCancelled(true);
            Player p = (Player) evt.getWhoClicked();
            GamePlayer gp = Main.getPc().getGamePlayer(p);


            if (evt.getRawSlot() < evt.getInventory().getSize() && !evt.getCurrentItem().getType().equals(Material.AIR)) {
                Player allow = Bukkit.getPlayer(evt.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.RED.toString(), "").replace(ChatColor.GREEN.toString(), "").replace(ChatColor.YELLOW.toString(), "").replace(ChatColor.BLUE.toString(), ""));
                List<String> lore = evt.getCurrentItem().getItemMeta().getLore();
                if (allow != null) {
                    if (lore.get(1).contains("未")) {
                        gp.addTrust(allow.getName());
                        evt.getWhoClicked().closeInventory();
                        evt.getWhoClicked().sendMessage(ChatColor.AQUA + "YourCraft >>" + " 您将" + allow.getDisplayName() + ChatColor.AQUA + "加入共享熔炉列表.");


                    } else {
                        gp.removeTrust(allow.getName());
                        evt.getWhoClicked().closeInventory();
                        evt.getWhoClicked().sendMessage(ChatColor.AQUA + "YourCraft >>" + " 您将" + allow.getDisplayName() + ChatColor.AQUA + "移出共享熔炉列表.");
                    }
                } else {
                    //System.out.print("FUR_____Player is NULL!");
                }

            }
        }

    }


}
