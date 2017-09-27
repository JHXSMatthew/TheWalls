package com.github.JHXSMatthew.Controllers;

import com.github.JHXSMatthew.Game.GamePlayer;
import com.github.JHXSMatthew.Game.GameStats;
import com.github.JHXSMatthew.Kits.KitType;
import com.github.JHXSMatthew.Objects.SQLStatsContainer;
import com.huskehhh.mysql.mysql.MySQL;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class MySQLController {

	private Connection c =null;
	private MySQL my;
	private static String TABLENAME  = "TheWalls";
	
	public MySQLController(){
	    this.my = new MySQL("192.168.123.2", "3306", "games", "game", "NO_PUBLIC_SECTION");
	}
	
	public void openConnection(){
	    try {
			c = my.openConnection();
		} catch (ClassNotFoundException e) {
			System.out.print("Connection error !");
			e.printStackTrace();
		} catch (SQLException e1) {
			System.out.print("Connection error !");
			e1.printStackTrace();
		}
	}
	
	
	public void clsoeConnection() throws SQLException{
		this.c.close();
	}
	
	
	
	public void closeDB() throws SQLException{
		this.my.closeConnection();
	}

	public HashMap<KitType,Integer> getKitMap(GamePlayer gp){
		HashMap<KitType,Integer> map = null;
		try {
			if (!this.my.checkConnection()) {
				this.c = this.my.openConnection();
			}
			Statement s = this.c.createStatement();
			ResultSet result = s.executeQuery("SELECT * FROM `TheWallsKits` WHERE id= (SELECT TheWalls.id FROM TheWalls WHERE TheWalls.Name = '"+gp.get().getName()+"');");
			if(result.next()){

				map = new HashMap<KitType,Integer>();
				for(KitType type : KitType.values())
					map.put(type,result.getInt(type.getDBName()));
				try{
					String current = result.getString("current");
					if(current != null){
						gp.setKit(KitType.getType(current).getKit(gp.get(),map.get(KitType.getType(current))));
					}
				}catch(Exception e){

				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}

	public void setCurrentKit(GamePlayer gp){
		try {
			if (!this.my.checkConnection()) {
				this.c = this.my.openConnection();
			}
			Statement s = this.c.createStatement();
			s.executeUpdate("UPDATE `TheWallsKits` SET current='"
					+gp.getKit().getType().getDBName()+
					"' WHERE id= (SELECT TheWalls.id FROM TheWalls WHERE TheWalls.Name = '"
					+gp.get().getName()+
					"');");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public SQLStatsContainer loadStats(String name) throws SQLException, ClassNotFoundException{
		if(!this.my.checkConnection()){
			this.c = this.my.openConnection();
		}
		Statement s = this.c.createStatement();
		ResultSet result = s.executeQuery("SELECT * FROM `" + TABLENAME + "` Where `Name`='"+name+"';");
		SQLStatsContainer current = new SQLStatsContainer();
		
		if(result.next()){
			try{
			current.death = result.getInt("Deaths");
			current.wins = result.getInt("Wins");
			current.kills = result.getInt("Kills");
			current.games = result.getInt("Games");
			current.money = result.getInt("Coins");
			}catch(Exception e){
				e.printStackTrace();
			}
			current.New = false;
		}
		
		s.close();
		result.close();
		s=null;
		
		return current;
		
	}

	public boolean hasData(String name) throws SQLException, ClassNotFoundException {
		if(!this.my.checkConnection()){
			this.c = this.my.openConnection();
		}
		Statement s = this.c.createStatement();
		ResultSet result = s.executeQuery("SELECT id FROM `" + TABLENAME + "` Where `Name`='"+name+"';");
		SQLStatsContainer current = new SQLStatsContainer();

		if(!result.next()){
			s.close();
			result.close();
			s=null;
			return false;
		}

		s.close();
		result.close();
		s=null;

		return true;
	}

	public boolean savePlayerData(GameStats data){
		String name = data.getName();
		try (Statement s = this.c.createStatement()){
			if(data.isNew() || !hasData(name)){
				s.executeUpdate("INSERT INTO `"+ TABLENAME +"` (`Name`,`Games`,`Wins`,`Kills`,`Deaths`,`Coins`) VALUES ('"+data.getName()+"','"+ data.getGames() + "','"+ data.getWins() + "','" + data.getKills() + "','" + data.getDeath() + "','" + data.getMoney() + "');" );
			}else{
					s.executeUpdate("UPDATE `"+ TABLENAME +"` SET `Games`='"+data.getGames() +"',`Wins`='"+data.getWins()
							+"',`Kills`='"+ data.getKills() 
							+"',`Deaths`='"+ data.getDeath()
							+"',`Coins`='"+ data.getMoney()
						    + "' Where `Name`='"+name+"';");
					

					/*
					s.executeUpdate("UPDATE `"+ TABLENAME +"` (`Games`,`Win`,`G1`,`G2`,`G3`,`G4`,`G5`,`G6`,`R1`,`R2`,`R3`,`R4`,`R5`,`R6`)"
							+ " VALUES ('"+ data.getGames() +"','"+data.getGiveValue(1) +"','" + data.getGiveValue(2) + "','" + data.getGiveValue(3) + "','" + data.getGiveValue(4) 
							+ "','" + data.getGiveValue(5) + "','" + data.getGiveValue(6) +"','" + data.getReceivedValue(1) + "','" +data.getReceivedValue(2) + "','" + data.getReceivedValue(3) 
							+ "','" + data.getReceivedValue(4) + "','" + data.getReceivedValue(5) + "','" + data.getReceivedValue(6) + "') Where `Name`='"+ data.getName()+ "';");
					//s.executeUpdate("UPDATE `expControl` SET `Amount`='"+amount +"' Where `Name`='"+name+"';");
					  */
					 
				}
			
			
			s.close();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public float getUser(String name) throws ClassNotFoundException, SQLException{
		if(!this.my.checkConnection()){
			this.c = this.my.openConnection();
		}
		Statement s = this.c.createStatement();
		ResultSet result = s.executeQuery("SELECT * FROM `expControl` Where `Name`='"+name+"';");
		if(result.next()){
			float i = result.getFloat("amount");
			s.close();
			result.close();
			return i;
			
		}else{
			return -1;
		}
	
	}
	
	

	
	public void deletePlayer(String name) throws ClassNotFoundException, SQLException{
		if(!this.my.checkConnection()){
			this.c = this.my.openConnection();
		}
		Statement s = this.c.createStatement();
		s.executeUpdate("DELETE FROM `expControl` Where `Name`='"+name+"';");
		s.close();
		s=null;
	}
	
	public void updatePlayer(String name, float amount) throws ClassNotFoundException, SQLException{
		if(!this.my.checkConnection()){
			this.c = this.my.openConnection();
		}
		Statement s = this.c.createStatement();
		float i = getUser(name);
		
		if(i == -1){
			s.executeUpdate("INSERT INTO `expControl` (`Name`,`Amount`) VALUES ('"+name+"','"+ amount + "');");
		}else{
			if(i != Bukkit.getPlayer(name).getExp()){
				s.executeUpdate("UPDATE `expControl` SET `Amount`='"+amount +"' Where `Name`='"+name+"';");
			}
			
		}
		
		s.close();
		s=null;
	}

	public int getWhackScore(String name) throws ClassNotFoundException, SQLException{
		if(!this.my.checkConnection()){
			this.c = this.my.openConnection();
		}
		Statement s = this.c.createStatement();
		ResultSet result = s.executeQuery("SELECT `score` FROM `WackAmole` Where `name`='"+name+"';");
		if(!result.next()){
			return -1;
		}
		int i = result.getInt("score");
		s.close();
		result.close();
		return i;
	}
}
