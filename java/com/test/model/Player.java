package com.test.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player implements Comparable<Player>{
	private int id;
	private String name;
	private int matchesPlayed;
	private int totalRunsScored;
	private int totalWicketsTaken;
	private int outOnZero;
	private String playerType;
	
	/*
	 * create table Player(id int , name varchar(40) not null, matchesPlayed int, totalRunsScored int, totalWicketsTaken int, outOnZero int, playerType varchar(20));
	 */
	
	public Player() {
		super();
	}
	
	public Player(int id, String name, int matchesPlayed, int totalRunsScored, int totalWicketsTaken, int outOnZero,
			String playerType) {
		super();
		this.id = id;
		this.name = name;
		this.matchesPlayed = matchesPlayed;
		this.totalRunsScored = totalRunsScored;
		this.totalWicketsTaken = totalWicketsTaken;
		this.outOnZero = outOnZero;
		this.playerType = playerType;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMatchesPlayed() {
		return matchesPlayed;
	}
	public void setMatchesPlayed(int matchesPlayed) {
		this.matchesPlayed = matchesPlayed;
	}
	public int getTotalRunsScored() {
		return totalRunsScored;
	}
	public void setTotalRunsScored(int totalRunsScored) {
		this.totalRunsScored = totalRunsScored;
	}
	public int getTotalWicketsTaken() {
		return totalWicketsTaken;
	}
	public void setTotalWicketsTaken(int totalWicketsTaken) {
		this.totalWicketsTaken = totalWicketsTaken;
	}
	public int getOutOnZero() {
		return outOnZero;
	}
	public void setOutOnZero(int outOnZero) {
		this.outOnZero = outOnZero;
	}
	public String getPlayerType() {
		return playerType;
	}
	public void setPlayerType(String playerType) {
		this.playerType = playerType;
	}
	
	public float avg_score() {
		float avg_score = this.getTotalRunsScored()/this.getMatchesPlayed();
		return avg_score;
	}

	@Override
	public int compareTo(Player o) {
		return Float.compare(o.avg_score(), this.avg_score());
	}
	
	

}
