<%-- 
    Document   : applicationview
    Created on : Mar 1, 2014, 8:30:43 AM
    Author     : Vidak
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<c:url value="/resources/css/generalStyle.css" />" type="text/css" rel="stylesheet">
    <link href="<c:url value="/resources/css/layout.css" />" type="text/css" rel="stylesheet">
    <title>Application View</title>
</head>
<body>
    
    <%@include file="header.jsp" %>
    
    <div id="contentWrapper">
        
        
        <table id="applicationFormTable" class="formTable">
            <tr>
                <th colspan="2">
                    <h2><spring:message code="label.applicationViewForm"/></h2> 
                </th>
            </tr>
            <tr>
                <td><spring:message code="label.firstname"/>:</td>
                <td>${message.getFirstname()}</td>
            </tr>
            <tr>
                <td><spring:message code="label.lastname"/>:</td>
                <td>${message.getLastname()}</td>
            </tr>
            <tr>
                <td>
                    <spring:message code="label.dateOfBirth"/>:
                </td>
                <td>
                    ${message.getDateOfBirth()}
                </td>
            </tr>
            <tr>
                <td><spring:message code="label.email"/>:</td>
                <td>${message.getEmail()}</td>
            </tr>
            <tr>
                <td><spring:message code="label.phone"/>:</td>
                <td>${message.getPhone()}</td>
            </tr>
            <tr>
                <td colspan="2">
                    <div id="workExpertiseListDiv">
                        <table colspan="0" rowspan="0" style="padding: 0px; margin: 0px;">
                            <tr>
                                <td>
                                    <b>Expertise:</b>
                                </td>
                                <td>
                                    <b>Years:</b>
                                </td>
                            </tr>
                            <c:forEach items="${message.getExpExpList()}" var="container">
                                <tr>
                                    <td>${container.getExpertise().getExpertiseName()}</td>
                                    <td>${container.getApplicantExperience().getYears()}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    Periods of time available for work
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div id="availableListDiv">
                        <table colspan="0" rowspan="0" style="padding: 0px; margin: 0px;">
                            <tr>
                                <td>
                                    <b>From</b>
                                </td>
                                <td>
                                    <b>To</b>
                                </td>
                            </tr>
                            <c:forEach items="${message.getAvList()}" var="container">
                                <tr>
                                    <td>${container.getFrom()}</td>
                                    <td>${container.getTo()}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </td>
            </tr>
        </table>
        
    
    </div>
    
    <%@include file="footer.jsp" %>
            
</body>
</html>