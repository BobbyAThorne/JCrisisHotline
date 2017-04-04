<%-- 
    Document   : Login
    Created on : Apr 4, 2017, 12:03:57 PM
    Author     : Eric Walton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <jsp:body>
        <div id="loginDiv">
            <form id="loginForm" action="" method="GET"><!--Login handler not done yet.-->
                <br/>
                <input type="text" name="username" placeholder="Username" /> <br/><br/>
                <input type="password" name="password" placeholder="Password"  /> <br/><br/>
                <input type="submit" value="Login"/> <br/><br/>
            </form>
        </div>
    </jsp:body>
</t:template>
