package com.github.JHXSMatthew.Game;

import com.github.JHXSMatthew.Main;
import com.github.JHXSMatthew.Objects.SQLStatsContainer;
import lombok.Getter;

import java.sql.SQLException;

public class GameStats {

    @Getter
    private final String name;
    @Getter
    private int games = 0;
    @Getter
    private int wins = 0;
    @Getter
    private int kills = 0;
    @Getter
    private int death = 0;
    @Getter
    private int money = 0;
    @Getter
    private boolean isNew = true;
    private boolean changed = false;

    public GameStats(String name) {
        this.name = name;
        load();
    }


    private void load() {
        if (Main.getSql() == null) {
            return;
        }
        SQLStatsContainer ct = null;
        try {
            ct = Main.getSql().loadStats(name);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (ct == null) {
            System.out.print("STATS LOAD ERROR");
            return;
        }
        if (!ct.New) {
            games = ct.games;
            wins = ct.wins;
            isNew = ct.New;
            money = ct.money;
            kills = ct.kills;
            death = ct.death;
        }

    }

    public void save() {
        if (Main.getSql() == null) {
            return;
        }
        if (changed || isNew) {
            Main.getSql().savePlayerData(this);
        }
    }

    public void addWin() {
        changed = true;
        wins++;
    }

    public void addgames() {
        changed = true;
        games++;
    }

    public void addDeath() {
        changed = true;
        death++;
    }

    public void addKills() {
        changed = true;
        kills++;
    }

    public void giveMoney(int amount) {
        changed = true;
        money += amount;
    }

}
