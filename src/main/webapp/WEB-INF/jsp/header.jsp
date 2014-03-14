<%-- 
    Document   : header
    Created on : Mar 13, 2014, 7:41:38 PM
    Author     : Vidak
--%>

<div id="headerDiv">
    <div class="textCenterDiv">
        <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
        <h3><spring:message code="label.title"/></h3>

        <span style="float: right">
            <a href="?lang=en"><img src="resources/images/england.png" border="0"></a> 
            | 
            <a href="?lang=sv_SE"><img src="resources/images/sweden.png" border="0"></a>
            | 
            <a href="?lang=es_ES"><img src="resources/images/spain.png" border="0"></a>
            | 
            <a href="?lang=zh_CN"><img src="resources/images/china.png" border="0"></a>
        </span>
    </div>
</div>