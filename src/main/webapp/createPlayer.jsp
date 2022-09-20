<%@page import="com.test.dao.PlayerDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Player</title>
</head>
<body>
	<jsp:useBean id="player"
  	class="com.test.model.Player" />

	<jsp:setProperty property="*" name="player" />
	
	<%
	  PlayerDao playerDao = new PlayerDao();
	  int status = playerDao.registerPlayer(player);
	  if (status > 0) {
	   out.print("You are successfully registered");
	  }
	  else {
		  out.println("Registration Failed!");
	  }
	%>
</body>
</html>