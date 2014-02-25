<%-- 
    Document   : Application
    Created on : 2014-feb-24, 11:54:35
    Author     : Jonas
--%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<c:url value="/resources/css/generalStyle.css" />" type="text/css" rel="stylesheet">
    <link href="<c:url value="/resources/css/layout.css" />" type="text/css" rel="stylesheet">
    <title>Spring 3 MVC Series - Contact Manager</title>
</head>
<body>
    
    <div id="contentWrapper">
        
        <h2>Contact Manager</h2>

        <form:form method="post" action="addApplier.htm">
            <table class="formTable">
                <tr>
                    <td><form:label path="firstname">Name:</form:label></td>
                    <td><form:input path="firstname" class="textInput" /></td>
                </tr>
                <tr>
                    <td><form:label path="surname">Surname:</form:label></td>
                    <td><form:input path="surname" class="textInput" /></td>
                </tr>
                <tr>
                    <td><form:label path="age">Age:</form:label></td>
                    <td><form:input path="age" class="textInput" /></td>
                </tr>
                <tr>
                    <td><form:label path="email">Email:</form:label></td>
                    <td><form:input path="email" class="textInput" /></td>
                </tr>
                <tr>
                    <td><form:label path="telephone">Telephone:</form:label></td>
                    <td><form:input path="telephone" class="textInput" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Submit application" class="centerdInput"/>
                    </td>
                </tr>
            </table> 
        </form:form>
    
    </div>
    
</body>
</html>