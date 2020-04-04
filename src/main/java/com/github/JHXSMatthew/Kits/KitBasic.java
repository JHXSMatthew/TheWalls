package com.github.JHXSMatthew.Kits;

import com.github.JHXSMatthew.Kits.Skills.SkillBasic;
import com.github.JHXSMatthew.Kits.Skills.SkillType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class KitBasic implements Listener {
    protected int level = 1;
    private String name;
    private KitType type;
    private UUID owner;
    private List<SkillBasic> skill;


    public KitBasic(UUID uuid, int level, KitType type) {
        this.owner = uuid;
        this.level = level;
        this.type = type;
        skill = new ArrayList<>();

        for (SkillType skills : getType().getSkillTypes()) {
            if (level >= skills.getUnlockLevel()) {
                skill.add(skills.getSkill(this, level));
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public KitType getType() {
        return this.type;
    }


    public Player getPlayer() {
        return Bukkit.getPlayer(owner);
    }

    public String getPlayerName() {
        return getPlayer().getName();
    }

    public List<String> getDescription() {
        ArrayList<String> string = new ArrayList<>();
        for (SkillBasic basic : skill) {
            string.add(basic.getDescription());
        }
        return string;
    }

    public void dispose() {
        for (SkillBasic basic : skill) {
            basic.dispose();
        }
    }

    public int getLevel() {
        return this.level;
    }


}
