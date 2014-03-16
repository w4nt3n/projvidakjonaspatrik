<%-- 
    Document   : applicationview
    Created on : Mar 1, 2014, 8:30:43 AM
    Author     : Vidak
    Edited     : Patrik
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<c:url value="/resources/css/generalStyle.css" />" type="text/css" rel="stylesheet">
    <link href="<c:url value="/resources/css/layout.css" />" type="text/css" rel="stylesheet">
    <title><spring:message code="label.title"/></title>
</head>
<body>
    
    <%@include file="header.jsp" %>
    
    <c:choose>
            
        <c:when test="${message.hasError() == true}">
            <div class="textCenterDiv">
                Something went wrong, we are sorry. ${message.getError()}
            </div>
        </c:when>

        <c:otherwise>
    
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
                            <fieldset id="fieldsetExperiences">
                                <legend><spring:message code="label.areasOfExpertise"/></legend>
                                <table colspan="0" rowspan="0" style="padding: 0px; margin: 0px;">
                                    <tr>
                                        <td>
                                            <b><spring:message code="label.expertise"/>:</b>
                                        </td>
                                        <td>
                                            <b><spring:message code="label.years"/>:</b>
                                        </td>
                                    </tr>
                                    
                                    <c:forEach items="${message.getExpExpList()}" var="container">
                                        <tr>
                                            <td>${container.getExpertise().getExpertiseName()}</td>
                                            <td>${container.getApplicantExperience().getYears()}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </fieldset>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <fieldset id="fieldsetAvailability">
                                <legend><spring:message code="label.periodsOfAvailability"/></legend>
                                <table colspan="0" rowspan="0" style="padding: 0px; margin: 0px;">
                                    <tr>
                                        <td>
                                            <b><spring:message code="label.from"/></b>
                                        </td>
                                        <td>
                                            <b><spring:message code="label.to"/></b>
                                        </td>
                                    </tr>
                                    <c:forEach items="${message.getAvList()}" var="container">
                                        <tr>
                                            <td>${container.getFrom()}</td>
                                            <td>${container.getTo()}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </fieldset>
                        </td>
                    </tr>
                </table>


            </div>
        </c:otherwise>
                
    </c:choose>
    
    <%@include file="footer.jsp" %>
            
</body>
</html>