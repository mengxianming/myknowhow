<%@ page language="java" isErrorPage="true"
	contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${model.name}详细</title>
</head>
<body>
	<h1>${model.name} Info:</h1>
	<c:forEach items="${model.dataMap }" var="data">
		<label id="${data.key }">${data.key}:</label>
		<label>${data.value}</label>
		<br>
	</c:forEach>
</body>
</html>