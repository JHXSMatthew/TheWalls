package com.github.JHXSMatthew.Objects;

import com.github.JHXSMatthew.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class Wall {

    public Location up;
    public Location down;

    // define , horizontal is the wall with constant Z

    private boolean horizontal = false;


    public Wall(Location loc1, Location loc2) {
        if (loc1.getY() > loc2.getY()) {
            up = loc1;
            down = loc2;
        } else {
            up = loc2;
            down = loc1;
        }
        if (loc1.getZ() == loc2.getZ()) {
            horizontal = true;
        }
    }

    public void setFallen() {
        World world = up.getWorld();
        
        // 检查游戏是否存在
        if (Main.getGc().getGame() == null) {
            return; // 如果游戏不存在，则不执行任何操作
        }
        
        if (horizontal) {
            int small_x = smaller(down.getBlockX(), up.getBlockX());
            int big_x = bigger(down.getBlockX(), up.getBlockX());
            for (int Y = Main.getGc().getGame().getTop() - 1; Y >= down.getBlockY(); Y--) {
                for (int X = small_x; X <= big_x; X++) {
                    world.getBlockAt(X, Y, up.getBlockZ()).setType(Material.AIR);
                }
            }
            for (int Y = down.getBlockY() - 1; Y < Main.getGc().getGame().getDown(); Y--) {
                for (int X = small_x; X <= big_x; X++) {
                    world.getBlockAt(X, Y, up.getBlockZ()).setType(Material.STONE);
                }
            }

        } else {
            int small_z = smaller(down.getBlockZ(), up.getBlockZ());
            int big_z = bigger(down.getBlockZ(), up.getBlockZ());
            for (int Y = Main.getGc().getGame().getTop() - 1; Y >= down.getBlockY(); Y--) {
                for (int Z = small_z; Z <= big_z; Z++) {
                    world.getBlockAt(up.getBlockX(), Y, Z).setType(Material.AIR);
                }
            }
            for (int Y = down.getBlockY() - 1; Y < Main.getGc().getGame().getDown(); Y--) {
                for (int Z = small_z; Z <= big_z; Z++) {
                    world.getBlockAt(up.getBlockX(), Y, Z).setType(Material.STONE);
                }
            }
        }
    }

    public boolean isWallBlock(Location l) {

        if (horizontal) {
            if (l.getBlock().getZ() == up.getBlockZ()) {
                return true;
            }
        } else {
            if (l.getBlock().getX() == up.getBlockX()) {
                return true;
            }
        }
		/*
		if(l.getBlockX() < down.getBlockX() || l.getBlockX() > up.getBlockX()){
			System.out.print("it's not x wall block");
			return false;
		}
		if(l.getBlockZ() < down.getBlockZ() || l.getBlockZ() > up.getBlockZ()){
	    	System.out.print("it's not z wall block");
			return false;
		}
		*/

        return false;


    }

    public int bigger(int a, int b) {
        if (a > b) {
            return a;
        }
        return b;
    }

    public int smaller(int a, int b) {
        if (a > b) {
            return b;
        }
        return a;
    }
}
