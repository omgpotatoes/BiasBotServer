<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <link type="text/css" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet" />
    <title>BiasBotServer | super-ugly NLP output form</title>
</head>
<body>

<h2>super-ugly NLP output:</h2>
   <table>
    <tr>
        <td>${nlpProcessingResult}</td>
    </tr>
</table>

<h2>slightly-less-ugly sentiment-highlighted NLP output:</h2>
   <table>
    <tr>
        <td>${nlpProcessingResultPretty}</td>
    </tr>
</table>

</body>
</html>