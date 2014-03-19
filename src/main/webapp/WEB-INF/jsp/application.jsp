<%-- 
    Document   : application
    Created on : Feb 22, 2014, 13:14:15 PM
    Author     : Vidak, Patrik
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<c:url value="/resources/css/generalStyle.css" />" type="text/css" rel="stylesheet">
    <link href="<c:url value="/resources/css/layout.css" />" type="text/css" rel="stylesheet">
    <link href="<c:url value="/resources/css/pikaday.css" />" type="text/css" rel="stylesheet">
    <script src="<c:url value="/resources/javascript/validator.js" />"></script>
    <script src="<c:url value="/resources/javascript/pikaday.js" />"></script>
    <script src="<c:url value="/resources/javascript/buttonList.js" />"></script>
    <script type="text/javascript">
        
    function submitButtonLists() {
        document.getElementById("inputExperience").value = bListExp.join(",");
        document.getElementById("inputAvailability").value = bListPeriods.join(",");
    }
        
    </script>
    <title><spring:message code="label.title"/></title>
</head>
<body onload="setBirthdayDaySelect()">
    
    <%@include file="header.jsp" %>
    
    <div id="contentWrapper">
        
        <c:choose>
            
            <c:when test="${command.getAfterSubmit() == true}">
                <div class="textCenterDiv">
                    <spring:message code="label.submitted"/>
                </div>
            </c:when>
            
            <c:when test="${command.hasError() == true}">
                <div class="textCenterDiv">
                    Something went wrong, we are sorry. ${command.getError()}
                </div>
            </c:when>

            <c:otherwise>
                <form:form method="post" action="addApplier.htm">
                    <table id="applicationFormTable" class="formTable">
                        <input id="inputExperience" name="inputExperience" type="hidden"/>
                        <input id="inputAvailability" name="inputAvailability" type="hidden"/>
                        <tr>
                            <th colspan="2">
                                <h2><spring:message code="label.applicationForm"/></h2>
                            </th>
                        </tr>
                        <tr>
                            <td><form:label path="firstname"><spring:message code="label.firstname"/>:</form:label></td>
                            <td><form:input path="firstname" id="inputName" class="textInput" /></td>
                        </tr>
                        <tr>
                            <td><form:label path="lastname"><spring:message code="label.lastname"/>:</form:label></td>
                            <td><form:input path="lastname" id="inputSurname" class="textInput" /></td>
                        </tr>
                        <tr>
                            <td><spring:message code="label.dateOfBirth"/>:</td>
                            <td>
                                <spring:message code="label.year"/>:
                                <select name="dropdownYear" id="dropdownYear">
                                    <option></option>
                                    <%int number = (Calendar.getInstance()).get(Calendar.YEAR);
                                    for(int i = number; i>= number - 150; i--){%>
                                        <option><%=i %></option>
                                    <%} %>
                                </select>
                                <spring:message code="label.month"/>:
                                <select name="dropdownMonth" id="dropdownMonth">
                                    <option></option>
                                    <%number = 12;
                                    for(int i = 01; i<= number; i++){%>
                                        <option><%=i %></option>
                                    <%} %>
                                </select>
                                <spring:message code="label.day"/>:
                                <select name="dropdownDay" id="dropdownDay">
                                    <option></option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><form:label path="email"><spring:message code="label.email"/>:</form:label></td>
                            <td><form:input path="email" id="inputEmail" class="textInput" /></td>
                        </tr>
                        <tr>
                            <td><form:label path="phone"><spring:message code="label.phone"/>:</form:label></td>
                            <td><form:input path="phone" id="inputPhone" class="textInput" /></td>
                        </tr>
                        
                        <%-- Areas of Expertise --%>
                        <tr>
                            <td colspan = "20">
                                <fieldset id="fieldsetExperiences">
                                    <legend><spring:message code="label.areasOfExpertise"/></legend>
                                    <spring:message code="label.expertise"/>:
                                    <select id="workExpertiseExpertiseSelect">
                                        <c:forEach items="${command.getExpertiseList()}" var="expertise">
                                            <option value="${expertise.getID()}">${expertise.getExpertiseName()}</option>
                                        </c:forEach>
                                    </select><br>
                                    <spring:message code="label.years"/>:
                                    <select id="workExpertiseYearsSelect">
                                        <option></option>
                                        <%number = 100;
                                        for(int i = 1; i <= number; i++){%>
                                            <option><%=i %></option>
                                        <%} %>
                                    </select>
                                    <button type="button" onclick="addANewExpertise(); submitButtonLists();"><spring:message code="label.add"/></button>
                                </fieldset>
                            </td>
                        </tr>
                        
                        <%-- Periods of Availability --%>
                        <tr>
                            <td colspan = "20">
                                <fieldset id="fieldsetAvailability">
                                    <legend><spring:message code="label.periodsOfAvailability"/></legend>
                                    <spring:message code="label.from"/>: <input type="text" style="width:120px;" id="datepickerFrom">
                                    <spring:message code="label.to"/>: <input type="text" style="width:120px;" id="datepickerTo">
                                    <button type="button" onclick="addPeriod(); submitButtonLists();"><spring:message code="label.add"/></button>
                                </fieldset>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="submit" id="submitButton" disabled="true" value="<spring:message code="label.submit"/>" class="centerdInput"/>
                                <label id="submitButtonEnableLable" type="hidden" style="display: none; float: right; color: red;"><spring:message code="error.invalidInput"/></label>
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
    var bListExp = new ButtonList("fieldsetExperiences", ["<spring:message code="label.expertise"/>", "<spring:message code="label.yearsOfExperience"/>"]);
    var bListPeriods = new ButtonList("fieldsetAvailability", ["<spring:message code="label.from"/>", "<spring:message code="label.to"/>"]);
    
    new Pikaday({ field: document.getElementById('datepickerFrom') });
    new Pikaday({ field: document.getElementById('datepickerTo') });
    
    var validator = new Validator("inputName", "inputEmail", "inputSurname", "inputPhone");
    validator.add("dropdownYear", checkDateOfBirth);
    validator.add("dropdownMonth", checkDateOfBirth);
    validator.add("dropdownDay", checkDropdown);
</script>
    
</body>
</html>
