<%@page import="com.study.autoprodtool.common.Urls"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div>
	<div style="float: right; padding: 10px 20px;">
		<button>ヘルプ</button>
		<br> <br>
		<button id="btn-ret">戻る</button>
	</div>
	<h1>機能リスト</h1>
	<fieldset>
		<legend>登録・編集・削除</legend>
		<fieldset>
			<legend>内容</legend>
			<div>
				<div id="jqgrid-function-list" style="margin: 10px 20px;">
					<table id="function-list"></table>
				</div>				
			</div>
		</fieldset>

		<fieldset>
			<legend>履歴</legend>
			<div>
				<div id="jqgrid-history-list" style="margin: 10px 20px;">
					<table id="history-list"></table>
				</div>				
			</div>
		</fieldset>

		<div>
			<button id="btn-newedit">${isEdit ? '変更' : '登録' }</button>
			<c:if test="${isEdit }">
				<button id="btn-del">削除</button>
			</c:if>
		</div>
	</fieldset>
</div>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/lib/jquery/jquery-ui/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/lib/jquery/jqGrid/css/ui.jqgrid.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/lib/jquery/jqGrid/i18n/grid.locale-ja.custom.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/jqGrid/jquery.jqGrid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/common.js"></script>

