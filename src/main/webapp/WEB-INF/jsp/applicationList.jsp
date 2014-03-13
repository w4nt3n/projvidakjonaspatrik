<%-- 
    Document   : applicationList
    Created on : Feb 26, 2014, 8:28:40 AM
    Author     : Vidak
--%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  <%-- needed to loop through list --%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<c:url value="/resources/css/generalStyle.css" />" type="text/css" rel="stylesheet">
    <link href="<c:url value="/resources/css/layout.css" />" type="text/css" rel="stylesheet">
    <title>Applications</title>
</head>
<body>
    
    <%@include file="header.jsp" %>
    
    <div id="contentWrapper">
        <table id="applicationListTable" class="formTable">
            <tr>
                <th colspan="6">
                    <h2>Applications received</h2> 
                </th>
            </tr>
            <tr>
                <td></td>
                <td><b>Name</b></td>
                <td><b>Surname</b></td>
                <td><b>Email</b></td>
                <td><b>Telephone</b></td>
            </tr>
            <c:forEach items="${message.getAllApplications()}" var="applicant">
                <tr>
                    <td><a href="applicationView.htm?applicantID=${applicant.getId()}">View</a></td>
                    <td>${applicant.getFirstname()}</td>
                    <td>${applicant.getLastname()}</td>
                    <td>${applicant.getEmail()}</td>
                    <td>${applicant.getPhone()}</td>
                </tr>
            </c:forEach>
        </table> 
    </div>
    
    <%@include file="footer.jsp" %>
    
</body>
</html>