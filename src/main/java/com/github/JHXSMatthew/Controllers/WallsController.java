package com.github.JHXSMatthew.Controllers;

import com.github.JHXSMatthew.Config.FileSystem;
import com.github.JHXSMatthew.Game.GameMap;
import com.github.JHXSMatthew.Objects.Wall;

import java.util.List;

public class WallsController {

	
	
	
	public void loadWalls(GameMap m){
		FileSystem saveFile = new FileSystem(m.getName() + "_Locations", "TheWalls", "Arena");
		recursiveLoad(m,0,saveFile);
	}
	
	private void recursiveLoad(GameMap m, int count, FileSystem file){
		if(file.getLocation("wallsOne"+count, false) != null){
			try{
				System.out.print("load wall "  + count);
				m.addWall(new Wall(file.getLocation("wallsOne"+count, false),file.getLocation("wallsTwo"+count, false)));
				recursiveLoad(m,count + 1, file);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void saveWall(FileSystem saveFile,GameMap m){
		
		List<Wall> w = m.getWalls();
		int count = w.size();
		for(int i = 0 ; i < count ; i ++){
			saveFile.addLocation("wallsOne" + i, w.get(i).up , m.getName());
			saveFile.addLocation("wallsTwo" + i, w.get(i).down , m.getName());
		}
		
		saveFile.save();
	}
}
