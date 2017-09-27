package com.github.JHXSMatthew.Controllers;

import com.github.JHXSMatthew.Game.GameMap;
import com.github.JHXSMatthew.Main;
import org.bukkit.Bukkit;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapController {
	
	List<String> mapList;
	
	
	
	
	public MapController(){
		mapList = new ArrayList<String>();
		File dir = new File(Bukkit.getPluginManager().getPlugin(Main.pluginName).getDataFolder() + "/" + "Arena", "");
		String files[] = dir.list();
		for(String s : files){
			if(!s.contains("Basics")){
				continue;
			}
			mapList.add(s.substring(0,s.lastIndexOf("_")));
			Main.get().getLogger().info("GameMap " +  mapList.get(mapList.size() -1 )  + " loaded !");
		}
	}
	
	
	public GameMap pickMap(){
		Random r = new Random();
		String tempName =  mapList.get(r.nextInt(mapList.size()));
		
		File source = new File(Bukkit.getPluginManager().getPlugin("TheWalls").getDataFolder() + "/" + "maps"+ "/" , tempName);
		File target = new File(tempName);
		
		Main.getWc().copyWorld(source,target);
		Main.getWc().loadWorld(tempName);
		
		
		return new GameMap(tempName);
	}
}
