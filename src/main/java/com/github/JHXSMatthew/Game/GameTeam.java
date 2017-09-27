package com.github.JHXSMatthew.Game;

import com.github.JHXSMatthew.Main;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class GameTeam {
	private Location l;
	private List<GamePlayer> list = null;
	private boolean spec = false;
	private String name;
	private Color color = null;
	
	public GameTeam(Location spawnPoints, boolean spec){
		this.spec = spec;
		l = spawnPoints;
		list = new ArrayList<GamePlayer>();
	}
	
	
	public boolean isInTeam(GamePlayer arg){
		return list.contains(arg);
	}
	
	public void setName(String n){
		name = n;
		color = Main.getMsg().phaseCorlor(name);
	}
	
	public String getName(){
		return name;
	}
	
	public void removeTeam(GamePlayer gp){
		
		list.remove(gp);
		Main.getGuic().subTeamCount(name);
	}
	
	public boolean joinTeam(GamePlayer arg){
		list.add(arg);
		giveTeamChestPlate(arg);
		Main.getGuic().addTeamCount(name);
		
		return true;
	}
	
	public void giveTeamChestPlate(GamePlayer arg){
		arg.get().getInventory().setArmorContents(null);
		ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		LeatherArmorMeta meta = (LeatherArmorMeta) chestplate.getItemMeta();
		meta.setColor(color);
		meta.setDisplayName(name);
		chestplate.setItemMeta(meta);
		arg.get().getInventory().setChestplate( chestplate);
		
	}
	
	
	public void setLocation (Location arg){
		l = arg;
	}
	
	public void prepareTeamForBegin(){
		for(GamePlayer p : list){
			p.get().teleport(l);
			p.get().setVelocity(new Vector(0,0,0));
			p.get().setFallDistance(0);
			p.setGameBegin();
			p.get().setDisplayName(name.substring(0,2) + p.get().getName());
		}
	}
	
	public void initialTeamScoreBoard(Team t){
		for(GamePlayer p : list){
			t.addEntry(p.get().getName());
		}
		
		t.setAllowFriendlyFire(false);
		t.setCanSeeFriendlyInvisibles(true);
		t.setPrefix(name.substring(0,2));
		
	}
	
	public void sendTeamMessage(String arg){
		for(GamePlayer gp : list){
			gp.get().sendMessage(arg);
		}
		if(spec){
			Bukkit.getConsoleSender().sendMessage("[观察者]" + arg);
		}else{
			Bukkit.getConsoleSender().sendMessage(arg);
		}
	}
	
	public Location getLocation(){
		return l;
	}
	
	public boolean isSpecTeam(){
		return spec;
	}
	public int getPlyaerAmount(){
		return list.size();
	}
	
	public List<GamePlayer> getPlayers(){
		return list;
	}
	
	public void sendTeamActionBar(String msg){
		for(GamePlayer p : list){
			p.sendActionBar(msg);
		}
	}
}
