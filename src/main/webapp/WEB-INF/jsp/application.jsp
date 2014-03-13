<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<c:url value="/resources/css/generalStyle.css" />" type="text/css" rel="stylesheet">
    <link href="<c:url value="/resources/css/layout.css" />" type="text/css" rel="stylesheet">
    <link href="<c:url value="/resources/css/pikaday.css" />" type="text/css" rel="stylesheet">
    <script src="<c:url value="/resources/javascript/applicationFormChecker.js" />"></script>
    <script src="<c:url value="/resources/javascript/pikaday.js" />"></script>
    <script src="<c:url value="/resources/javascript/buttonList.js" />"></script>
    <title>Application Form</title>
</head>
<body onload="setBirthdayDaySelect()">
    
    <%@include file="header.jsp" %>
    
    <div id="contentWrapper">
        
        <c:choose>
            
            <c:when test="${command.getAfterSubmit() == true}">
                <div class="textCenterDiv">
                    Your application has now been submitted.
                    " ${command.getFirstname()} "
                </div>
            </c:when>

            <c:otherwise>
                <form:form method="post" action="addApplier.htm">
                    <table id="applicationFormTable" class="formTable">
                        <input id="inputExperience" name="inputExperience" type="hidden" onsubmit="submitButtonLists()"/>
                        <input id="inputAvailability" name="inputAvailability" type="hidden"/>
                        <tr>
                            <th colspan="2">
                                <h2>Contact Manager</h2>
                            </th>
                        </tr>
                        <tr>
                            <td><form:label path="firstname">Name:</form:label></td>
                            <td><form:input path="firstname" id="inputName" class="textInput" /></td>
                            <%--<td><input type="text" id="inputName" name="FirstName"></td>--%>
                        </tr>
                        <tr>
                            <td><form:label path="lastname">Surname:</form:label></td>
                            <td><form:input path="lastname" id="inputSurname" class="textInput" /></td>
                        </tr>
                        <tr>
                            <td>Date of birth:</td>
                            <td>
                                Year:
                                <select name="dropdownYear" id="dropdownYear">
                                    <option></option>
                                    <%int number = (Calendar.getInstance()).get(Calendar.YEAR);
                                    for(int i = number; i>= number - 150; i--){%>
                                        <option><%=i %></option>
                                    <%} %>
                                </select>
                                Month:
                                <select name="dropdownMonth" id="dropdownMonth">
                                    <option></option>
                                    <%number = 12;
                                    for(int i = 01; i<= number; i++){%>
                                        <option><%=i %></option>
                                    <%} %>
                                </select>
                                Day:
                                <select name="dropdownDay" id="dropdownDay">
                                    <option></option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><form:label path="email">Email:</form:label></td>
                            <td><form:input path="email" id="inputEmail" class="textInput" /></td>
                        </tr>
                        <tr>
                            <td><form:label path="phone">Telephone:</form:label></td>
                            <td><form:input path="phone" id="inputPhone" class="textInput" /></td>
                        </tr>
                        
                        <%-- Areas of Expertise --%>
                        <tr>
                            <td colspan = "20">
                                <fieldset id="fieldsetExperiences">
                                    <legend>Areas of Expertise</legend>
                                    Expertise:
                                    <select id="workExpertiseExpertiseSelect">
                                        <c:forEach items="${command.getExpertiseList()}" var="expertise">
                                            <option value="${expertise.getID()}">${expertise.getExpertiseName()}</option>
                                        </c:forEach>
                                    </select><br>
                                    Years:
                                    <select id="workExpertiseYearsSelect">
                                        <option></option>
                                        <%number = 100;
                                        for(int i = 1; i <= number; i++){%> // @TODO: Kolla upp $taggar
                                            <option><%=i %></option>
                                        <%} %>
                                    </select>
                                    <button type="button" onclick="addANewExpertise()">Add</button>
                                </fieldset>
                            </td>
                        </tr>
                        
                        <%-- Periods of Availability --%>
                        <tr>
                            <td colspan = "20">
                                <fieldset id="fieldsetAvailability">
                                    <legend>Periods of Availability</legend>
                                    From: <input type="text" style="width:120px;" id="datepickerFrom">
                                    To: <input type="text" style="width:120px;" id="datepickerTo">
                                    <button type="button" onclick="addPeriod()">Add</button>
                                </fieldset>
                            </td>
                        </tr>
                        
                        
                        <tr>
                            <td colspan="2">Write your experience in different areas of expertise: </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <textarea class="bigTextBox" id="inputText"></textarea>
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

    
    <%@include file="footer.jsp" %>
    
<%-- Pikaday, instantiate datepickers for Periods of Availability field --%>
<script src="pikaday.js"></script>
<script>
    var bListExp = new ButtonList("fieldsetExperiences", ["Expertise", "Years of Experience"]);
    var bListPeriods = new ButtonList("fieldsetAvailability", ["From", "To"]);
    
    new Pikaday({ field: document.getElementById('datepickerFrom') });
    new Pikaday({ field: document.getElementById('datepickerTo') });
    
    var validator = new Validator("inputName", "inputSurname", "inputEmail", "inputPhone");
    validator.add("dropdownYear", checkDateOfBirth);
    validator.add("dropdownMonth", checkDateOfBirth);
    validator.add("dropdownDay", checkDropdown);
    
    function submitButtonLists() {
        document.getElementById("inputExperience").innerHTML = bListExp.join();
        document.getElementById("inputAvailability").innerHTML = bListPeriods.join();
    }
</script>
    
</body>
</html>
