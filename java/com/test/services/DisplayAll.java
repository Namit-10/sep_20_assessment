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

import com.test.util.Util;


@WebServlet("/DisplayAll")
public class DisplayAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DisplayAll() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		try {
			Statement stmt = Util.getConnection().createStatement();
			String dispAll = "select * from Player ";
			
			ResultSet rs = stmt.executeQuery(dispAll);
			out.println("<html><head><link rel='stylesheet' href='style.css'></head><body style=' min-height:100%;"
					+ "  background:linear-gradient(90deg, rgba(0, 0, 190, 0.4), rgba(0, 0, 0, 0.4)), url(img_5.jpg);"
					+ "  background-size:cover;'><center>");
			out.println("<table class='styled-table'><thead><tr><th>ID</th><th>Name</th><th>Matches Played</th><th>Total Runs Scored</th><th>Total Wickets Taken</th><th>Out on Zero</th><th>Player Type</th></thead></tr>");
			out.println("<tbody>");
			while(rs.next()) {
				out.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getInt(3)+"</td><td>"+rs.getInt(4)+"</td><td>"+rs.getInt(5)+"</td><td>"+rs.getInt(6)+"</td><td>"+rs.getString(7)+"</td></tr>");
			}
			out.println("</tbody></center></body></html>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
