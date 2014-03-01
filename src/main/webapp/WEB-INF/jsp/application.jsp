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
    <title>Application Form</title>
</head>
<body onload="setBirthdayDaySelect()">
    
    <div id="contentWrapper">
        
        <div id="jobExpertiseTemplate" style="display: none;">
            <table id="jobExpertiseRow">
                <input id="jobExpertiseRowExperiteseInput" type="hidden" value="" />
                <input id="jobExpertiseRowYearsInput" type="hidden" value="" />
                <tr>
                    <td><button type="button" onclick="removeRow('jobExpertiseRow')">X</button></td>
                    <td>jobExpertiseRowPlaceholderText</td>
                    <td>jobExpertiseRowPlaceholderYears years</td>
                </tr>
            </table>
        </div>
        
        <c:choose>
            
            <c:when test="${command.getAfterSubmit() == true}">
                <div class="textCenterDiv">
                    Your application has now been submitted.
                    " ${command.getName()} "
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
                            <td><form:label path="name">Name:</form:label></td>
                            <td><form:input path="name" id="nameTextBox" onkeyup="checkName('nameTextBox', 0)" onchange="checkName('nameTextBox', 0)" class="textInput" /></td>
                        </tr>
                        <tr>
                            <td><form:label path="surname">Surname:</form:label></td>
                            <td><form:input path="surname" id="surnameTextBox" onkeyup="checkName('surnameTextBox', 1)" onchange="checkName('surnameTextBox', 1)" class="textInput" /></td>
                        </tr>
                        <tr>
                            <td>
                                Date of birth:
                            </td>
                            <td>
                                Year:
                                <select name="birthdayYearSelect" id="birthdayYearSelect" onchange="setBirthdayDaySelect('birthdayYearSelect', 'birthdayMonthSelect', 'birthdayDaySelect'); checkDate('birthdayYearSelect', 2); checkDate('birthdayDaySelect', 4)">
                                    <option></option>
                                    <%int number = (Calendar.getInstance()).get(Calendar.YEAR);
                                    for(int i = number; i>= number - 150; i--){%>
                                        <option><%=i %></option>
                                    <%} %>
                                </select>
                                Month:
                                <select name="birthdayMonthSelect" id="birthdayMonthSelect" onchange="setBirthdayDaySelect('birthdayYearSelect', 'birthdayMonthSelect', 'birthdayDaySelect'); checkDate('birthdayMonthSelect', 3); checkDate('birthdayDaySelect', 4)">
                                    <option></option>
                                    <%number = 12;
                                    for(int i = 01; i<= number; i++){%>
                                        <option><%=i %></option>
                                    <%} %>
                                </select>
                                Day:
                                <select name="birthdayDaySelect" id="birthdayDaySelect" onchange="checkDate('birthdayDaySelect', 4)">
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
                            <td colspan="2">
                                <input type="hidden" id="workExpertiseInput" name="workExpertiseInput" />
                                Expertise:
                                <select id="workExpertiseExpertiseSelect">
                                    <c:forEach items="${command.getExpertiseList()}" var="expertise">
                                        <option>${expertise.getExpertiseName()}</option>
                                    </c:forEach>
                                </select>
                                Years:
                                <select id="workExpertiseYearsSelect">
                                    <%number = 100;
                                    for(int i = 0; i <= number; i++){%>
                                        <option><%=i %></option>
                                    <%} %>
                                </select>
                                <button type="button" onclick="addANewExpertise()">Add expertise</button>
                                <div id="workExpertiseListDiv">
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
                                <select id="workFromYearSelect" onchange="setBirthdayDaySelect('workFromYearSelect', 'workFromMonthSelect', 'workFromDaySelect'); checkDate('workFromYearSelect', 7); checkDate('workFromDaySelect', 9)">
                                    <option></option>
                                    <%number = (Calendar.getInstance()).get(Calendar.YEAR);
                                    for(int i = number; i>= number - 150; i--){%>
                                        <option><%=i %></option>
                                    <%} %>
                                </select>
                                Month:
                                <select id="workFromMonthSelect" onchange="setBirthdayDaySelect('workFromYearSelect', 'workFromMonthSelect', 'workFromDaySelect'); checkDate('workFromMonthSelect', 8); checkDate('workFromDaySelect', 9)">
                                    <option></option>
                                    <%number = 12;
                                    for(int i = 01; i<= number; i++){%>
                                        <option><%=i %></option>
                                    <%} %>
                                </select>
                                Day:
                                <select id="workFromDaySelect" onchange="checkDate('workFromDaySelect', 9)">
                                    <select></select>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Until:
                            </td>
                            <td>
                                Year:
                                <select id="workToYearSelect" onchange="setBirthdayDaySelect('workToYearSelect', 'workToMonthSelect', 'workToDaySelect'); checkDate('workToYearSelect', 10); checkDate('workToDaySelect', 12)">
                                    <option></option>
                                    <%number = (Calendar.getInstance()).get(Calendar.YEAR);
                                    for(int i = number; i>= number - 150; i--){%>
                                        <option><%=i %></option>
                                    <%} %>
                                </select>
                                Month:
                                <select id="workToMonthSelect" onchange="setBirthdayDaySelect('workToYearSelect', 'workToMonthSelect', 'workToDaySelect'); checkDate('workToMonthSelect', 11); checkDate('workToDaySelect', 12)">
                                    <option></option>
                                    <%number = 12;
                                    for(int i = 01; i<= number; i++){%>
                                        <option><%=i %></option>
                                    <%} %>
                                </select>
                                Day:
                                <select id="workToDaySelect" onchange="checkDate('workToDaySelect', 12)">
                                    <select></select>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">Write your experience in different areas of expertise: </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <textarea class="bigTextBox"></textarea>
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