<%-- 
    Document   : Resources
    Created on : Mar 26, 2017, 9:23:37 PM
    Author     : Tim Lansing
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    String userName = (String) session.getAttribute("userName");
    if (userName == null) {
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }
%>

<t:template>
    <jsp:body>
        <p>Body of the resources page.</p>
        <a href="resources/ResourceDetails.jsp">Details</a>
    </jsp:body>
</t:template>