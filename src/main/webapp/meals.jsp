<%--
  Created by IntelliJ IDEA.
  User: sasa
  Date: 09.02.2022
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
  <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<a href="addMeal.jsp">Add meal</a>
<table border="1">
  <tr>
      <th>id</th>
    <th>dateTime</th>
    <th>description</th>
    <th>calories</th>
      <th colspan=2>action</th>
  </tr>
  <c:forEach var="mealTo" items="${mealTo}">
      <c:if test="${mealTo.excess eq true}">
          <tr style="color: red"
      </c:if>
      <c:if test="${mealTo.excess eq false}">
          <tr style="color: green"
      </c:if>
      <tr>
          <td><c:out value="${mealTo.id}"/></td>
    <td><c:out value="${f:formatLocalDateTime(mealTo.dateTime, 'yyyy.MM.dd hh:mm')}"/></td>
          <td><c:out value="${mealTo.description}"/></td>
          <td><c:out value="${mealTo.calories}"/></td>
          <td><a href="meals?action=edit&Id=<c:out value="${mealTo.id}"/>">edit1</a></td>
          <td><a href="meals?action=delete&Id=<c:out value="${mealTo.id}"/>">delete1</a></td>
      </tr>
  </c:forEach>
</table>
</body>
</html>
