<%-- 
    Document   : Resources Category
    Created on : Mar 26, 2017, 9:23:37 PM
    Author     : Jessica Hoppe
--%>
<a href="../src/java/Accessors/ResourceAccessor.java"></a>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@page import="Beans.User"%>
<jsp:useBean id="pageBean" class="Beans.ResourcePagBean" scope="request" />
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
        <h3>Resources</h3>
        <table class="table">
            <thread>
                <td>ID</td>
                <td>Name</td>
                <td>Description</td>
                <td>Actions</td>
            </thread>
            <c:forEach var="resource" items="${pageBean.resourceList}">
                <tr>
                    <td>${resource.resourceId}</td>
                    <td>${resource.name}</td>
                    <td>${resource.description}</td>
                    <td><a href ="http://localhost:8080/JCrisis/ResourceHandler?action=details&resourceId=${resource.resourceId}">Edit</td>
                </tr>
            </c:forEach>                
        </table>
        <%--<a href="resources/ResourceDetails.jsp">Create Resource</a>--%>
        <a href="http://localhost:8080/JCrisis/ResourceHandler?action=blank">Create Resource</a>
    </jsp:body>
</t:template>