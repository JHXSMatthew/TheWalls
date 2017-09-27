package com.github.JHXSMatthew.Objects;

import org.bukkit.Chunk;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface NMS {

	public void respawnPlayer(Player player);
	public void updateChunks(World world, List<Chunk> chunks);
	public void sendParticles(World world, String type, float x, float y, float z, float offsetX, float offsetY, float offsetZ, float data, int amount);
	public String getName(ItemStack stack);
	public FireworkEffect getFireworkEffect(Color one, Color two, Color three, Color four, Color five, Type type);
	public void sendTitle(Player player, int fadein, int stay, int fadeout, String title, String subtitle);
	public boolean isOnePointSeven();
	public void sendActionBar(Player p, String msg) ;
}
