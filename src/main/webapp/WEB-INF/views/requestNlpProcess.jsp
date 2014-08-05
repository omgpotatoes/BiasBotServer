<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <link type="text/css" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet" />
    <title>BiasBotServer | super-ugly request NLP processing form</title>
</head>
<body>

<h2>super-ugly raw text input field:</h2>
<form:form method="POST" action="executeNlpProcess">
   <table>
    <tr>
        <td><form:textarea path="rawText" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Process Me Through NLP!"/>
        </td>
    </tr>
</table>  
</form:form>
</body>
</html>