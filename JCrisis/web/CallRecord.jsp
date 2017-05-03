<%-- 
    Document   : CallRecord
    Created on : Mar 26, 2017, 9:23:19 PM
    Author     : Laura Simmonds 
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@ page import="javax.servlet.http.HttpSession" %>
<%@page import="Beans.User"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }
    boolean hasRole = false;
    for (String role : user.getRoles()) {
        if (role.equals("counselor")) {
            hasRole = true;
        }
    }
    if (!hasRole) {
        request.getRequestDispatcher("/error/401Unauthorized.jsp").forward(request, response);
    }
%>
<jsp:useBean id="callPageBean" class="Beans.CallRecordPageBean" scope="session" />
<jsp:useBean id="resourcePageBean" class="Beans.ResourcePagesBean" scope="session" />

<t:template>
    <jsp:body>
        <form action="CallRecordHandler" method="GET" >
            <div style="display:inline-block; padding-left:20px;">
                <br /><br />
                <label for="counselorID">Counselor ID</label>&nbsp;
                <input type="text" name="counselorID" id="counselorID" /><br />               
            </div>
            <div style="display:inline-block; padding-left:40px">
                <br />
                <label for="dateTime">Date / Time</label>&nbsp;
                <input type="dateTime-local" name="dateTime" id="dateTime" /><br />
            </div>
            <div style="clear:both;">&nbsp;</div>
            <div style="display:inline-block; padding-left:35px">
                <br />
                <label for="firstName">First Name</label>&nbsp;
                <input type="text" name="firstName" id="firstName" /><br />               
            </div>
            <div style="display:inline-block; padding-left:90px">
                <br />
                <label for="city">City</label>&nbsp;
                <input type="text" name="city" id="city" /><br />
            </div>
            <div style="clear:both;">&nbsp;</div>
            <div style="display:inline-block; padding-left:37px;">
                <br />
                <label for="lastName">Last Name</label>&nbsp;
                <input type="text" name="lastName" id="lastName" /><br />               
            </div>
            <div style="display:inline-block; padding-left:85px">
                <br />
                <label for="state">State</label>&nbsp;
                <input type="text" name="state" id="state" /><br />
            </div>
            <div style="clear:both;">&nbsp;</div>
            <div style="display:inline-block; padding-left:67px">
                <br />
                <label for="phone">Phone</label>&nbsp;
                <input type="text" name="phone" id="phone" /><br />               
            </div>
            <div style="display:inline-block; padding-left:96px">
                <br />
                <label for="zip">Zip</label>&nbsp;
                <input type="text" name="zip" id="zip" /><br />
            </div>
            <div style="clear:both;">&nbsp;</div>
            <div style="display:inline-block; padding-left:55px">
                <br />
                <label for="address">Address</label>&nbsp;
                <input type="text" name="address" id="address" /><br />               
            </div>
            <div style="display:inline-block; padding-left:56px">
                <br />
                <label for="callType">Call Type</label>&nbsp;
                <select>
                    <option value="abuse">Abuse</option>
                    <option value="comResources">Community Resources</option>
                    <option value="depression">Depression / Suicide</option>
                    <option value="econChanges">Economic Changes</option>
                    <option value="jCrisis">Java Crisis</option>

                </select><br />               
            </div>
            <div style="clear:both;">&nbsp;</div>
            <div style="display:inline-block; padding-left:30px">
                <br />
                <label for="description">Description</label>&nbsp;
                <textarea rows="4" cols="72" placeholder="Description of the call"
                          name="description" id="description"></textarea><br />
            </div>
        </section>
        <br />
        <br />
        <section>

            <div style="display:inline-block; padding-left:10px">
                <style type="text/css" >
                    table, th, tr, td {
                        border: solid 1px #000000;
                        border-collapse: collapse;
                    }
                    td {
                        padding-left: 5px;
                        padding-right: 3px;
                    }
                    table#tItems  tr:nth-child(even) {
                        background-color: #ffffff;
                    }
                    table#tItems  tr:nth-child(odd) {
                        background-color: #ddffdd;
                    }
                    table#tItems th {
                        background-color: #004400;
                        color: #ffffff;
                    }
                    caption {
                        color: #000000;
                        font-weight: bold;
                        text-align: center;
                    }
                </style>
                <table id="tItems" style="width:350px;">
                    <caption>Resources</caption>
                    <colgroup>
                        <col style="text-align: right;" />
                        <col style="text-align: right;" />
                        <col style="text-align: right;" />
                    </colgroup>
                    <thead>
                        <tr>
                            <th>Resource Name</th>
                            <th>Phone</th>
                            <th>Description</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${resourcePageBean.resourceList}" var="resource">
                            <tr>
                                <td>${resource.name}</td>
                                <td>${resource.phone}</td>
                                <td>${resource.description}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>
            <div style="display:inline-block; padding-left:50px">
                <style type="text/css" >
                    table, th, tr, td {
                        border: solid 1px #000000;
                        border-collapse: collapse;
                    }
                    td {
                        padding-left: 5px;
                        padding-right: 3px;
                    }
                    table#tItems  tr:nth-child(even) {
                        background-color: #ffffff;
                    }
                    table#tItems  tr:nth-child(odd) {
                        background-color: #ddffdd;
                    }
                    table#tItems th {
                        background-color: #004400;
                        color: #ffffff;
                    }
                    caption {
                        color: #000000;
                        font-weight: bold;
                        text-align: center;
                    }
                </style>
                <table id="tItems" style="width:350px;">
                    <caption>Previous Call Records</caption>
                    <colgroup>
                        <col style="text-align: right;" />
                        <col style="text-align: right;" />
                        <col style="text-align: right;" />
                    </colgroup>
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Call Type</th>
                            <th>Description</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${callPageBean.callList}" var="callRecord">
                            <tr>
                                <td>${callRecord.endTime.toString()}</td>
                                <td>${callRecord.callTypeID}</td>
                                <td>${callRecord.callDescription}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div style="clear:both;">&nbsp;</div>
            <div style="display:inline-block; padding-left:317px">
                <input name="btnCreate" type="submit" value="Create"/>
            </div>
            <div style="display:inline-block; padding-right:300x">
                <button type="button">Close</button>
            </div>

    </form>
</jsp:body>
</t:template>