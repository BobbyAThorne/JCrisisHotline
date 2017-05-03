<%-- 
    Document   : CreateUser
    Created on : Apr 4, 2017, 12:31:08 PM
    Author     : Alissa Duffy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page import="Beans.User"%>
<jsp:useBean id="userBean" class="Beans.UserPageBean" scope="session" />
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        request.getRequestDispatcher("../Login.jsp").forward(request, response);
    }
    boolean hasRole = false;
    for(String role:user.getRoles()){
        if(role.equals("dataEntry")){
            hasRole = true;
        }
    }
    if(!hasRole){
        request.getRequestDispatcher("../error/401Unauthorized.jsp").forward(request, response);
    }
%>

<t:template folderDots="">

    <jsp:body>

        <h3 class="centered">Create User</h3>
        <form class="table" id="createUserForm" method="GET" action="CreateUser">
            <input type="hidden" name="action" value="create" />
            <div class="table-row">
                <label class="table-cell right" for="firstName">First Name: </label>
                <input class="table-cell required" type="text" name="firstName" id="firstName" />
                <label class="table-cell right" for="addressOne">Address One: </label>
                <input class="table-cell required" type="text" name="addressOne" id="addressOne" />   
            </div><br />
            <div class="table-row">
                <label class="table-cell right" for="lastName">Last Name: </label>
                <input class="table-cell required" type="text" name="lastName" id="lastName" />
                <label class="table-cell right" for="addressTwo">Address Two: </label>
                <input class="table-cell required" type="text" name="addressTwo" id="addressTwo" />  
            </div> <br />
            <div class="table-row">
                <label class="table-cell right" for="createUsername">Username: </label>
                <input class="table-cell required" type="text" name="UserName" id="UserName" />
                <label class="table-cell right" for="city">City: </label>
                <input class="table-cell required" type="text" name="city" id="city" />  
            </div> <br />
            <div class="table-row">
                <label class="table-cell right" for="phone">Phone Number: </label>
                <input class="table-cell required" type="text" name="phone" id="phone" />
                    <label class="table-cell right" for="territory">Territory: </label>
                <input class="table-cell required" type="text" name="territory" id="territory" />     
            </div><br />        
            <div class="table-row">
                <label class="table-cell right" for="n/a"></label>
                <label class="table-cell right" for="n/a2"></label>
                <label class="table-cell right" for="zip">Zip: </label>
                <input class="table-cell required" type="text" name="zip" id="zip" />
            </div> <br />
            <input class="centered" name="submit" type="submit" value="Update" />
            &nbsp;&nbsp;<input type="button" onclick="location.href = 'Users';" value="Cancel" />

        </form>
        ${userBean.errorMessage}

    </jsp:body>

</t:template>