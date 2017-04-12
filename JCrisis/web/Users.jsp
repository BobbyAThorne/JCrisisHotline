<%-- 
    Document   : Users
    Created on : Mar 26, 2017, 9:23:51 PM
    Author     : Tim Lansing
    Updated    : Alissa Duffy April 4,2017 
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    String userName = (String) session.getAttribute("userName");
    if (userName == null) {
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }
%>
<jsp:useBean id="pageBean" class="Beans.UserPageBean" scope="session" />

<t:template>
    <jsp:body>
        <p>Body of the users page.</p>
        <p>${pageBean.errorMessage}</p>
        <table class="table">
            <thead>
            <td>ID</td>
            <td>Name</td>
            <td>Actions</td>
            </thead>
            <c:forEach var="user" items="${pageBean.userList}">
                <tr>
                    <td>${user.ID}</td>
                    <td>${user.firstName} ${user.lastName}</td>
                    <td></td>
                </tr>
            </c:forEach>
        </table>
        <a href="users/CreateUser.jsp">Create New User</a>
    </jsp:body>
</t:template>