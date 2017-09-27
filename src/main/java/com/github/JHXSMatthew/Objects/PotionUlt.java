package com.github.JHXSMatthew.Objects;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffectType;

public class PotionUlt {

	
	public static boolean isNegativePotion(PotionEffectType pe){
		if(pe.equals(PotionEffectType.SLOW) || pe.equals(PotionEffectType.BLINDNESS) || pe.equals(PotionEffectType.CONFUSION) || pe.equals(PotionEffectType.HARM) 
				|| pe.equals( PotionEffectType.HUNGER) ||  pe.equals(PotionEffectType.POISON) ||  pe.equals(PotionEffectType.SLOW_DIGGING) ||  pe.equals(PotionEffectType.WEAKNESS) || pe.equals( PotionEffectType.WITHER)){
			return true;
		}
		return false;
	}
	
    public static void firework(Player player){
        Firework fw = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
        FireworkMeta fwmeta = fw.getFireworkMeta();
        FireworkEffect.Builder builder = FireworkEffect.builder();
        builder.withTrail();
        builder.withFlicker();
        builder.withFade(Color.RED);
        builder.withColor(Color.GRAY);
        builder.withColor(Color.SILVER);
        builder.with(FireworkEffect.Type.BALL_LARGE);
        fwmeta.addEffects(builder.build());
        fwmeta.setPower(1);
        fw.setFireworkMeta(fwmeta);
    }
}
