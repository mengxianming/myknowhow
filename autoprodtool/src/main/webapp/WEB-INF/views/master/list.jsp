<%@page import="com.study.autoprodtool.common.Urls"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>マスタデータ管理</h1>


<div>
	<div id="jqgrid-division-list" style="margin: 10px 20px;">		
		<table id="division-list"></table>
		<div id="division-pager"></div>
	</div>	
</div>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/lib/jquery/jquery-ui/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/lib/jquery/jqGrid/css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/lib/jquery/paginator/css/paginator.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/jquery-ui/jquery-ui.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/jqGrid/i18n/grid.locale-ja.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/jqGrid/jquery.jqGrid.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/paginator/jquery.paginator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/common.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/master/list-app.js" ></script>

<script type="text/javascript">
    var urls = {
	    division : {
		    list : '<c:url value="<%=Urls.DIVISION_LIST  %>"></c:url>',
		    update : '<c:url value="<%=Urls.DIVISION_UPDATE  %>"></c:url>',
		    create : '<c:url value="<%=Urls.DIVISION_CREATE  %>"></c:url>',
		    del : '<c:url value="<%=Urls.DIVISION_DELETE  %>"></c:url>'
	    }
	   
    };
</script>
