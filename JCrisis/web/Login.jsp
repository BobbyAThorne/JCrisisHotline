<%-- 
    Document   : Login
    Created on : Apr 4, 2017, 12:03:57 PM
    Author     : Eric Walton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String currentPageMessage = (String) session.getAttribute("currentPageMessage");
    if (null == currentPageMessage) {
        currentPageMessage = "Please enter your user name and password and submit.";
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>JCrisis Hotline</title>
        <link rel="stylesheet" href="${folderDots}css/standard_style.css" />
        <link rel="stylesheet" href="${folderDots}css/normalize.css" />
        <script src="${folderDots}javascript/jquery-3.2.0.min.js"></script>
        <script src="${folderDots}javascript/jquery.validate.min.js"></script>
        <script src="${folderDots}javascript/standard_script.js"></script>
    </head>
    <body>
        <div><jsp:include page="${folderDots}/includes/pagetop.html" /></div>
        <center><%= currentPageMessage%></center>
        <div id="loginDiv">
            <form id="loginForm" action="LoginHandler" method="POST"><!--Login handler not done yet.-->
                <br/>
                <input type="text" name="username" placeholder="Username" /> <br/><br/>
                <input type="password" name="password" placeholder="Password"  /> <br/><br/>
                <input type="submit" value="Login"/> <br/><br/>
            </form>
        </div>
		
        <center>
            <table border="1">
                <tr><td><center>User Name</center></td><td><center>Password</center></td><td><center>Role</center></td></tr>
                <tr><td>jSmith</td><td>password</td><td>counselor</td></tr>
                <tr><td>bJones</td><td>password</td><td>counselor, reports, manager</td></tr>
                <tr><td>kPerry</td><td>password</td><td>reports</td></tr>
                <tr><td>sWalker</td><td>password</td><td>dataEntry</td></tr>
            </table>
        </center>
    </body>
</html>
