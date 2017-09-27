package com.github.JHXSMatthew.Gui;

import com.github.JHXSMatthew.Game.Game;
import com.github.JHXSMatthew.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemManager {
	public GuiManager invManager;
	public ArrayList<ItemMeta> metaList = new ArrayList<ItemMeta>();
	public ArrayList<Integer> invIDList = new ArrayList<Integer>();
	private ClickItem ge;
	
	public ItemManager (Game arg){
	
		this.invManager = new GuiManager(this);
		this.ge = new ClickItem(this);
		Main.get().getServer().getPluginManager().registerEvents(this.ge,Main.get());
	}
	
	
	public int isGuiItem(ItemMeta meta){
		int i = 0;
		for (ItemMeta each : this.metaList){
		//	System.out.print("DEBUG: Comparing" + meta.getDisplayName());
		//	System.out.print("DEBUG: Exitsting" + each.getDisplayName());
			
		//	System.out.print("DEBUG: Comparing" + meta.getLore().toString());
	//		System.out.print("DEBUG: Exitsting" + each.getLore().toString());			
			
			
			if(meta.equals(each) ){
		//		System.out.print("SAME!" );
				return i;
			}
			i++;
		}
		return -1;
	}
	
	public void creatrNewGui (int invID , ItemMeta meta, int size , String name){
		this.metaList.add(meta);
	//	System.out.print("Meta ID: "+ this.metaList.indexOf(meta));
	//	System.out.print("A Meta has been add" + meta.getDisplayName());
		this.invIDList.add(invID);
		this.invManager.addInv(size, name);
	}
	
	public void giveItems(Player p, int slot,String name, Material material){
		ItemStack item = new ItemStack (material);
		item.setItemMeta(this.metaList.get(this.invManager.getInvID(name)));
		p.getInventory().setItem(slot , item);
	//	System.out.print("ItemGiveto" + p.getName());
	}
	
	
	
}
