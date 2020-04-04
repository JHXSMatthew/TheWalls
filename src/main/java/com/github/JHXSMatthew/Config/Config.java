package com.github.JHXSMatthew.Config;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    public static int wimMoney = 30;
    public static int killMoney = 5;
    public static int RealMoney = 2;
    public boolean isSetUp = false;
    public Location lobby = null;
    private FileConfiguration config;

    public Config(FileConfiguration config) {
        FileSystem saveFile = new FileSystem("config", "TheWalls");
        lobby = saveFile.getLocation("lobby", true);
        isSetUp = saveFile.getBoolean("setUp");
        this.config = config;
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
