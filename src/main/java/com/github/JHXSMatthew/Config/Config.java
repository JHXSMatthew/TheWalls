package com.github.JHXSMatthew.Config;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    // number of games to "stop" the server
    // -1 will not restart
    public static int GAME_TO_SHUTDOWN = 5;

    public static int wimMoney = 30;
    public static int killMoney = 5;
    public static int RealMoney = 2;
    public boolean isSetUp = false;
    public Location lobby = null;
    private FileConfiguration config;

    public Config(FileConfiguration config) {
        this.config = config;
        
        // 加载配置值，如果不存在则使用默认值
        FileSystem saveFile = new FileSystem("config", "TheWalls");
        lobby = saveFile.getLocation("lobby", true);
        isSetUp = saveFile.getBoolean("setUp");
        
        // 加载经济相关配置
        wimMoney = config.getInt("wimMoney", 30);
        killMoney = config.getInt("killMoney", 5);
        RealMoney = config.getInt("RealMoney", 2);
        GAME_TO_SHUTDOWN = config.getInt("gameToShutdown", 5);
    }
    
    public FileConfiguration getConfig() {
        return config;
    }


    public void save() {
        config.set("setUp", isSetUp);

        FileSystem saveFile = new FileSystem("config", "TheWalls");
        try {
            saveFile.removeLocation("lobby");
        } catch (Exception e) {

        }
        saveFile.addLocation("lobby", lobby, lobby.getWorld().getName());
        saveFile.save();
    }
}
