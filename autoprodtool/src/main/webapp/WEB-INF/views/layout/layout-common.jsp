<%@page trimDirectiveWhitespaces="true"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>モジュール展開表</title>


</head>
<body>
	<div id="container">
		<div id="layout-left" class="layout-content">
			<tiles:insertAttribute name="left" />
		</div>
		<div id="layout-main" class="layout-content">
			<tiles:insertAttribute name="main" />
		</div>
	</div>


</body>
</html>