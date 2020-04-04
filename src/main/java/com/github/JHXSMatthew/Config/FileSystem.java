package com.github.JHXSMatthew.Config;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;


public class FileSystem {


    private File FileDir;
    private File pFile;
    private FileConfiguration config;

    public FileSystem(String filename, String pluginname) {

        this.FileDir = new File(Bukkit.getPluginManager().getPlugin(pluginname).getDataFolder(), "");
        this.pFile = new File(Bukkit.getPluginManager().getPlugin(pluginname).getDataFolder(), filename + ".yml");
        this.config = YamlConfiguration.loadConfiguration(pFile);
    }

    public FileSystem(String filename, String pluginname, String Folder) {
        this.FileDir = new File(Bukkit.getPluginManager().getPlugin(pluginname).getDataFolder() + "/" + Folder, "");
        this.pFile = new File(Bukkit.getPluginManager().getPlugin(pluginname).getDataFolder() + "/" + Folder, filename + ".yml");
        this.config = YamlConfiguration.loadConfiguration(pFile);
    }


    public void create() {

        if (!FileDir.exists()) {
            FileDir.mkdir();
        }
        if (!pFile.exists()) {

            try {
                pFile.createNewFile();

            } catch (Exception e) {

                //Log.Error(e.getMessage());
            }

        }
    }


    public void delete() {
        pFile.delete();
    }


    public boolean existFile() {
        if (pFile.exists()) {
            return true;
        }
        return false;
    }


    public void addLocation(String LocationName, Location location, String worldName) {
        if (!existFile()) {
            create();
        }
        config.set(LocationName + ".x", location.getX());
        config.set(LocationName + ".y", location.getY());
        config.set(LocationName + ".z", location.getZ());
        config.set(LocationName + ".world", worldName);
        config.set(LocationName + ".yaw", location.getYaw());
        config.set(LocationName + ".pitch", location.getPitch());
        try {
            config.save(pFile);
        } catch (IOException e) {

            //	Log.Error(e.getMessage());
        }
    }

    public void addSign(String ArenaName, Location location) {
        if (!existFile()) {
            create();
        }

        config.set(ArenaName + ".x", location.getX());
        config.set(ArenaName + ".y", location.getY());
        config.set(ArenaName + ".z", location.getZ());
        config.set(ArenaName + ".world", location.getWorld().getName());
        config.set(ArenaName + ".yaw", location.getYaw());
        config.set(ArenaName + ".pitch", location.getPitch());
        try {
            config.save(pFile);
        } catch (IOException e) {

            //	Log.Error(e.getMessage());
        }


    }


    public void addBasics(String ArenaName, String term, int input) {
        if (!existFile()) {
            create();
        }
        config.set(ArenaName + "." + term, input);
        try {
            config.save(pFile);
        } catch (IOException e) {
            //	Log.Error(e.getMessage());
        }
    }

    public void addBasicsdouble(String ArenaName, String term, double input) {
        if (!existFile()) {
            create();
        }
        config.set(ArenaName + "." + term, input);
        try {
            config.save(pFile);
        } catch (IOException e) {
            //	Log.Error(e.getMessage());
        }
    }

    public void addBasicsString(String ArenaName, String term, String input) {
        if (!existFile()) {
            create();
        }
        config.set(ArenaName + "." + term, input);
        try {
            config.save(pFile);
        } catch (IOException e) {
            //	Log.Error(e.getMessage());
        }
    }

    public String getBasicsString(String ArenaName, String term) {
        if (!existFile()) {
            System.out.println("File not Found");
            return null;
        }
        String returnValue = config.getString(ArenaName + "." + term);
        return returnValue;

    }

    public String getString(String term) {
        if (!existFile()) {
            System.out.println("File not Found");
            return null;
        }
        String returnValue = config.getString(term);
        return returnValue;

    }

    public int getBasics(String ArenaName, String term) {
        if (!existFile()) {
            System.out.println("File not Found");
            return 0;
        }
        int returnValue = config.getInt(ArenaName + "." + term);
        return returnValue;

    }

    public double getBasicsdouble(String ArenaName, String term) {
        if (!existFile()) {
            System.out.println("File not Found");
            return 0;
        }
        double returnValue = config.getDouble(ArenaName + "." + term);
        return returnValue;

    }

    public boolean getBoolean(String term) {
        if (!existFile()) {
            System.out.println("File not Found");
            return false;
        }
        boolean returnValue = config.getBoolean(term);
        System.out.println("Value is " + returnValue);
        return returnValue;

    }


    public Location getLocation(String LocationName, boolean yawandpitch) {
        if (!existFile()) {
            System.out.println("File not Found");
            return null;
        }
        double x = config.getDouble(LocationName + ".x");
        double y = config.getDouble(LocationName + ".y");
        double z = config.getDouble(LocationName + ".z");
        String world = config.getString(LocationName + ".world");
        double yaw = config.getDouble(LocationName + ".yaw");
        double pitch = config.getDouble(LocationName + ".pitch");

        if (world == null) {
            return null;
        }

        if (yawandpitch) {
            return new Location(Bukkit.getWorld(world), x, y, z, (int) yaw, (int) pitch);
        }
        return new Location(Bukkit.getWorld(world), x, y, z);

    }

    public void removeLocation(String LocationName) {
        if (!existFile()) {
            create();
        }
        config.set(LocationName + ".x", null);
        config.set(LocationName + ".y", null);
        config.set(LocationName + ".z", null);
        config.set(LocationName + ".world", null);
        config.set(LocationName + ".yaw", null);
        config.set(LocationName + ".pitch", null);
        config.set(LocationName, null);
        try {
            config.save(pFile);
        } catch (IOException e) {

            //	Log.Error(e.getMessage());
        }

    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void setConfig(FileConfiguration config) {
        this.config = config;
    }

    public void save() {
        config.options().copyDefaults(true);
        try {
            config.save(pFile);
        } catch (IOException e) {

            //	Log.Error(e.getMessage());
        }
        ;
    }

    public File getFileDir() {
        return FileDir;
    }

    public void setFileDir(File fileDir) {
        FileDir = fileDir;
    }

    public File getpFile() {
        return pFile;
    }

    public void setpFile(File pFile) {
        this.pFile = pFile;
    }


}