package com.test.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.PlayerDao;
import com.test.model.Player;
import com.test.util.Util;


class Sortbyname implements Comparator<Player> {
	 
	@Override
	public int compare(Player p1, Player p2) {
		// TODO Auto-generated method stub
		return p1.getName().compareTo(p2.getName());
	}
	
}

@WebServlet("/DisplayFinalTeam")
public class DisplayFinalTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Statement stmt = Util.getConnection().createStatement();
			PrintWriter out = response.getWriter();
			
			String all = "select * from Player";
			ResultSet rs = stmt.executeQuery(all);
			
			int bowlers = Integer.parseInt(request.getParameter("numberBowlers"));
			int wicketKeepers = Integer.parseInt(request.getParameter("numberWicketKeepers"));
			
			ArrayList<Player> player_list = PlayerDao.getPlayerList();
			
			ArrayList<Player> final_list = CreateFinalList.createTeam(player_list, bowlers, wicketKeepers);
			
			Collections.sort(final_list, new Sortbyname());
			
			out.println("<html><head><link rel='stylesheet' href='style.css'></head><body style=' min-height:100%;"
					+ "  background:linear-gradient(90deg, rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.6)), url(img_6.jpg);"
					+ "  background-size:cover;'><center>");
			out.println("<h1 style='color: white'>FINAL LIST</h1><br><br>");
			out.println("<span style='color: white'><small>In alphabetical order</small><span><br><br>");
			out.println("<table class='styled-table'><thead><tr><th>ID</th><th>Name</th><th>Matches Played</th><th>Total Runs Scored</th><th>Total Wickets Taken</th><th>Out on Zero</th><th>Player Type</th></tr></thead>");
			out.println("<tbody>");
			
			if(final_list.size() > 0) {
				for(Player p : final_list) {
					out.println("<tr><td>"+p.getId()+"</td><td>"+p.getName()+"</td><td>"+p.getMatchesPlayed()+"</td><td>"+p.getTotalRunsScored()+"</td><td>"+p.getTotalWicketsTaken()+"</td><td>"+p.getOutOnZero()+"</td><td>"+p.getPlayerType()+"</td></tr>");
					
				}
			}
			out.println("</tbody>");
			out.println("</center></body></html>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



}
