<%@page trimDirectiveWhitespaces="true"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>モジュール展開表</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/jquery.js"></script>
</head>
<body>
	<div id="container">
		<div id="layout-left" style="float:left; width:300px;">
			<div id="left-container" style="border: 2px solid;">
				<tiles:insertAttribute name="left" />
			</div>

		</div>
		<div id="layout-main" style="padding-left: 320px;">
			<div id="right-container" style="border: 2px solid; text-align: center;padding: 10px 0 20px;">
				<tiles:insertAttribute name="main" />
			</div>

		</div>
	</div>
</body>

</html>