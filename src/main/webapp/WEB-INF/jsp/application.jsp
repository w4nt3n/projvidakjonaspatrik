<%-- 
    Document   : Application
    Created on : 2014-feb-24, 11:54:35
    Author     : Jonas
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
    <script src="<c:url value="/resources/javascript/applicationFormChecker.js" />"></script>
    <title>Spring 3 MVC Series - Contact Manager</title>
</head>
<body onload="setBirthdayDaySelect()">
    
    <div id="contentWrapper">
        
        <c:choose>
            
            <c:when test="${command.getAfterSubmit() == true}">
                <div class="textCenterDiv">
                    Your application has now been submitted.
                </div>
            </c:when>

            <c:otherwise>
                <form:form method="post" action="addApplier.htm">
                    <table id="applicationFormTable" class="formTable">
                        <tr>
                            <th colspan="2">
                                <h2>Contact Manager</h2> 
                            </th>
                        </tr>
                        <tr>
                            <td><form:label path="firstname">Name:</form:label></td>
                            <td><form:input path="firstname" id="firstnameTextBox" onkeyup="checkName('firstnameTextBox', 0)" onchange="checkName('firstnameTextBox', 0)" class="textInput" /></td>
                        </tr>
                        <tr>
                            <td><form:label path="surname">Surname:</form:label></td>
                            <td><form:input path="surname" id="surnameTextBox" onkeyup="checkName('surnameTextBox', 1)" onchange="checkName('surnameTextBox', 1)" class="textInput" /></td>
                        </tr>
                        <tr>
                            <td><form:label path="age">Age:</form:label></td>
                            <td><form:input path="age" class="textInput" /></td>
                        </tr>
                        <tr>
                            <td>
                                Date of birth:
                            </td>
                            <td>
                                Year:
                                <select id="birthdayYearSelect" onchange="setBirthdayDaySelect(); checkDate('birthdayYearSelect', 2); checkDate('birthdayDaySelect', 4)">
                                    <option></option>
                                    <%int number = (Calendar.getInstance()).get(Calendar.YEAR);
                                    for(int i = number; i>= number - 150; i--){%>
                                        <option><%=i %></option>
                                    <%} %>
                                </select>
                                
                                Month:
                                <select id="birthdayMonthSelect" onchange="setBirthdayDaySelect(); checkDate('birthdayMonthSelect', 3); checkDate('birthdayDaySelect', 4)">
                                    <option></option>
                                    <%number = 12;
                                    for(int i = 01; i<= number; i++){%>
                                        <option><%=i %></option>
                                    <%} %>
                                </select>
                                Day:
                                <select id="birthdayDaySelect" onchange="checkDate('birthdayDaySelect', 4)">
                                    <select></select>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><form:label path="email">Email:</form:label></td>
                            <td><form:input path="email" id="emailTextBox" onkeyup="checkEmail('emailTextBox', 5)" onchange="checkEmail('emailTextBox', 5)" class="textInput" /></td>
                        </tr>
                        <tr>
                            <td><form:label path="telephone">Telephone:</form:label></td>
                            <td><form:input path="telephone" id="telephoneTextBox" onkeyup="checkTelephone('telephoneTextBox', 6)" onchange="checkTelephone('telephoneTextBox', 6)" class="textInput" /></td>
                        </tr>
                        <tr>
                            <td colspan="2">Competence Profile: </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <textarea class="bigTextBox"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                Upload your CV, please select it.
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="file" name="file" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="submit" id="submitButton" disabled="true" value="Submit application" class="centerdInput"/>
                                <label id="submitButtonEnableLable" style="display: none; float: right; color: red;">Please review your form, some inputs are invalid</label>
                            </td>
                        </tr>
                    </table> 
                </form:form>
            </c:otherwise>
                
        </c:choose>
 
        
    
    </div>
    
</body>
</html>