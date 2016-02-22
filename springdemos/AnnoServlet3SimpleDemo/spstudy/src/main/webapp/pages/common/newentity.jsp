<%@ page language="java" isErrorPage="true"
	contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<c:set var="opt" value="${empty model.dataMap.get('id') ? '新建' : '更新'}"></c:set>
<title>${model.name} ${opt}</title>
</head>
<body>
	<h1>Input ${model.name} Info:</h1>
	<form action="${model.url}" method="post">
		<c:forEach items="${model.dataMap }" var="data">
			<label id="${data.key }">${data.key}</label>
			<input name="${data.key}" value="${data.value}"  ${data.key == 'id' ? 'disabled' : ''}/>
			<br>
		</c:forEach>
		<input type="submit" value="${opt }"/>
	</form>
</body>
</html>