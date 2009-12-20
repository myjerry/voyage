<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/xhtml1-strict.dtd">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/myjerry.tld" prefix="mj"%>

<%@page import="com.google.appengine.api.users.User" %>
<%@page import="com.google.appengine.api.users.UserService" %>
<%@page import="com.google.appengine.api.users.UserServiceFactory" %>

<html>
	<head>
		<title>Voyage :: <tiles:getAsString name="title" /></title>
		<link rel="stylesheet" type="text/css" media="screen, print, projection"  href="<c:url value='/assets/css/voyage.css' />"></link>
	</head>
	<body>

		<div id="header">
		  <a name="top"></a>
		  <h1 class="logo"><img src="" alt="voyage" /></h1>
		  <h1 id="site-title">voyage</h1>
		  <div id="utility-nav">
		  <ul>
		    <li><a href="" class="last">Welcome, <mj:gae.user /></a></li>
		  </ul>
		  
		  <ul class="lower-group">
		  	<li><a href="/index.html">Home</a></li>
		  	<li><a href="/admin.html">Admin</a></li>
		  	<li><a href="/_ah/admin" target="_blank" >Local Datastore</a></li>


			<%
				UserService userService = UserServiceFactory.getUserService();
				User user = userService.getCurrentUser();
				String thisURL = request.getRequestURL().toString();
				if(user == null) {
			%>
				<li><a href="<%=userService.createLoginURL(thisURL) %>" class="last">Sign In</a></li>
			<% } else { %>
				<li><a href="<%=userService.createLogoutURL(thisURL) %>" class="last">Sign Out</a></li>
			<% } %>


		  </ul>

		  </div>
		</div>
		
	    <div id="content"> 
	      <div id="content-column" style="width:968px;"> 
	        <div class="module primary"> 		
				<tiles:insertAttribute name="body" />
			</div>
		  </div>
		</div>
		
		<div id='footer'>
			<p>
			powered by voyage (build number 0.6.2009.12.6)
			<br />
			Copyright &copy; 2009 myjerry
			</p>
		</div>
		
	</body>
</html>
