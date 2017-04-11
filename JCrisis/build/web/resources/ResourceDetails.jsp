<%-- 
    Document   : ResourceDetails
    Created on : Apr 4, 2017, 9:57:44 AM
    Author     : Christian Lopez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="resourceBean" class="Beans.Resource" scope="session" />

<t:template folderDots="../">
    
    <jsp:body>
        
        <c:choose>
            <c:when test="${resourceBean.name == null}">
                    <h3 class="centered">Resource</h3>
            </c:when>
            <c:otherwise>
                <h3 class="centered">${resourceBean.name}</h3>
            </c:otherwise>
        </c:choose>
        
        <!--<h3 class="centered">Resource</h3>-->
        
        <form class="table" id="resourceDetailsForm" method="GET" action="#">
            <div class="table-row">
                
                
            </div>
            <div class="table-row">
                <label class="table-cell right" for="resourceCategory">Category: </label>
                <input class="table-cell required" type="text" name="resourceCategory" id="resourceCategory" />
                <label class="table-cell right" for="resourceHours">Country: </label>
                <input class="table-cell required" type="text" name="resourceCountry" id="resourceHours" />
                
            </div> <br />
            <div class="table-row">
                <label class="table-cell right" for="resourcePhone">Phone: </label>
                <input class="table-cell required" type="text" name="resourcePhone" id="resourcePhone" />
                <label class="table-cell right" for="resourceEmail">Email: </label>
                <input class="table-cell required" type="text" name="resourceEmail" id="resourceEmail" />
                
            </div> <br />
            <div class="table-row">
                <label class="table-cell right" for="resourceAddress1">Address One: </label>
                <input class="table-cell required" type="text" name="resourceAddress1" id="resourceAddress1" />
                <label class="table-cell right" for="resourceDescription">Description: </label>
                <section id="floater">
                    <textarea class="table-cell" name="resourceDescription" id="resourceDescription" rows="9" cols="16"></textarea>
                </section>
                
                
            </div> <br />
            <div class="table-row">
                <label class="table-cell right" for="resourceAddress2">Address Two: </label>
                <input class="table-cell required" type="text" name="resourceAddress2" id="resourceAddress2" />
                
            </div><br />
            <div class="table-row">
                <label class="table-cell right" for="resourceCity">City: </label>
                <input class="table-cell required" type="text" name="resourceCity" id="resourceCity" />
                
            </div> <br />
            <div class="table-row">
                <label class="table-cell right" for="resourceState">State: </label>
                <input class="table-cell required" type="text" name="resourceState" id="resourceState" />
                
            </div> <br />
            <div class="table-row">
                <label class="table-cell right" for="resourceZip">Zip: </label>
                <input class="table-cell required" type="text" name="resourceZip" id="resourceZip" />
                
            </div>
            <input class="centered" type="submit" value="Update" />
            <input type="button" onclick="location.href='../Resources.jsp';" value="Back" />
            
            
            
        </form>
        
    </jsp:body>
    
    
</t:template>
