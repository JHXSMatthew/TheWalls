package com.github.JHXSMatthew.Controllers;

import com.github.JHXSMatthew.Game.Game;
import com.github.JHXSMatthew.Game.GameMap;
import com.github.JHXSMatthew.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
public class GameController {
	private Game currentGame =null ;
	private int count = 0;
	//private Location lobby = null;
	
	public GameController(){
		try{
			Main.getCon().lobby.getWorld().setTime(0);
			Main.getCon().lobby.getWorld().setGameRuleValue("doDaylightCycle","false");
			Main.getCon().lobby.getWorld().setStorm(false);
			Main.getCon().lobby.getWorld().setThundering(false);
		}catch(Exception e){

		}
		newGame();
	}
	
	
	public boolean newGame(){
		if(count > 5){
			Bukkit.shutdown();
		}else{
			count ++;
		}

		
		try{
			GameMap m = Main.getMc().pickMap();
			World w = Bukkit.getServer().getWorld(m.getName());
			currentGame = new Game(m,w);
			Main.getGuic().returnToZero();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	public void setGameNull(){
		currentGame = null;
	}
	
	public void removeGame(boolean sure){

		try{
			for(Player p : Bukkit.getOnlinePlayers()){
				p.kickPlayer("游戏重置");
			}
			
				Main.getWc().deleteWorld(currentGame.getWorld());
		}catch(Exception e){
			
		}
		currentGame = null;
		if(!sure){
			newGame();
		}
		
	}
	
	public Location getLobby(){
		return Main.getCon().lobby;
	}
	
	public Game getGame(){
		return currentGame;
	}
	
	

}
