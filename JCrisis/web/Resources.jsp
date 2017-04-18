<%-- 
    Document   : Resources
    Created on : Mar 26, 2017, 9:23:37 PM
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
        if(role.equals("counselor") || role.equals("dataEntry")){
            hasRole = true;
        }
    }
    if(!hasRole){
        request.getRequestDispatcher("/error/401Unauthorized.jsp").forward(request, response);
    }
%>

<t:template>
    <jsp:body>
        <p>Body of the resources page.</p>
        <a href="resources/ResourceDetails.jsp">Create Resource</a>
    </jsp:body>
</t:template>