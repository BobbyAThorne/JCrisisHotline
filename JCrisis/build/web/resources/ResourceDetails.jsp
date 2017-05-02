<%-- 
    Document   : ResourceDetails
    Created on : Apr 4, 2017, 9:57:44 AM
    Author     : Christian Lopez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Beans.Resource" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="resourceBean" class="Beans.Resource" scope="session" />


<%@page import="Beans.User"%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        request.getRequestDispatcher("../Login.jsp").forward(request, response);
    }
    boolean hasRole = false;
    for(String role:user.getRoles()){
        if(role.equals("counselor") || role.equals("dataEntry")){
            hasRole = true;
        }
    }
    if(!hasRole){
        request.getRequestDispatcher("../error/401Unauthorized.jsp").forward(request, response);
    }
%>

<t:template folderDots="../">
    
    <jsp:body>
        
        <c:choose>
            <c:when test="${resourceBean.name == \"\"}">
                    <h3 class="centered">Resource</h3>
            </c:when>
            <c:otherwise>
                <h3 class="centered">${resourceBean.name}</h3>
            </c:otherwise>
        </c:choose>
        
        <!--<h3 class="centered">Resource</h3>-->
        
        <form class="table" id="resourceDetailsForm" method="POST" action="../ResourceHandler">
            <div class="table-row">
                
                
            </div>
            <div class="table-row">
                <input type="hidden" name="resourceId" value="${resourceBean.resourceId}" />
                <c:choose>
                    <c:when test="${resourceBean.resourceId == 0}">
                        <input type="hidden" name="action" value="create" />
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="action" value="update" />
                    </c:otherwise>
                </c:choose>
                
                <label class="table-cell right" for="resourceName">Name: </label>
                <input class="table-cell required" type="text" name="resourceName" id="resourceCategory" value="${resourceBean.name}" maxlength="50"/>
                <label class="table-cell right" for="resourceCategory">Category: </label>
                <c:choose>
                    <c:when test="${resourceBean.resourceId == 0}">
                        <input class="table-cell required" type="text" name="resourceCategory" id="resourceCategory" value="${resourceBean.categories}"/>
                    </c:when>
                    <c:otherwise>
                        <input class="table-cell required" type="text" name="resourceCategory" id="resourceCategory" disabled="disabled" value="${resourceBean.categories}"/>
                    </c:otherwise>
                </c:choose>
                
                
                
            </div> <br />
            <div class="table-row">
                <label class="table-cell right" for="resourcePhone">Phone: </label>
                <input class="table-cell required" type="text" name="resourcePhone" id="resourcePhone" value="${resourceBean.phone}" maxlength="15"/>
                <label class="table-cell right" for="resourceCountry">Country: </label>
                <input class="table-cell required" type="text" name="resourceCountry" id="resourceCountry" value="${resourceBean.country}" maxlength="50"/>
                
                
            </div> <br />
            <div class="table-row">
                <label class="table-cell right" for="resourceAddress1">Address One: </label>
                <input class="table-cell required" type="text" name="resourceAddress1" id="resourceAddress1" value="${resourceBean.addressOne}" maxlength="50" />
                <label class="table-cell right" for="resourceEmail">Email: </label>
                <input class="table-cell required" type="text" name="resourceEmail" id="resourceEmail" value="${resourceBean.email}" maxlength="100"/>
                
                
                
            </div> <br />
            <div class="table-row">
                <label class="table-cell right" for="resourceAddress2">Address Two: </label>
                <input class="table-cell required" type="text" name="resourceAddress2" id="resourceAddress2" value="${resourceBean.addressTwo}" maxlength="50"/>
                <label class="table-cell right" for="resourceDescription">Description: </label>
                <section id="floater">
                    <textarea class="table-cell" name="resourceDescription" id="resourceDescription" rows="9" cols="16">${resourceBean.description}</textarea>
                </section>
                
            </div><br />
            <div class="table-row">
                <label class="table-cell right" for="resourceCity">City: </label>
                <input class="table-cell required" type="text" name="resourceCity" id="resourceCity" value="${resourceBean.city}" maxlength="50"/>
                
            </div> <br />
            <div class="table-row">
                <label class="table-cell right" for="resourceTerritory">Territory: </label>
                <input class="table-cell required" type="text" name="resourceTerritory" id="resourceTerritory" value="${resourceBean.territory}" maxlength="50"/>
                
            </div> <br />
            <div class="table-row">
                <label class="table-cell right" for="resourcePostalCode">Postal Code: </label>
                <input class="table-cell required" type="text" name="resourcePostalCode" id="resourcePostalCode" value="${resourceBean.postalCode}" maxlength="10"/>
                
            </div>
            <input class="centered" type="submit" value="Save" />
            <input type="button" onclick="location.href='http://localhost:8080/JCrisis/ResourceHandler?action=list';" value="Back" />
            <c:if test="${resourceBean.error != \"\"}">
                <p class="red">${resourceBean.error}</p>
            </c:if>
            
            
        </form>
        
    </jsp:body>
    
    
</t:template>
