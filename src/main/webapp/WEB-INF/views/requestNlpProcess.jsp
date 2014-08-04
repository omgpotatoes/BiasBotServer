<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>BiasBotServer: request nlp processing</title>
</head>
<body>

<h2>text for processing:</h2>
<form:form method="POST" action="/bias_bot_server/executeNlpProcess">
   <table>
    <tr>
        <td><form:label path="rawText">raw text:</form:label></td>
        <td><form:input path="rawText" style="width:80%"/></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</body>
</html>