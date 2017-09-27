package com.github.JHXSMatthew.Game;

import com.github.JHXSMatthew.Config.FileSystem;
import com.github.JHXSMatthew.Main;
import com.github.JHXSMatthew.Objects.Wall;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class GameMap {

	private String name;
	private String displayName = null;
	protected List<Location> spawnPoints;
	private List<Location> limits;
	private List<Wall> walls;
	private int playerLimit;
	private boolean isLoaded= false;
	private int wallTime;
	private  double percentage;
	private String builder = null;
	
	
	
	public GameMap(String arg){
		spawnPoints = new ArrayList<Location> ();
		limits = new ArrayList<Location> ();
		walls = new ArrayList<Wall>();
		
		name = arg;
		if(!load()){
			this.isLoaded = false;
			return;
		}
		this.isLoaded = true;

		
	}
	public void setDisplayName(String name){
		this.displayName = name;
	}
	
	public void setBound(Location up, Location down){
		limits.add(up);
		limits.add(down);
	}
	
	public void setName(String name){
		 this.name = name;
	}
	
	public void addSpawnPoints(Location e){
		spawnPoints.add(e);
	}
	
	public void setPlayer(int arg){
		playerLimit = arg;
	}
	
	public void setWallTime(int arg){
		wallTime = arg;
	}
	public void setPercentage(float arg){
		percentage = arg;
	}

	public void setBuilder(String arg){
		builder = arg;
	}
	
	public String getBuilder(){
		return builder;
	}
	public void save(){
		FileSystem saveFile = new FileSystem(name + "_Basics", "TheWalls", "Arena");
		saveFile.addBasics(name, "playerLimit", playerLimit);
		saveFile.addBasics(name, "wallTime", wallTime);
		saveFile.addBasicsdouble(name, "percentage", percentage);
		saveFile.addBasicsString(name, "displayName", displayName);
		saveFile.addBasicsString(name, "builder", builder);
		saveFile.save();
		
		//locations
		saveFile = new FileSystem(this.name + "_Locations", "TheWalls", "Arena");
		
		//get two location exact
		Location up =  limits.get(0);//saveFile.getLocation("bound1", true);
		Location down  = limits.get(1);//saveFile.getLocation("bound2", true);
		assert(up !=null && down != null);
		if(up.getX() < down.getX()){
			double temp = up.getX();
			up.setX(down.getX());
			down.setX(temp);
		}
		if(up.getZ() < down.getZ()){
			double temp = up.getZ();
			up.setZ(down.getZ());
			down.setZ(temp);
		}
		
		if(up.getY() < down.getY()){
			double temp = up.getY();
			up.setY(down.getY());
			down.setY(temp);
		}
		saveFile.addLocation("bound1", up,name);
		saveFile.addLocation("bound2", down,name);
		
		
		//walls
		Main.getWac().saveWall(saveFile,this);
		assert(walls.size() % 2 == 0);
		//spawns
		
		for(int i = 0 ; i < spawnPoints.size(); i ++){
			saveFile.addLocation("spawn" + i, spawnPoints.get(i), name);
		}
	
	}
	

	

	
	public double getPercentage(){
		return percentage;
	}
	
	public int getWallTime(){
		return wallTime;
	}
	
	public String getName(){
		return name;
	}
	
	public Location getSpawnPoints(int index){
		return spawnPoints.get(index);
	}
	
	public String getDisplayerName(){
		return displayName;
	}
	
	public int getSpanwPointsCount(){
		return spawnPoints.size();
	}
	
	public void addWall(Wall w){
		 walls.add(w);
	}
	public int getPlayerLimit(){
		return playerLimit;
	}
	
	private boolean load(){
		if(this.name == null){
			return false;
		}
		try{
			FileSystem saveFile = new FileSystem(name + "_Basics", "TheWalls", "Arena");
			
			playerLimit = saveFile.getBasics(this.name, "playerLimit");
			wallTime = saveFile.getBasics(this.name, "wallTime");
			percentage = saveFile.getBasicsdouble(this.name, "percentage");
			displayName = saveFile.getBasicsString(name, "displayName");
			builder = saveFile.getBasicsString(name, "builder");
			//locations
			saveFile = new FileSystem(this.name + "_Locations", "TheWalls", "Arena");
			//get two location exact
			Location up = saveFile.getLocation("bound1", true);
			Location down  = saveFile.getLocation("bound2", true);
			assert(up !=null && down != null);
			if(up.getX() < down.getX()){
				double temp = up.getX();
				up.setX(down.getX());
				down.setX(temp);
			}
			if(up.getZ() < down.getZ()){
				double temp = up.getZ();
				up.setZ(down.getZ());
				down.setZ(temp);
			}
			
			if(up.getY() < down.getY()){
				double temp = up.getY();
				up.setY(down.getY());
				down.setY(temp);
			}
			limits.add(up);
			limits.add(down);
			
			//load all walls
			Main.getWac().loadWalls(this);
			assert(walls.size() % 2 == 0);
			//load all spawns
			int i = 0;
			while(saveFile.getLocation("spawn" + i, true) != null){
				spawnPoints.add(saveFile.getLocation("spawn" + i, true));
				i++;
			}
			
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	
	public List<Wall> getWalls(){
		return walls;
	}
	
	public boolean isLoaded(){
		return this.isLoaded;
	}

	public Location getUp(){
		return limits.get(0);
	}
	
	public Location getDown(){
		return limits.get(1);
	}
	
}
