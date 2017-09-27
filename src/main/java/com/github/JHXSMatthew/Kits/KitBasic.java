package com.github.JHXSMatthew.Kits;

import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import com.github.JHXSMatthew.Main;
import com.mcndsj.GameEvent.Events.GameEndEvent;
import com.mcndsj.GameEvent.Events.GameStartEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

public abstract class KitBasic implements Listener {
	private String name;
	protected int level = 1;
	private KitType type;
	private UUID owner;
	private List<SkillBasic> skill;


	public KitBasic(UUID uuid,int level,KitType type){
		this.owner = uuid;
		this.level = level;
		this.type = type;
		skill = new ArrayList<>();

		for(SkillType skills : getType().getSkillTypes()){
			if(level >= skills.getUnlockLevel() ){
				skill.add(skills.getSkill(this,level));
			}
		}
	}


	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}


	public KitType getType(){
		return this.type;
	}


	public Player getPlayer(){
		return Bukkit.getPlayer(owner);
	}

	public String getPlayerName(){
		return getPlayer().getName();
	}

	public List<String> getDescription(){
		ArrayList<String> string = new ArrayList<>();
		for(SkillBasic basic : skill){
			string.add(basic.getDescription());
		}
		return string;
	}

	public void dispose(){
		for(SkillBasic basic : skill){
			basic.dispose();
		}
	}

	public int getLevel(){
		return this.level;
	}






	
	
}
