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
            <form action="ReportsHandler" method="POST">
            <label for="selection">Please select one: </label>
            <select id="selection" name="selection">
                <option value ="caller">Calls Report</option>
                <option value ="resource">Resource Report</option>
               
            </select>
            <input type ="Submit" value="Run">        
            </form>
        </div> 
    </jsp:body>
</t:template>