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
    
    <div id="contentWrapper">
        
        
        <table id="applicationFormTable" class="formTable">
            <tr>
                <th colspan="2">
                    <h2>Application view</h2> 
                </th>
            </tr>
            <tr>
                <td>Name:</td>
                <td>${message.getFirstname()}</td>
            </tr>
            <tr>
                <td>Surname:</td>
                <td>${message.getLastname()}</td>
            </tr>
            <tr>
                <td>
                    Date of birth:
                </td>
                <td>
                    ${message.getDateOfBirth()}
                </td>
            </tr>
            <tr>
                <td>Email:</td>
                <td>${message.getEmail()}</td>
            </tr>
            <tr>
                <td>Telephone:</td>
                <td>${message.getPhone()}</td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="hidden" id="workExpertiseInput" name="workExpertiseInput" /> 
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
                <td>
                    From:
                </td>
                <td>
                    Year:
                    
                    Month:
                    
                    Day:
                </td>
            </tr>
            <tr>
                <td>
                    Until:
                </td>
                <td>
                    Year:
                    
                    Month:
                    
                    Day:
                </td>
            </tr>
        </table>
        
    
    </div>
    
</body>
</html>