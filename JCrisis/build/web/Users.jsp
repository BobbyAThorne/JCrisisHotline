<%-- 
    Document   : Users
    Created on : Mar 26, 2017, 9:23:51 PM
    Author     : Tim Lansing
    Updated    : Alissa Duffy April 4,2017 
--%>

<%@page import="Beans.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }
    boolean hasRole = false;
    for(String role:user.getRoles()){
        if(role.equals("dataEntry") || role.equals("manager")){
            hasRole = true;
        }
    }
    if(!hasRole){
        request.getRequestDispatcher("/error/401Unauthorized.jsp").forward(request, response);
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
                    <td>
                        <c:choose>
                            <c:when test="${pageBean.manager}"><a href="javascript:updateUser(${user.ID});">Edit</a></c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:choose>
            <c:when test="${pageBean.dataEntry}">
                <a href="CreateUser">Create New User</a>
            </c:when>
            <c:when test="${pageBean.manager}">
                <form>
                    <input type="hidden" name="id" />
                    <label for="username">Username</label><input id="username" type="text" name="username" />
                    <label for="firstName">First Name:</label><input id="firstName" type="text" name="firstName" />
                    <label for="lastName">Last Name:</label><input id="lastName" type="text" name="lastName" />
                    <label for="phone">Phone:</label><input id="phone" type="text" name="phone" />
                    <label for="addressOne">Address One:</label><input id="addressOne" type="text" name="addressOne" />
                    <label for="addressTwo">Address Two:</label><input id="addressTwo" type="text" name="addressTwo" />
                    <label for="city">City:</label><input id="city" type="text" name="city" />
                    <label for="territory">Territory:</label><input id="territory" type="text" name="territory" />
                    <label for="zip">Zip:</label><input id="zip" type="text" name="zip" />
                    <label for="isCounselor">Counselor:</label><input id="isCounselor" type="checkbox" name="isCounselor" />
                    <label for="isManager">Manager:</label><input id="isManager" type="checkbox" name="isManager" />
                    <label for="isDataEntry">Data Entry:</label><input id="isDataEntry" type="checkbox" name="isDataEntry" />
                    <label for="isActive">Active:</label><input id="isActive" type="checkbox" name="isActive" />
                </form>
                <script>
                    var userDict = ${pageBean.userListJSON};
                    function updateUser(userID) {
                        var userData = userDict[userID];
                        $("#username").val("\""+userID+"\"");
                        for(var userProperty in userData) {
                            if($("#"+userProperty).attr("type")=="text"){
                                $("#"+userProperty).val(userData[userProperty]);
                            } else if("true"==userData[userProperty]) {
                                $("#"+userProperty).attr("checked","checked");
                            }
                        }
                    }
                </script>
            </c:when>
        </c:choose>
    </jsp:body>
</t:template>