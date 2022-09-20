package com.test.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.test.model.Player;

public class CreateFinalList {
	
public static ArrayList<Player> createTeam(List<Player> arr, int numBowlers, int wicketKeeper) {
		
		ArrayList<Player> final_list = new ArrayList<Player>(11);
		ArrayList<Player> bowlers = new ArrayList<Player>(numBowlers);
		ArrayList<Player> wicketKeepers = new ArrayList<Player>(wicketKeeper);
		ArrayList<Player> others = new ArrayList<Player>(20-numBowlers-wicketKeeper);
		
		for(Player p : arr) {
			if(p.getPlayerType().equalsIgnoreCase("BOWLER")) {
				bowlers.add(p);
//				System.out.println("BOWLER ADDED : "+p.getName());
			}
			else if(p.getPlayerType().equalsIgnoreCase("WICKETKEEPER")) {
				wicketKeepers.add(p);
//				System.out.println("WICKETKEEPER ADDED : "+p.getName());
			}
			else {
				others.add(p);
//				System.out.println("OTHERS ADDED : "+p.getName());
			}
		}
		//Sorting according to avg score
		
		Collections.sort(bowlers);
		Collections.sort(wicketKeepers);
		Collections.sort(others);
		

		
		int teamCount = 0;
		int addedBowlers = 0;
		
		for(Player p : bowlers) {
			if(!final_list.contains(p) && addedBowlers <= numBowlers-1) {
				final_list.add(p);
				addedBowlers++;
				teamCount++;
//				System.out.println("Added player in bowlers"+p.getName());
			}
		}
		
		if(wicketKeeper == 1 && wicketKeepers.size()>0) {
			final_list.add(wicketKeepers.get(0));
			teamCount++;
//			System.out.println("Added player in wicket keepers"+wicketKeepers.get(0).getName());
		}
		
		int remaining_teamCount = 11-teamCount;
//		System.out.println("TEAM COUNT : "+teamCount+" REMAINING COUNT : "+remaining_teamCount);
//		System.out.println("OTHERS SIZE : "+others.size());
		for(int i = 0; i < remaining_teamCount; i++) {
			if(!final_list.contains(others.get(i)) && !others.get(i).getPlayerType().equals("BOWLER")) {
				final_list.add(others.get(i));
				teamCount++;
//				System.out.println("Added player in others"+others.get(i).getName());
			}
		}
		
		//Find the top 'numBowlers' from the list to add to the main list
		Collections.sort(final_list);
		
		return final_list;
	}
}
