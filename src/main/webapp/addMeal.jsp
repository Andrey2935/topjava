<%--
  Created by IntelliJ IDEA.
  User: sasa
  Date: 15.02.2022
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add or edit meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Add or edit meal</h2>
<form method="post" action="meals" name="addMeal">
    ID : <input type="text" readonly="readonly" name="Id"
                     value="<c:out value="${mealTo.id}" />" /> <br />
    DateTime : <input type="datetime-local" name="dateTime"
                      value="<c:out value="${mealTo.dateTime}" />" /> <br />
    Description : <input type="text" name="description"
                         value="<c:out value="${mealTo.description}" />" /> <br />
    Calories : <input type="text" name="calories"
                      value="<c:out value="${mealTo.calories}" />" /> <br />
    <input type="submit" value="Submit" />
</form>
</body>
</html>
