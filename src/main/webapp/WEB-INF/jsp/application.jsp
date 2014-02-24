<%-- 
    Document   : Application
    Created on : 2014-feb-24, 11:54:35
    Author     : Jonas
--%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring 3 MVC Series - Contact Manager</title>
</head>
<body>
    <h2>Contact Manager</h2>

    <form:form method="post" action="addApplier.htm">
        <table>
            <tr>
                <td><form:label path="firstname">First Name</form:label></td>
                <td><form:input path="firstname" /></td>
            </tr>
            <tr>
                <td><form:label path="lastname">Last Name</form:label></td>
                <td><form:input path="lastname" /></td>
            </tr>
            <tr>
                <td><form:label path="lastname">Email</form:label></td>
                <td><form:input path="email" /></td>
            </tr>
            <tr>
                <td><form:label path="lastname">Telephone</form:label></td>
                <td><form:input path="telephone" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Add Applier"/>
                </td>
            </tr>
        </table> 
    </form:form>
</body>
</html>