<%@page import="com.study.autoprodtool.common.Urls"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>ユーザーリスト</h1>

<div>
<button id="btn-new">新規</button>
<button id="btn-update">編集</button>
<button id="btn-detail">詳細</button>
<button id="btn-del">選択削除</button>
</div>
<div>
	<div id="jqgrid-user-list" style="margin: 10px 20px;">		
		<table id="user-list"></table>
		<div id="pager"></div>
	</div>
	<div style="text-align: center; width: 100%; margin: 0 auto;">
		<div id="paginator"></div>
	</div>
</div>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/lib/jquery/jquery-ui/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/lib/jquery/jqGrid/css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/lib/jquery/paginator/css/paginator.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/jquery-ui/jquery-ui.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/jqGrid/i18n/grid.locale-ja.custom.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/jqGrid/jquery.jqGrid.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/paginator/jquery.paginator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/common.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user/list-app.js" ></script>

<script type="text/javascript">
    var urls = {
	    list : '<c:url value="<%=Urls.USER_LIST  %>"></c:url>',
	    detail : '<c:url value="<%=Urls.USER_DETAIL  %>"></c:url>',
	    update : '<c:url value="<%=Urls.USER_UPDATE  %>"></c:url>',
	    create : '<c:url value="<%=Urls.USER_CREATE  %>"></c:url>',
	    del : '<c:url value="<%=Urls.USER_DELETE  %>"></c:url>'
    };
</script>
