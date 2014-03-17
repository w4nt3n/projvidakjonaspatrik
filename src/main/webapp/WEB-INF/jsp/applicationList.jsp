<%-- 
    Document   : applicationList
    Created on : Feb 26, 2014, 8:28:40 AM
    Author     : Vidak, Patrik
--%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<c:url value="/resources/css/generalStyle.css" />" type="text/css" rel="stylesheet">
    <link href="<c:url value="/resources/css/layout.css" />" type="text/css" rel="stylesheet">
    <title>Applications</title>
</head>
<body>
    
    <%@include file="header.jsp" %>
    
    <c:choose>
            
        <c:when test="${message.hasError() == true}">
            <div class="textCenterDiv">
                It seems components of the page cant be loaded. We are sorry.
            </div>
        </c:when>

        <c:otherwise>
            <div id="contentWrapper">
                <table id="applicationListTable" class="formTable">
                    <tr>
                        <th colspan="6">
                            <h2><spring:message code="label.applications" text="Applications"/></h2> 
                        </th>
                    </tr>
                    <tr>
                        <td colspan="20">
                            <fieldset>
                                <legend><spring:message code="label.applicationFiltering" text="Application Filtering"/></legend>
                                Available From: <input type="text"/> To: <input type="text"/><button>Filter</button>
                            </fieldset>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><b><spring:message code="label.firstname" text="Firstname"/></b></td>
                        <td><b><spring:message code="label.lastname"  text="Lastname"/></b></td>
                        <td><b><spring:message code="label.email"     text="Email"/></b></td>
                        <td><b><spring:message code="label.phone"     text="Phone"/></b></td>
                    </tr>
                    <c:forEach items="${message.getAllApplications()}" var="applicant">
                        <tr class="row">
                            <td><a href="applicationView.htm?applicantID=${applicant.getId()}"><spring:message code="label.view" text="View"/></a></td>
                            <td>${applicant.getFirstname()}</td>
                            <td>${applicant.getLastname()}</td>
                            <td>${applicant.getEmail()}</td>
                            <td>${applicant.getPhone()}</td>
                        </tr>
                    </c:forEach>
                </table> 
            </div>
        </c:otherwise>
                
        </c:choose>
    
    <%@include file="footer.jsp" %>
    
</body>
</html>