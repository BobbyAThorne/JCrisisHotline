<%--
    Document   : ChangePassword
    Created on : Apr 4, 2017, 12:37:18 AM
    Author     : Aaron Usher
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@page import="Beans.User"%>
<jsp:useBean id="bean" class="Beans.ChangePasswordBean" scope="request"/>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }
%>


<t:template>
    <jsp:body>
        <form class="table" id="changePasswordForm" method="GET" action="PasswordHandler">
            <div class="table-row">
                <label class="table-cell" for="oldPassword">Old Password:</label>
                <input class="table-cell required" type="password" name="oldPassword" id="oldPassword" /><br />
            </div>
            <div class="table-row">
                <label class="table-cell" for="newPassword">New Password:</label>
                <input class="table-cell required" type="password" name="newPassword" id="newPassword" /><br />
            </div>
            <div class="table-row">
                <label class="table-cell" for="confirmPassword">Confirm Password:</label>
                <input class="table-cell required" equalTo="#newPassword" type="password" name="confirmPassword" id="confirmPassword" /><br />
            </div>
            <div class="table-row">
                <input class="table-cell" type="submit" value="Update"/>
            </div>
        </form>
        ${bean.message}
    </jsp:body>
</t:template>