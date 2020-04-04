package com.github.JHXSMatthew.Config;


import com.github.JHXSMatthew.Main;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Random;

public class Message {

    public static String prefix;
    private int i = 0;


    private HashMap<String, String> store;

    public Message() {
        store = new HashMap<String, String>();
        loadMessages();

        new BukkitRunnable() {
            Random r = new Random();

            @Override
            public void run() {
                i = r.nextInt(4);
            }

        }.runTaskTimerAsynchronously(Main.get(), 0, 100);


    }

    public void loadMessages() {
        prefix = ChatColor.AQUA + "YourCraft >> " + ChatColor.RESET;
        store.put("join-team-msg1", ChatColor.AQUA + " 加入了 ");
        store.put("lobby-quit-msg1", ChatColor.YELLOW + "玩家 " + ChatColor.RESET);
        store.put("lobby-quit-msg2", ChatColor.YELLOW + " 离开了游戏。");
        store.put("game-quit-msg1", ChatColor.YELLOW + "玩家");
        store.put("game-quit-msg2", ChatColor.YELLOW + "临阵脱逃 .");
        store.put("time-jump-to-30", ChatColor.RED + "时间调至 30 秒");
        store.put("start-count-msg3", ChatColor.AQUA + "游戏还有 " + ChatColor.GREEN);
        store.put("start-count-msg4", ChatColor.AQUA + " 秒开始!");
        store.put("start-count-msg1", ChatColor.GREEN + "== 距离开始 ");
        store.put("start-count-msg2", ChatColor.GREEN + " 秒 == ");


        store.put("wall-fall-time3", ChatColor.RED + "距离大战 " + ChatColor.GREEN);
        store.put("wall-fall-time4", ChatColor.RED + " 秒");
        store.put("wall-fall-time1", ChatColor.GRAY + ChatColor.BOLD.toString() + "== 距离战墙倒塌 ");
        store.put("wall-fall-time2", ChatColor.GRAY + ChatColor.BOLD.toString() + " 秒 ==");

        store.put("not-enough-people", ChatColor.RED + "人数不足!");

        store.put("wall-fall-msg", ChatColor.YELLOW + "战墙已经倒塌，快提起你的剑大战一场吧！");
        store.put("wall-fall-title", ChatColor.RED + "大战开始");

        store.put("player-death-msg1", ChatColor.YELLOW + " 将玩家 ");
        store.put("player-death-msg2", ChatColor.YELLOW + " 撕成了碎片!");
        store.put("player-death-msg", ChatColor.YELLOW + " 死了 !");
        store.put("player-death-msg3", ChatColor.YELLOW + " 一不小心死了.");

        store.put("lucky-chest-found", ChatColor.AQUA + " 幸运宝箱！?");

        store.put("player-death-title", ChatColor.RED + ChatColor.BOLD.toString() + "你死了!");

        store.put("team-join-msg", ChatColor.YELLOW + " 更换队伍至 ");
        store.put("team-join-full", ChatColor.RED + "目标队伍已满员或与别队人数相差太大.");
        store.put("team-join-same", ChatColor.RED + "您已经在这个队伍里了!");

        store.put("no-before-wallfall", ChatColor.YELLOW + " 只有战墙倒塌后才可以这么做.");

        store.put("kill-coin-earn1", ChatColor.GREEN + " 成功击杀敌人,获得代币 ");
        store.put("kill-coin-earn2", ChatColor.GREEN + " 枚 .");
        store.put("win-coin-earn1", ChatColor.GREEN + " 您赢了! 获得代币奖励 ");
        store.put("win-coin-earn2", ChatColor.GREEN + " 枚 .");

        store.put("all-msg-prefix", "[所有人] " + ChatColor.RESET);
        store.put("notify-chat-format1", ChatColor.RED + "您当前是 队伍聊天 模式。");
        store.put("notify-chat-format2", ChatColor.RED + "如果想让所有人都看到您的聊天，请在前面加!。 例如: !大家好");

        store.put("not-trust-fur", ChatColor.YELLOW + "熔炉主人尚未给您权限.您可以叫他使用左键点击熔炉给予您权限。");
        store.put("too-much-fur", ChatColor.YELLOW + "您同时拥有的熔炉太多了，该熔炉将不会受到保护!");
        store.put("fur-place-success", ChatColor.YELLOW + "熔炉保护成功,您可以左键点击熔炉给予队友开启权限.");
    }

    public String getMessage(String key) {
        String s = store.get(key);
        if (s == null) {
            return key;
        }
        return store.get(key);
    }

    public String parseTeamName(int i) {
        switch (i) {
            case 0:
                return ChatColor.RED + "红队";
            case 1:
                return ChatColor.YELLOW + "黄队";
            case 2:
                return ChatColor.BLUE + "蓝队";
            case 3:
                return ChatColor.GREEN + "绿队";

        }
        return "未知";
    }


    public String getSpecMsg() {
        String msg;
        switch (i) {
            case 1:
                msg = ChatColor.GREEN + ChatColor.BOLD.toString() + "== 使用/likai 可以回到大厅 ==";
                break;
            case 2:
                msg = ChatColor.GREEN + ChatColor.BOLD.toString() + "== 大键盘 2 键选择观战队伍并传送至玩家身边 ==";
                break;
            case 3:
                msg = ChatColor.GREEN + ChatColor.BOLD.toString() + "== 左键点击存活玩家即可附身观察 ==";
                break;
            default:
                msg = ChatColor.GREEN + ChatColor.BOLD.toString() + "== 您现在是观察者模式 ==";
        }
        return msg;
    }

    public Color parseColor(String name) {
        if (name.contains("红")) {
            return Color.RED;
        }
        if (name.contains("黄")) {
            return Color.YELLOW;
        }
        if (name.contains("蓝")) {
            return Color.BLUE;
        }
        if (name.contains("绿")) {
            return Color.GREEN;
        }
        return null;

    }


    public DyeColor parseDyeColor(String name) {
        if (name.contains("红")) {
            return DyeColor.RED;
        }
        if (name.contains("黄")) {
            return DyeColor.YELLOW;
        }
        if (name.contains("蓝")) {
            return DyeColor.BLUE;
        }
        if (name.contains("绿")) {
            return DyeColor.GREEN;
        }
        return null;

    }

}
