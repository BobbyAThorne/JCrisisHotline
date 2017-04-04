<%-- 
    Document   : Login
    Created on : Apr 4, 2017, 12:03:57 PM
    Author     : Eric Walton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <div id="loginDiv">
            <form id="loginForm" action="" method="GET"><!--Login handler not done yet.-->
                <br/>
                <input type="text" name="username" placeholder="Username" /> <br/><br/>
                <input type="password" name="password" placeholder="Password"  /> <br/><br/>
                <input type="submit" value="Login"/> <br/><br/>
            </form>
        </div>
    </body>
</html>
