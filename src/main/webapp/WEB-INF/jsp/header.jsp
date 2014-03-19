<%-- 
    Document   : header
    Created on : Mar 13, 2014, 7:41:38 PM
    Author     : Vidak, Patrik
--%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="<c:url value="/resources/javascript/url_editing.js" />"></script>
<div id="headerDiv">
    <div class="textCenterDiv">
        <h3><spring:message code="label.title"/></h3>
        <span style="float: right">
            <img src="resources/images/england.png" onclick="addParam('lang', 'en')" alt="English" title="English" border="0" class="clickableImg">
            |
            <img src="resources/images/sweden.png" onclick="addParam('lang', 'sv_SE')" alt="Svenska" title="Svenska" border="0" class="clickableImg">
            |
            <img src="resources/images/spain.png" onclick="addParam('lang', 'es_ES')" alt="Español" title="Español" border="0" class="clickableImg">
            |
            <img src="resources/images/china.png" onclick="addParam('lang', 'zh_CN')" alt="中文" title="中文" border="0" class="clickableImg">
            
            <br>
            
            <sec:authorize access="isAuthenticated()">
                Logged in as: <sec:authentication property="principal.username" /> | 
                <a href='<c:url value="/j_spring_security_logout" />'>Logout</a>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <a href="applicationList.htm">Admin</a>
            </sec:authorize>  
        </span>
    </div>
</div>