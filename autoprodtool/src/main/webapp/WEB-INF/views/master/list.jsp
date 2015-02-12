<%@page import="com.study.autoprodtool.common.Urls"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>マスタデータ管理</h1>


<div style="margin: 0 10px; overflow: auto;">
	<div class="grid-row">
		<div id="jqgrid-division-list" class="master-grid">
			<table id="division-list"></table>
			<div id="division-pager"></div>
		</div>

		<div id="jqgrid-company-list" class="master-grid">
			<table id="company-list"></table>
			<div id="company-pager"></div>
		</div>

		<div id="jqgrid-role-list" class="master-grid">
			<table id="role-list"></table>
			<div id="role-pager"></div>
		</div>
	</div>

</div>

<style>
<!--
.grid-row{
width: 1000px;
overflow: hidden;
}

.master-grid{
margin: 10px 10px;
float : left;
}

-->
</style>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/lib/jquery/jquery-ui/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/lib/jquery/jqGrid/css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/lib/jquery/paginator/css/paginator.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/jquery-ui/jquery-ui.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/jqGrid/i18n/grid.locale-ja.custom.js" ></script>
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
	    },
	    company : {
		    list : '<c:url value="<%=Urls.COMPANY_LIST  %>"></c:url>',
		    update : '<c:url value="<%=Urls.COMPANY_UPDATE  %>"></c:url>',
		    create : '<c:url value="<%=Urls.COMPANY_CREATE  %>"></c:url>',
		    del : '<c:url value="<%=Urls.COMPANY_DELETE  %>"></c:url>'
	    },
	    role : {
		    list : '<c:url value="<%=Urls.ROLE_LIST  %>"></c:url>',
		    update : '<c:url value="<%=Urls.ROLE_UPDATE  %>"></c:url>',
		    create : '<c:url value="<%=Urls.ROLE_CREATE  %>"></c:url>',
		    del : '<c:url value="<%=Urls.ROLE_DELETE  %>"></c:url>'
	    }
	   
    };
</script>
