<%@ page language="java" isErrorPage="true"
	contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${listDetail.name}列表</title>
</head>
<body>
	<h1>${listDetail.name}列表:</h1>
	<table>
		<tr>
			<c:forEach items="${listDetail.headers }" var="hd">
				<th>${hd}</th>
			</c:forEach>
		</tr>
		<c:forEach items="${listDetail.datas }" var="data">
			<tr>
				<c:forEach items="${listDetail.headers }" var="hd">
					<td>${data[hd]}</td>
				</c:forEach>
			</tr>
		</c:forEach>

	</table>


</body>
</html>