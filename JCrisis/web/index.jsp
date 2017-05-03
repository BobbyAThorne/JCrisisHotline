<%--
    Document   : index
    Created on : Mar 26, 2017, 8:17:05 PM
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
%>

<t:template>
    <jsp:body>
        <p>Welcome to JCrisis Hotline, ${user.firstName} ${user.lastName}! Please select an option.</p>
    </jsp:body>
</t:template>