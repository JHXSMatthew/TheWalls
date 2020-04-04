package com.github.JHXSMatthew;


import com.github.JHXSMatthew.Config.Config;
import com.github.JHXSMatthew.Config.Message;
import com.github.JHXSMatthew.Controllers.*;
import com.github.JHXSMatthew.Game.GameMap;
import com.github.JHXSMatthew.Listeners.BlockListener;
import com.github.JHXSMatthew.Listeners.GuiListener;
import com.github.JHXSMatthew.Listeners.PlayerListener;
import com.github.JHXSMatthew.Listeners.SelectEvent;
import com.github.JHXSMatthew.Objects.NMS;
import com.github.JHXSMatthew.Objects.NMSHandler;
import com.github.JHXSMatthew.Objects.Wall;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;


public class Main extends JavaPlugin {
    public static boolean isEditMode = false;

    public static Main instance;

    public static String pluginName = "TheWalls";
    public static Config c;
    private static GameController gc;
    private static WorldController wc;
    private static MapController mc;
    private static WallsController wac;
    private static PlayerController pc;
    private static NMS nms;
    private static ChestControl cc;
    private static Message msg;
    private static ItemController ic;
    private static GUIController guic;
    private static MySQLController sql;
    private static SelectEvent se = null;
    private static BungeeController bungee;

//	SignEvent sign = new SignEvent(this);
    public ChestControl chest = new ChestControl(this);
    boolean bc = false;
    Logger logger = Logger.getLogger("Minecraft");
    private GameMap setMap = null;

    public static com.github.JHXSMatthew.Config.Config getCon() {
        return c;
    }

    public static ItemController getIc() {
        return ic;
    }

    public static ChestControl getCc() {
        return cc;
    }

    public static PlayerController getPc() {
        return pc;
    }

    public static NMS getNms() {
        return nms;
    }

    public static WorldController getWc() {
        return wc;
    }

    public static MapController getMc() {
        return mc;
    }

    public static Main get() {
        return instance;
    }

    public static Message getMsg() {
        return msg;
    }

    public static BungeeController getBc() {
        return bungee;
    }

    public static GameController getGc() {
        return gc;
    }

    public static GUIController getGuic() {
        return guic;
    }

    public static MySQLController getSql() {
        return sql;
    }

    public static WallsController getWac() {
        return wac;
    }

    public void onDisable() {
        this.logger.info("[战墙] 正在关闭 - by jhxs QQ 68638023 !");
        for (Player p : getServer().getOnlinePlayers()) {
            p.teleport(gc.getLobby());
        }

        gc.removeGame(true);
    }


    public void onEnable() {
        instance = this;


        logger.info("============ 战墙初始化 ============");
        logger.info("-->加载配置文件");
        saveDefaultConfig();
        msg = new Message();
        logger.info("    Msg done");
        c = new Config(getConfig());
        logger.info("    Config done");

        logger.info("-->加载控制器");

        wc = new WorldController();
        logger.info("    WC done");
        wac = new WallsController();
        logger.info("    Walls done");
        nms = new NMSHandler();
        logger.info("    NMS done");


        if (c.isSetUp) {
            guic = new GUIController();
            logger.info("    GUIC done");
            pc = new PlayerController();
            logger.info("    PC done");
            mc = new MapController();
            logger.info("    MC done");
            gc = new GameController();
            logger.info("    GC done");
            cc = new ChestControl();
            logger.info("    CC done");
            bungee = new BungeeController();
            logger.info("    BC done。");
            ic = new ItemController();
            logger.info("    IC done");

            sql = new MySQLController();
            sql.openConnection();

            logger.info("    MYSQL done!");
            logger.info("-->加载事件");
            getServer().getPluginManager().registerEvents(new BlockListener(), this);
            getServer().getPluginManager().registerEvents(new PlayerListener(), this);
            getServer().getPluginManager().registerEvents(new GuiListener(), this);
            getServer().getPluginManager().registerEvents(bungee, this);
            getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
            getServer().getMessenger().registerOutgoingPluginChannel(this, "LobbyConnect");

        }


        logger.info("============ 初始化完毕 ============");


    }


    public boolean onCommand(CommandSender sender, Command cmd, String cl, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        if (!cl.equals("wall")) {
            return false;
        }
        if (!sender.isOp()) {
            return false;
        }

        if (args.length < 1) {
            return false;
        }

        Player p = (Player) sender;

        if (args[0].equals("wand")) {
            if (se == null) {
                se = new SelectEvent();
                this.getServer().getPluginManager().registerEvents(se, this);
            }
            se.getWand(p);
            p.sendMessage("wand done");
            return true;

        }

        if (args[0].equals("start")) {
            gc.getGame().switchState(2);
            return true;
        }

        if (args[0].equals("create")) {
            if (args.length < 3) {
                p.sendMessage("Wrong length!");
                return true;
            }
            if (setMap != null) {
                setMap.save();
                setMap = new GameMap(args[1]);

            } else {
                setMap = new GameMap(args[1]);
            }
            setMap.setDisplayName(args[2]);
            p.sendMessage("created!");
            return true;

        }
        if (args[0].equals("builder")) {
            if (args.length < 2) {
                p.sendMessage("Wrong length!");
                return true;
            }
            setMap.setBuilder(args.toString().replace("builder", ""));
            return true;
        }

        if (setMap == null) {
            p.sendMessage("fucking empty");
            return false;
        }

        if (args[0].equals("bound")) {
            setMap.setBound(se.left.clone(), se.right.clone());

            p.sendMessage(se.left.toString());
            p.sendMessage(se.right.toString());
            se.clear();
            return true;
        }

        if (args[0].equals("wall")) {
            setMap.addWall(new Wall(se.left.clone(), se.right.clone()));
            p.sendMessage(se.left.toString());
            p.sendMessage(se.right.toString());
            se.clear();
            p.sendMessage("wall : " + setMap.getWalls().size());
            return true;
        }

        if (args[0].equals("spawn")) {
            setMap.addSpawnPoints(p.getLocation());
            p.sendMessage("count " + setMap.getSpanwPointsCount());
            return true;
        }

        if (args[0].equals("player")) {
            if (args.length < 2) {
                p.sendMessage("Wrong length!");
                return true;
            }

            setMap.setPlayer(Integer.parseInt(args[1]));
            p.sendMessage(args[1]);
            return true;
        }

        if (args[0].equals("walltime")) {
            if (args.length < 2) {
                p.sendMessage("Wrong length!");
                return true;
            }

            setMap.setWallTime(Integer.parseInt(args[1]));
            p.sendMessage(args[1]);
            return true;
        }

        if (args[0].equals("percentage")) {
            if (args.length < 2) {
                p.sendMessage("Wrong length!");
                return true;
            }

            setMap.setPercentage(Float.parseFloat(args[1]));
            p.sendMessage(args[1]);
            return true;
        }


        if (args[0].equals("lobby")) {
            c.lobby = p.getLocation();
            p.sendMessage(c.lobby.toString());
            return true;
        }


        if (args[0].equals("save")) {
            setMap.save();

            File target = new File(Bukkit.getPluginManager().getPlugin("TheWalls").getDataFolder() + "/" + "maps" + "/", setMap.getName());
            File source = new File(p.getWorld().getName());
            wc.copyWorld(source, target);
            c.save();
            p.sendMessage("saved");
            return true;
        }

        return false;
    }


}