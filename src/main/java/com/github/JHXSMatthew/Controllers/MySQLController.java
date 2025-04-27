package com.github.JHXSMatthew.Controllers;

import com.github.JHXSMatthew.Game.GamePlayer;
import com.github.JHXSMatthew.Game.GameStats;
import com.github.JHXSMatthew.Kits.KitType;
import com.github.JHXSMatthew.Objects.SQLStatsContainer;
import com.huskehhh.mysql.mysql.MySQL;
import org.bukkit.Bukkit;

import java.sql.*;
import java.util.HashMap;

@SuppressWarnings("CallToPrintStackTrace")
public class MySQLController {

    private final static String TABLE_NAME = "TheWalls";
    private final MySQL my;
    private Connection c = null;

    public MySQLController() {
        this.my = new MySQL("192.168.123.2", "3306", "games", "game", "NO_PUBLIC_SECTION");
    }

    public void openConnection() {
        try {
            c = my.openConnection();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.print("Connection error !");
            e.printStackTrace();
        }
    }


    public void closeConnection() throws SQLException {
        this.c.close();
    }


    public void closeDB() throws SQLException {
        this.my.closeConnection();
    }

    public HashMap<KitType, Integer> getKitMap(GamePlayer gp) {
        HashMap<KitType, Integer> map = null;
        try {
            if (!this.my.checkConnection()) {
                this.c = this.my.openConnection();
            }
            PreparedStatement s = this.c.prepareStatement("SELECT * FROM `TheWallsKits` " + "WHERE id = (SELECT TheWalls.id FROM TheWalls WHERE TheWalls.Name = ?);");
            s.setString(1, gp.get().getName());
            ResultSet result = s.executeQuery();
            if (result.next()) {

                map = new HashMap<>();
                for (KitType type : KitType.values())
                    map.put(type, result.getInt(type.getDBName()));
                try {
                    String current = result.getString("current");
                    if (current != null) {
                        gp.setKit(KitType.getType(current).getKit(gp.get(), map.get(KitType.getType(current))));
                    }
                } catch (Exception ignored) {

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public void setCurrentKit(GamePlayer gp) {
        try {
            if (!this.my.checkConnection()) {
                this.c = this.my.openConnection();
            }
            PreparedStatement s = this.c.prepareStatement("UPDATE `TheWallsKits` " + "SET current = '" + gp.getKit().getType().getDBName() + "' " + "WHERE id = (SELECT TheWalls.id FROM TheWalls WHERE TheWalls.Name = ?);");
            s.setString(1, gp.get().getName());
            s.executeUpdate("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SQLStatsContainer loadStats(String name) throws SQLException, ClassNotFoundException {
        if (!this.my.checkConnection()) {
            this.c = this.my.openConnection();
        }
        PreparedStatement s = this.c.prepareStatement("SELECT * FROM `" + TABLE_NAME + "` Where `Name`= ?");
        s.setString(1, name);
        ResultSet result = s.executeQuery();
        SQLStatsContainer current = new SQLStatsContainer();

        if (result.next()) {
            try {
                current.death = result.getInt("Deaths");
                current.wins = result.getInt("Wins");
                current.kills = result.getInt("Kills");
                current.games = result.getInt("Games");
                current.money = result.getInt("Coins");
            } catch (Exception e) {
                e.printStackTrace();
            }
            current.New = false;
        }

        s.close();
        result.close();

        return current;
    }

    public boolean hasData(String name) throws SQLException, ClassNotFoundException {
        if (!this.my.checkConnection()) {
            this.c = this.my.openConnection();
        }
        PreparedStatement s = this.c.prepareStatement("SELECT id FROM `" + TABLE_NAME + "` Where `Name`=?;");
        s.setString(1, name);
        ResultSet result = s.executeQuery();

        if (!result.next()) {
            s.close();
            result.close();
            return false;
        }

        s.close();
        result.close();

        return true;
    }

    public void savePlayerData(GameStats data) {
        String name = data.getName();
        try {
            if (data.isNew() || !hasData(name)) {
                PreparedStatement s = this.c.prepareStatement("INSERT INTO `" + TABLE_NAME + "` (`Name`,`Games`,`Wins`,`Kills`,`Deaths`,`Coins`) " + "VALUES (?,'" + data.getGames() + "','" + data.getWins() + "','" + data.getKills() + "','" + data.getDeath() + "','" + data.getMoney() + "');");
                s.setString(1, name);
                s.executeUpdate();
            } else {
                PreparedStatement s = this.c.prepareStatement("UPDATE `" + TABLE_NAME + "` " + "SET `Games`='" + data.getGames() + "'," + "`Wins`='" + data.getWins() + "'," + "`Kills`='" + data.getKills() + "'," + "`Deaths`='" + data.getDeath() + "'," + "`Coins`='" + data.getMoney() + "' " + "Where `Name`=?;");
                s.setString(1, name);
                s.executeUpdate();
					

					/*
					s.executeUpdate("UPDATE `"+ TABLENAME +"` (`Games`,`Win`,`G1`,`G2`,`G3`,`G4`,`G5`,`G6`,`R1`,`R2`,`R3`,`R4`,`R5`,`R6`)"
							+ " VALUES ('"+ data.getGames() +"','"+data.getGiveValue(1) +"','" + data.getGiveValue(2) + "','" + data.getGiveValue(3) + "','" + data.getGiveValue(4) 
							+ "','" + data.getGiveValue(5) + "','" + data.getGiveValue(6) +"','" + data.getReceivedValue(1) + "','" +data.getReceivedValue(2) + "','" + data.getReceivedValue(3) 
							+ "','" + data.getReceivedValue(4) + "','" + data.getReceivedValue(5) + "','" + data.getReceivedValue(6) + "') Where `Name`='"+ data.getName()+ "';");
					//s.executeUpdate("UPDATE `expControl` SET `Amount`='"+amount +"' Where `Name`='"+name+"';");
					  */

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public float getUser(String name) throws ClassNotFoundException, SQLException {
        if (!this.my.checkConnection()) {
            this.c = this.my.openConnection();
        }
        PreparedStatement s = this.c.prepareStatement("SELECT * FROM `expControl` Where `Name`=?;");
        s.setString(1, name);
        ResultSet result = s.executeQuery();
        if (result.next()) {
            float i = result.getFloat("amount");
            s.close();
            result.close();
            return i;

        } else {
            return -1;
        }

    }


    public void deletePlayer(String name) throws ClassNotFoundException, SQLException {
        if (!this.my.checkConnection()) {
            this.c = this.my.openConnection();
        }
        PreparedStatement s = this.c.prepareStatement("DELETE FROM `expControl` Where `Name`=?;");
        s.setString(1, name);
        s.executeUpdate();
        s.close();
    }

    public void updatePlayer(String name, float amount) throws ClassNotFoundException, SQLException {
        if (!this.my.checkConnection()) {
            this.c = this.my.openConnection();
        }
        float i = getUser(name);

        if (i == -1) {
            PreparedStatement s = this.c.prepareStatement("INSERT INTO `expControl` (`Name`,`Amount`) VALUES (?, ?);");
            s.setString(1, name);
            s.setFloat(2, amount);
            s.executeUpdate();
            s.close();
        } else {
            if (i != Bukkit.getPlayer(name).getExp()) {
                PreparedStatement s = this.c.prepareStatement("UPDATE `expControl` SET `Amount`= ? Where `Name`= ?;");
                s.setFloat(1, amount);
                s.setString(2, name);
                s.executeUpdate();
                s.close();
            }

        }
    }

    public int getWhackScore(String name) throws ClassNotFoundException, SQLException {
        if (!this.my.checkConnection()) {
            this.c = this.my.openConnection();
        }
        PreparedStatement s = this.c.prepareStatement("SELECT `score` FROM `WackAmole` Where `name`=?;");
        s.setString(1, name);
        ResultSet result = s.executeQuery();
        if (!result.next()) {
            return -1;
        }
        int i = result.getInt("score");
        s.close();
        result.close();
        return i;
    }
}
