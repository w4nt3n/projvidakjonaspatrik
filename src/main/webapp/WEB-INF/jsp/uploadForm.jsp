<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>  
<head>  
    <script type="text/javascript" src="jquery-1.2.6.min.js"></script>  
    <link href="<c:url value="/resources/css/generalStyle.css" />" type="text/css" rel="stylesheet">
    <link href="<c:url value="/resources/css/layout.css" />" type="text/css" rel="stylesheet">
    <title>CV Upload</title>  
</head>  
<body>  

    <div id="contentWrapper"> 

        <form:form method="post" enctype="multipart/form-data"  modelAttribute="uploadedFile" action="fileUpload.htm">  
            <table class="formTable">  
                <tr>
                    <th colspan="3">
                        <h2>CV Upload</h2>  
                    </th>
                </tr>
                <tr>
                    <td colspan="3">
                        Please select a file to upload
                    </td>
                </tr>
                <tr>  
                    <td>Upload File: </td>  
                    <td><input type="file" name="file" /></td>  
                    <td style="color: red; font-style: italic;">
                        <form:errors  path="file" />  
                    </td>  
                </tr>  
                <tr>  
                    <td colspan="3"><input type="submit" value="Upload" class="centerdInput"/></td>  
                </tr>  
            </table>  
        </form:form>  
        
    </div>  

</body>  
</html>  
