package com.test.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.PlayerDao;
import com.test.util.Util;

@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			PrintWriter out = response.getWriter();
			int status = PlayerDao.checkPlayerExists(name);
			
			if(status > 0) {
				Statement stmt = Util.getConnection().createStatement();
				
				String show_details = "select * from Player where name = '"+name+"' ";
				
				ResultSet rs = stmt.executeQuery(show_details);
				
				
				
				while(rs.next()) {
					out.println("<html>"
							+ "<body>"
							+ "<center>");
					out.println("<h3>The information about "+name+" available in the database is : </h3><br><br>");
					out.println("<form action='Update' method='post'>");
					out.println("ID : <input type='number' name='id' value="+rs.getInt(1)+" readonly><br>");
					out.println("Name : <input type='text' name='name' value="+rs.getString(2)+"><br>");
					out.println("Matches Played : <input type='number' name='matchesPlayed' value="+rs.getInt(3)+"><br>");
					out.println("Total Runs Scored : <input type='number' name='totalRunsScored' value="+rs.getInt(4)+"><br>");
					out.println("Total Wickets Taken : <input type='number' name='totalWicketsTaken' value="+rs.getInt(5)+"><br>");
					out.println("Out on Zero : <input type='number' name='outOnZero' value="+rs.getInt(6)+"><br>");
					out.println("Player Type : <input type='text' name='playerType' value="+rs.getString(7)+" readonly><br>");
					out.println("<input type='submit' value='Submit'>");
					out.println("</center>");
					out.println("</body>");
					out.println("</html>");
					
					
					
				}
			}
			else {
				out.println("<h1 style='background-color: red; color: white;'>UPDATE REJECTED!</h1>"
						+ "<br><br>The given name "+name+" does not match with any player names.<br>Please give a valid player name.");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt((String)request.getParameter("id"));
		String name = (String)request.getParameter("name");
		int matchesPlayed = Integer.parseInt((String)request.getParameter("matchesPlayed"));
		int totalRunsScored = Integer.parseInt((String)request.getParameter("totalRunsScored"));
		int totalWicketsTaken = Integer.parseInt((String)request.getParameter("totalWicketsTaken"));
		int outOnZero = Integer.parseInt((String)request.getParameter("outOnZero"));
		String playerType = (String)request.getParameter("playerType");
		
		
		PrintWriter out = response.getWriter();
		try {
			Statement stmt = Util.getConnection().createStatement();
			String update_query = "update Player set name='"+name+"', matchesPlayed = "+matchesPlayed+", totalRunsScored = "+totalRunsScored+", totalWicketsTaken = "+totalWicketsTaken+", outOnZero = "+outOnZero+" where id = "+id+" ";
			int status = stmt.executeUpdate(update_query);
			
			if(status > 0) {
				out.println("UPDATE SUCCESSFUL!");
			}
			else {
				out.println("UPDATE UNSUCCESSFUL!");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
