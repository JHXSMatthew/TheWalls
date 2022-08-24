package com.github.JHXSMatthew;

import com.github.JHXSMatthew.Config.Config;
import com.github.JHXSMatthew.Config.Message;
import com.github.JHXSMatthew.Controllers.*;
import com.github.JHXSMatthew.Listeners.BlockListener;
import com.github.JHXSMatthew.Listeners.GuiListener;
import com.github.JHXSMatthew.Listeners.PlayerListener;
import com.github.JHXSMatthew.Objects.NMS;
import com.github.JHXSMatthew.Objects.NMSHandler;
import lombok.Getter;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public class Main extends JavaPlugin {
    public static boolean isEditMode = false;

    public static Main instance;

    public static String pluginName = "TheWalls";
    @Getter
    private static GameController gc;
    @Getter
    private static WorldController wc;
    @Getter
    private static MapController mc;
    @Getter
    private static WallsController wac;
    @Getter
    private static PlayerController pc;
    @Getter
    private static NMS nms;
    @Getter
    private static ChestControl cc;
    @Getter
    private static Message msg;
    @Getter
    private static ItemController ic;
    @Getter
    private static GUIController guic;
    @Getter
    private static MySQLController sql;

    //Vault start
    public static Permission permission = null;
    public static Economy economy = null;
    public static Chat chat = null;
    //Vault end

    public static Config getCon() {
        return c;
    }

    public static Config c;

    public static BungeeController getBc() {
        return bungee;
    }

    private static BungeeController bungee;

    public ChestControl chest = new ChestControl(this);

    boolean bc = false;
    Logger logger = Logger.getLogger("Minecraft");
    ConsoleCommandSender sender;

    public static Main get() {
        return instance;
    }

    public void onDisable() {
        this.logger.info("[战墙] 正在关闭 - by jhxs QQ 68638023 !");
        for (Player p : getServer().getOnlinePlayers()) {
            p.teleport(gc.getLobby());
        }
        if (c.isSetUp) {
            gc.removeGame(true);
        }
    }

    public void onEnable() {
        sender = Bukkit.getConsoleSender();
        instance = this;

        logger.info("============ 战墙初始化 ============");
        sender.sendMessage(ChatColor.AQUA + "-->加载配置文件");
        saveDefaultConfig();
        msg = new Message();
        sender.sendMessage("    " + ChatColor.GREEN + "成功加载消息");
        c = new Config(getConfig());
        sender.sendMessage("    " + ChatColor.GREEN + "成功加载配置");
        sender.sendMessage(ChatColor.AQUA + "-->加载命令");
        Bukkit.getPluginCommand("wall").setExecutor(new Command());
        sender.sendMessage(ChatColor.GREEN + "    命令加载成功");
        sender.sendMessage(ChatColor.AQUA + "-->加载管理器");
        wc = new WorldController();
        sender.sendMessage("    " + ChatColor.GREEN + "世界管理器加载成功");
        wac = new WallsController();
        sender.sendMessage("    " + ChatColor.GREEN + "城墙管理器加载成功");
        nms = new NMSHandler();
        sender.sendMessage("    " + ChatColor.GREEN + "NMS包加载成功");


        if (c.isSetUp) {
            guic = new GUIController();
            sender.sendMessage("    " + ChatColor.GREEN + "菜单控制器加载成功");
            pc = new PlayerController();
            sender.sendMessage("    " + ChatColor.GREEN + "玩家管理器加载成功");
            mc = new MapController();
            sender.sendMessage("    " + ChatColor.GREEN + "地图管理器加载成功");
            gc = new GameController();
            sender.sendMessage("    " + ChatColor.GREEN + "游戏管理器加载成功");
            cc = new ChestControl();
            sender.sendMessage("    " + ChatColor.GREEN + "箱子管理器加载成功");
            bungee = new BungeeController();
            sender.sendMessage("    " + ChatColor.GREEN + "跨服管理器加载成功");
            ic = new ItemController();
            sender.sendMessage("    " + ChatColor.GREEN + "物品管理器加载成功");

            sql = new MySQLController();
            sql.openConnection();

            sender.sendMessage("    " + ChatColor.GREEN + "数据库管理器加载成功");
            sender.sendMessage(ChatColor.AQUA + "-->加载监听器");
            getServer().getPluginManager().registerEvents(new BlockListener(), this);
            getServer().getPluginManager().registerEvents(new PlayerListener(), this);
            getServer().getPluginManager().registerEvents(new GuiListener(), this);
            getServer().getPluginManager().registerEvents(bungee, this);
            getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
            getServer().getMessenger().registerOutgoingPluginChannel(this, "LobbyConnect");
            sender.sendMessage(ChatColor.GREEN + "    监听器加载成功");

            try {
                sender.sendMessage(ChatColor.AQUA + "-->注册权限经济绑定");
                setupEconomy();
                sender.sendMessage(ChatColor.GREEN + "    经济系统绑定成功");
                setupChat();
                sender.sendMessage(ChatColor.GREEN + "    聊天系统绑定成功");
                setupPermissions();
                sender.sendMessage(ChatColor.GREEN + "    权限系统绑定成功");
                sender.sendMessage(ChatColor.GREEN + "-->加载成功");
            } catch (Exception e) {
                sender.sendMessage(ChatColor.RED + "无法加载!");
            }
        }
        sender.sendMessage("============ 初始化完毕 ============");
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }

        return (chat != null);
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

}