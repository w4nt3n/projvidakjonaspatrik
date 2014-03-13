<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<c:url value="/resources/css/generalStyle.css" />" type="text/css" rel="stylesheet">
    <link href="<c:url value="/resources/css/layout.css" />" type="text/css" rel="stylesheet">
    <title>Login Page</title>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body onload='document.f.j_username.focus();'>
    
    <%@include file="header.jsp" %>
    
    <div id="contentWrapper">
        <%-- If there is an error --%>
	<c:if test="${not empty error}">
            <div class="errorblock">
                Your login attempt was not successful, try again.<br /> Cause :
                ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
            </div>
	</c:if>

	<form name='f' action="<c:url value='j_spring_security_check' />"
            method='POST'>

            <table id="loginTable" class="formTable">
                <tr>
                    <th colspan="2">
                        <h2>Login</h2>
                    </th>
                </tr>
                <tr>
                    <td>User:</td>
                    <td><input type='text' name='j_username' class="textInput" />
                    </td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type='password' name='j_password' class="textInput" />
                    </td>
                </tr>
                <tr>
                    <td><input value="Reset" name="reset" type="reset" />
                    </td>
                    <td><input value="Login" name="submit" type="submit" />
                    </td>
                </tr>
            </table>

	</form>
    </div>                

    <%@include file="footer.jsp" %>

</body>
</html>
