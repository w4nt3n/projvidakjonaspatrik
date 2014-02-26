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
    <title>Spring 3 MVC Series - Contact Manager</title>
</head>
<body>
    
    <div id="contentWrapper">
 
            <table id="applicationListTable" class="formTable">
                <tr>
                    <th colspan="5">
                        <h2>Applications received</h2> 
                    </th>
                </tr>
                <c:forEach items="${message.getAllApplications()}" var="person">
                <tr>
                    <td><b>Name:</b> ${person.getName()}</td>
                    <td><b>Surname:</b> ${person.getSurname()}</td>
                    <td><b>Age:</b> ${person.getAge()}</td>
                    <td><b>Email:</b> ${person.getEmail()}</td>
                    <td><b>Telephone:</b> ${person.getTelephone()}</td>
                </tr>
                </c:forEach>
            </table> 
    
    </div>
    
</body>
</html>
