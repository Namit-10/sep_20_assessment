package com.test.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.util.Util;
import com.test.dao.PlayerDao;
import com.test.model.Player;


@WebServlet("/CreatePlayer")
public class CreatePlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		int id = Integer.parseInt((request.getParameter("id")));
		
		String name = request.getParameter("name");
		
		int matchesPlayed = Integer.parseInt(request.getParameter("matchesPlayed"));
		
		int totalRunsScored = Integer.parseInt(request.getParameter("totalRunsScored"));
		
		int totalWicketsTaken = Integer.parseInt(request.getParameter("totalWicketsTaken"));
		
		int outOnZero = Integer.parseInt(request.getParameter("outOnZero"));
		
		String playerType = request.getParameter("playerType");
		
		if(matchesPlayed >= 0 ) {
			if(totalRunsScored >= 0 && totalRunsScored < 100000) {
				if(totalWicketsTaken >= 0 && totalWicketsTaken <= matchesPlayed*10) {
					if(outOnZero >= 0 && outOnZero <= matchesPlayed) {
						if(playerType.equalsIgnoreCase("batsman") || playerType.equalsIgnoreCase("bowler") || playerType.equalsIgnoreCase("allrounder") || playerType.equalsIgnoreCase("wicketkeeper")) {
							Player p = new Player(id, name, matchesPlayed, totalRunsScored, totalWicketsTaken, outOnZero, playerType);
							
							Statement stmt = null;
							
							try {
								stmt = Util.getConnection().createStatement();
								PlayerDao.registerPlayer(p);
								
								out.println("ACCOUNT CREATION SUCCESSFUL!");
							}
							catch(Exception e) {
								e.printStackTrace();
							}
							
							
						}
						else {
							//playerType is wrong
							out.println("ACCOUNT CREATION FAILURE! WRONG PLAYER TYPE!");
						}
					}
					else {
						//outOnZero not in range
						out.println("ACCOUNT CREATION FAILURE! OUT ON ZERO IS NOT IN ACCEPTABLE RANGE!");
					}
				}
				else {
					//totalWicketsTaken not in range
					out.println("ACCOUNT CREATION FAILURE! TOTAL WICKETS TAKEN IS NOT IN ACCEPTABLE RANGE!");
				}
			}
			else {
				//runs scored not in range
				out.println("ACCOUNT CREATION FAILURE! TOTAL RUNS SCORED IS NOT IN ACCEPTABLE RANGE!");
			}
		}
		else {
			//matches played less than 0
			out.println("ACCOUNT CREATION FAILURE! TOTAL MATCHES PLAYED CANNOT BE LESS THAN 0!");
		}
	}

}
