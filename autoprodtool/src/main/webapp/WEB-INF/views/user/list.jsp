<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
	<div id="jqgrid-user-list">
		<h3>ユーザー企業を選択してください。</h3>
		<table id="user-list"></table>
		<div id="pager"></div>
	</div>
	<div style="text-align: center; width: 100%; margin: 0 auto;">
		<div id="paginator"></div>
	</div>
</div>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/lib/jquery/jqGrid/css/ui.jqgrid.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/jqGrid/jquery.jqGrid.js" />

<script type="text/javascript">
    var listUrl = "${pageContext.request.contextPath}/user/list";
</script>
