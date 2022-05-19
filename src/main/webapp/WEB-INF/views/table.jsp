<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<h1>Список мероприятий:</h1>
<table border="1px">
    <c:forEach var="opera" items="${operas}">
        <tr>
            <td width="40%">
                    ${opera.eventName}
            </td>
            <td width="60%">
                    ${opera.eventInfo}
            </td>
        </tr>
    </c:forEach>

</table>
</body>
</html>