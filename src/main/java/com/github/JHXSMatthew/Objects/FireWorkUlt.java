package com.github.JHXSMatthew.Objects;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

import java.util.Random;

;

public class FireWorkUlt {
	
	
	public static void spawnFireWork(Location loc , World w){
		 Firework fw = (Firework) w.spawn(loc.clone().add(new Vector(getRandomNum(5, -5), 0.6, getRandomNum(5, -5))), Firework.class);
		 FireworkMeta meta = fw.getFireworkMeta();
		 FireworkEffect effect = getFireworkEffect(getRandomColor(),getRandomColor(), getRandomColor(), getRandomColor(), getRandomColor(), getRandomType());
		 meta.addEffect(effect);
		 meta.setPower(getRandomNum(4, 1));
		 fw.setFireworkMeta(meta);
		 
	}
	public static FireworkEffect getFireworkEffect(Color one, Color two, Color three, Color four, Color five, Type type) {
		return FireworkEffect.builder().flicker(false).withColor(one, two, three, four).withFade(five).with(type).trail(true).build();
	}
	
	public static int getRandomNum(int max, int min) {
		Random rand = new Random();
	    int ii = min + rand.nextInt(((max - (min)) + 1));
	    rand = null;
	    return ii;
	}
	
	public static Type getRandomType() {
		int type = getRandomNum(5, 1);
		switch (type) {
		case 1: return Type.STAR;
		case 2: return Type.CREEPER;
		case 3: return Type.BURST;
		case 4: return Type.BALL_LARGE;
		case 5: return Type.BALL;
		default: return Type.STAR;
		}
	}
	
	public static Color getRandomColor() {
		int color = getRandomNum(17, 1);
		switch (color) {
		case 1: return Color.AQUA;
		case 2: return Color.BLACK;
		case 3: return Color.BLUE;
		case 4: return Color.FUCHSIA;
		case 5: return Color.GRAY;
		case 6: return Color.GREEN;
		case 7: return Color.LIME;
		case 8: return Color.MAROON;
		case 9: return Color.NAVY;
		case 10: return Color.OLIVE;
		case 11: return Color.ORANGE;
		case 12: return Color.PURPLE;
		case 13: return Color.RED;
		case 14: return Color.SILVER;
		case 15: return Color.TEAL;
		case 16: return Color.WHITE;
		case 17: return Color.YELLOW;
		default: return Color.RED;
		}
	}
	
}
