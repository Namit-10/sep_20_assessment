package com.test.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.util.Util;
import com.test.model.Player;

public class PlayerDao {
	static ArrayList<Player> arrl = new ArrayList<Player>();
	
    public static int registerPlayer(Player player) throws ClassNotFoundException, SQLException {
    
        String sql = "insert into Player(id, name, matchesPlayed, totalRunsScored, totalWicketsTaken, outOnZero, playerType) values("+player.getId()+",'"+player.getName()+"',"+player.getMatchesPlayed()+","+player.getTotalRunsScored()+","+player.getTotalWicketsTaken()+","+player.getOutOnZero()+",'"+player.getPlayerType()+"' )";

        Statement stmt = Util.getConnection().createStatement();
        
        arrl.add(player);
        
		return stmt.executeUpdate(sql);

    }
    
    public static int checkPlayerExists (String name) throws ClassNotFoundException, SQLException {
        
        String check_name = "select * from Player where name = '"+name+"' ";

        Statement stmt = Util.getConnection().createStatement();
        
		ResultSet rs = stmt.executeQuery(check_name);
		
		if(rs.next()) {
			return 1;
		}
		else {
			return 0;
		}

    }
    
    public static ArrayList<Player> getPlayerList() throws SQLException {
    	String list_query = "select * from Player";
    	Statement stmt = Util.getConnection().createStatement();
    	
    	ArrayList<Player> full_list = new ArrayList<Player>();
    	ResultSet rs = stmt.executeQuery(list_query);
    	
    	int id, matchesPlayed, totalRunsScored, totalWicketsTaken, outOnZero;
    	String name, playerType;
    	
    	while(rs.next()) {
    		id = rs.getInt(1);
    		name = rs.getString(2);
    		matchesPlayed = rs.getInt(3);
    		totalRunsScored = rs.getInt(4);
    		totalWicketsTaken = rs.getInt(5);
    		outOnZero = rs.getInt(6);
    		playerType = rs.getString(7);
    		
    		full_list.add(new  Player(id, name, matchesPlayed, totalRunsScored, totalWicketsTaken, outOnZero, playerType));
    		
    	}
    	return full_list;
    }
    
    
}