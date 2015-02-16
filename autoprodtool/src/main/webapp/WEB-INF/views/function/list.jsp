<%@page import="com.study.autoprodtool.common.Urls"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
	<h1>機能リスト</h1>
	<fieldset>
		<legend>検索条件</legend>
		<form id="filter-form">
			<div style="overflow: hidden; margin: 10px auto;">
				<div class="filter-cell">
					<h4>機能ID</h4>
					<div>
						<input type="hidden" name="filterNames" value="id" /> <select id="select-id" name="filter.id"></select>
					</div>
				</div>
				<div class="filter-cell">
					<h4>大分類</h4>
					<div>
						<input type="hidden" name="filterNames" value="categoryBigName" /> <select id="select-categoryBigName"
							name="filter.categoryBigName"></select>
					</div>
				</div>
				<div class="filter-cell">
					<h4>中分類</h4>
					<div>
						<input type="hidden" name="filterNames" value="categoryMidName" /> <select id="select-categoryMidName"
							name="filter.categoryMidName"></select>
					</div>
				</div>
				<div class="filter-cell">
					<h4>小分類</h4>
					<div>
						<input type="hidden" name="filterNames" value="categorySmallName" /> <select id="select-categorySmallName"
							name="filter.categorySmallName"></select>
					</div>
				</div>
			</div>

		</form>
	</fieldset>

	<div>

		<button id="btn-new">新規登録</button>
		<button id="btn-update">選択したデータを変更</button>
		<button id="btn-detail">選択したデータの履歴</button>
		<button id="btn-del">削除済みデータ</button>
	</div>
	<div>
		<div id="jqgrid-function-list" style="margin: 10px 20px;">
			<table id="function-list"></table>
			<div id="pager"></div>
		</div>
		<div style="text-align: center; width: 100%; margin: 0 auto;">
			<div id="paginator"></div>
		</div>
	</div>
</div>

<style>
<!--
.filter-cell {
	display: inline-block;
	width :   150px;
}
-->
</style>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/lib/jquery/jquery-ui/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/lib/jquery/jqGrid/css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/lib/jquery/paginator/css/paginator.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/lib/jquery/jqGrid/i18n/grid.locale-ja.custom.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/jqGrid/jquery.jqGrid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/paginator/jquery.paginator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/function/list-app.js"></script>

<script type="text/javascript">
    var urls = {	    
	    list : '<c:url value="<%=Urls.FUNCTION_LIST%>"></c:url>',
	    detail : '<c:url value="<%=Urls.FUNCTION_DETAIL%>"></c:url>',
	    update : '<c:url value="<%=Urls.FUNCTION_UPDATE%>"></c:url>',
	    create : '<c:url value="<%=Urls.FUNCTION_CREATE%>"></c:url>',
	    del : '<c:url value="<%=Urls.FUNCTION_DELETE%>"></c:url>',
	    filterList : '<c:url value="<%=Urls.FUNCTION_FILTER_LIST%>"></c:url>'
    };   
   
</script>
