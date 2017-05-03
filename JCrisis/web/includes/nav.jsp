<%@page import="Beans.User"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="navContainer">
    <div id="nav">
        <c:if test='${user.roles.contains("counselor")}'>
            <a href="http://localhost:8080/JCrisis/RequestHandler?task=CallRecord">Call Record</a>
        </c:if>

        <c:if test='${user.roles.contains("counselor") || user.roles.contains("dataEntry")}'>
            <a href="http://localhost:8080/JCrisis/ResourceHandler?action=list">Resources</a>
        </c:if>

        <c:if test='${user.roles.contains("dataEntry") || user.roles.contains("manager")}'>
            <a href="http://localhost:8080/JCrisis/Users">Users</a>
        </c:if>

        <c:if test='${user.roles.contains("reports")}'>
            <a href="http://localhost:8080/JCrisis/RequestHandler?task=Reports">Reports</a>
        </c:if>
    </div>
    <div class="dropdown" id="userLinks">
        <button class="dropbtn">${user.userName}</button>
        <div class="dropdown-content">
            <a href="http://localhost:8080/JCrisis/LogoutHandler">Logout</a>
            <a href="http://localhost:8080/JCrisis/ChangePassword.jsp">Change Password</a>
        </div>
    </div>
</div>