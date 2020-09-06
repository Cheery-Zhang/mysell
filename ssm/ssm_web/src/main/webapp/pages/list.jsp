<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 78221
  Date: 2019/11/29
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>hh</h2>
<c:forEach items="${productList}" var="product">
    ${product.cityName }
    ${product.productName}
</c:forEach>
</body>
</html>
