<%-- 
    Document   : Reports
    Created on : Mar 26, 2017, 9:24:19 PM
    Author     : Tim Lansing
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@page import="Beans.User"%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }
    boolean hasRole = false;
    for(String role:user.getRoles()){
        if(role.equals("reports")){
            hasRole = true;
        }
    }
    if(!hasRole){
        request.getRequestDispatcher("/error/401Unauthorized.jsp").forward(request, response);
    }
%>

<t:template>
    <jsp:body>
          <center><h1>Report Page</h1></center>
        <div id= "reportPage">            
            <label for="selection">Please select one: </label>
            <select id="selection">
                <option value ="2015">2014</option>
                <option value ="2015">2015</option>
                <option value ="2016">2016</option>
            </select>
            <input type ="Submit" value="Run">             
        </div> 
    </jsp:body>
</t:template>