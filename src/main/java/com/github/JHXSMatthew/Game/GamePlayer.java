package com.github.JHXSMatthew.Game;

import com.github.JHXSMatthew.Kits.KitBasic;
import com.github.JHXSMatthew.Kits.KitType;
import com.github.JHXSMatthew.Main;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GamePlayer {


    public boolean notified = false;
    private Player p = null;
    private Game currentGame = null;
    private GameTeam team = null;
    private KitBasic kit = null;
    private HashMap<KitType, Integer> kitLevels;
    private GameStats gs = null;
    private List<Location> fur = null;
    private List<String> trust = null;


    public GamePlayer(Player arg1) {
        p = arg1;
        gs = new GameStats(p.getName());
        kitLevels = Main.getSql().getKitMap(this);
    }

    public HashMap<KitType, Integer> getKitLevels() {
        return kitLevels;
    }

    public GameStats getGs() {
        return gs;
    }

    public KitBasic getKit() {
        ;
        return kit;
    }

    public void setKit(KitBasic basic) {
        this.kit = basic;
    }

    public String getKitName() {
        return kit == null ? "无" : ChatColor.GREEN + kit.getType().getDisplayName() + "(" + kit.getLevel() + ")";
    }

    public Player get() {
        return p;
    }


    public Game getGame() {
        return currentGame;
    }


    public boolean isSpec() {
        if (team == null) {
            return false;

        }
        return team.isSpecTeam();
    }

    public GameTeam getTeam() {
        return team;
    }

    public void setTeam(GameTeam team) {
        this.team = team;
    }

    public void setDefault() {
        p.setGameMode(GameMode.ADVENTURE);
        p.setMaxHealth(40);
        p.setHealth(p.getMaxHealth());
        p.getInventory().clear();
        p.teleport(Main.getGc().getLobby());
        currentGame = null;
        team = null;
    }

    public void setGameLobby(Game g, GameTeam t) {
        p.setGameMode(GameMode.ADVENTURE);
        p.setMaxHealth(40);
        p.setHealth(p.getMaxHealth());
        p.teleport(Main.getGc().getLobby());
        p.getInventory().clear();
        for (PotionEffect pt : p.getActivePotionEffects()) {
            p.removePotionEffect(pt.getType());
        }

        currentGame = g;
        team = t;
    }

    public void setGameSpec(Game g, GameTeam t) {
        currentGame = g;
        final GamePlayer gp = this;
        gp.get().setDisplayName(ChatColor.GRAY + gp.get().getName());
        gp.get().getInventory().clear();
        gp.get().getInventory().setArmorContents(null);
        try {
            team.removeTeam(gp);
        } catch (Exception e) {
            gp.get().teleport(currentGame.getFirstPlayer().get());
        }
        gp.get().setGameMode(GameMode.SPECTATOR);
        t.joinTeam(gp);
        team = t;


    }

    public void setGameBegin() {
        p.setGameMode(GameMode.SURVIVAL);
        p.setHealth(p.getMaxHealth());
        p.setFoodLevel(20);
        p.getInventory().clear();
        team.giveTeamChestPlate(this);
        p.setExp(0);

        //	trusted = new ArrayList<String>();
        trust = new ArrayList<String>();
        fur = new ArrayList<Location>();
        getGs().addgames();
        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 12000, 5));
    }

    public boolean isMyFur(Location l) {
        if (fur == null || fur.size() == 0 || l == null) {
            return false;
        }
        return fur.contains(l);
    }

    public void addFur(Location l) {
        fur.add(l);
    }

    public void removeFur(Location l) {
        fur.remove(l);
    }

    public int howManyFur() {
        return fur.size();
    }

    public void addTrust(String name) {
        trust.add(name);
    }

    public boolean isTrust(String name) {
        return trust.contains(name);
    }

    public void removeTrust(String name) {
        trust.remove(name);
    }

    public void dispose() {
        if (kit != null) {
            kit.dispose();
        }
    }

    public void openFurGUI() {

        Inventory k = Bukkit.createInventory(null, 9, "§b熔炉控制");


        List<GamePlayer> list = team.getPlayers();

        for (int i = 0; i < list.size(); i++) {


            GamePlayer indi = list.get(i);
            if (indi.equals(this)) {
                continue;
            }


            ItemStack item = new ItemStack(Material.SKULL_ITEM);
            item.setDurability((short) 3);
            SkullMeta meta = (SkullMeta) item.getItemMeta();
            List<String> lore = new ArrayList<String>();
            meta.setOwner(indi.get().getName());
            meta.setDisplayName(indi.get().getDisplayName());

            lore.add("§7------------");
            if (!trust.contains(indi.get().getName())) {
                lore.add("§c未共享✘");
                lore.add("§e点击共享熔炉");
            } else {
                lore.add("§a已共享✔");
                lore.add("§e点击取消共享");
            }
            lore.add("§7------------");
            meta.setLore(lore);
            item.setItemMeta(meta);
            k.setItem(i, item);
        }
        p.openInventory(k);
        k = null;
    }


    public void sendActionBar(String msg) {
        Main.getNms().sendActionBar(p, msg);
    }

    public void sendTitle(String msg) {
        Main.getNms().sendTitle(p, 0, 60, 20, " ", msg);
    }

    public void sendTitle(String title, String msg) {
        Main.getNms().sendTitle(p, 0, 60, 20, title, msg);
    }

}
