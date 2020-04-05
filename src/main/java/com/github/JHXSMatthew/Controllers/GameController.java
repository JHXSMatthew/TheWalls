package com.github.JHXSMatthew.Controllers;

import com.github.JHXSMatthew.Config.Message;
import com.github.JHXSMatthew.Game.Game;
import com.github.JHXSMatthew.Game.GameMap;
import com.github.JHXSMatthew.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import static com.github.JHXSMatthew.Config.Config.GAME_TO_SHUTDOWN;

public class GameController {
    private Game currentGame = null;
    private int count = 0;

    public GameController() {
        if(Main.getCon().lobby != null){
            try {
                // 设置大厅世界属性
                Main.getCon().lobby.getWorld().setTime(0);
                Main.getCon().lobby.getWorld().setGameRuleValue("doDaylightCycle", "false");
                Main.getCon().lobby.getWorld().setStorm(false);
                Main.getCon().lobby.getWorld().setThundering(false);
            } catch (Exception e) {

            }
        }else{
            // 不存在则提示
            Main.get().getServer().getConsoleSender().sendMessage(Main.getMsg().getMessage("msg-no-lobby"));
        }

        if(Main.getMc().hasMap()){
            // 加载一张地图
            newGame();
        }else{
            // 如果没有任何一张可用地图，提示设置地图。
            Main.get().getServer().getConsoleSender().sendMessage(Main.getMsg().getMessage("msg-no-map"));
        }

    }


    public boolean newGame() {
        if (count > GAME_TO_SHUTDOWN) {
            Bukkit.shutdown();
        } else {
            count++;
        }


        try {
            GameMap m = Main.getMc().pickMap();
            World w = Bukkit.getServer().getWorld(m.getName());
            currentGame = new Game(m, w);
            Main.getGuic().returnToZero();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public void setGameNull() {
        currentGame = null;
    }

    public void removeGame(boolean sure) {

        try {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.kickPlayer("游戏重置");
            }

            Main.getWc().deleteWorld(currentGame.getWorld());
        } catch (Exception e) {

        }
        currentGame = null;
        if (!sure) {
            newGame();
        }

    }

    public Location getLobby() {
        return Main.getCon().lobby;
    }

    public Game getGame() {
        return currentGame;
    }


}
