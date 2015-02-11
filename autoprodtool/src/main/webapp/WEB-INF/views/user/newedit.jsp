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
	<h1>ユーザー情報</h1>
	<form id="form">

		<table class="table">
			<tbody>
				<tr>
					<th colspan="2">ユーザID</th>
					<td><input name="loginName" value="${user.loginName}"></td>
				</tr>
				<tr>
					<th colspan="2">ユーザ識別</th>
					<td><select id="select-role" name="roleId">
					    <c:forEach items="roles" var="item">					    	
					    	<c:set var="selectedStr">${item.id == user.roleId ? 'selected="selected"' : ''}</c:set>
					        <option value="${item.id}" ${selectedStr}>${item.name }</option>
					    </c:forEach>
					    </select>
					</td>
				</tr>
				<tr class="blank-row">
					<td colspan="3"></td>
				</tr>
				<tr>
					<th rowspan="4">氏名</th>
					<th>日本語</th>
					<td><input name="name" value="${user.name }"></td>
				</tr>
				<tr>
					<th>カナ半角</th>
					<td><input name="nameKana" value="${user.nameKana}"></td>
				</tr>
				<tr>
					<th>英語</th>
					<td><input name="nameEnglish" value="${user.nameEnglish }"></td>
				</tr>
				<tr>
					<th>ニックネーム</th>
					<td><input name="nickname" value="${user.nickname }"></td>
				</tr>
				<tr class="blank-row">
					<td colspan="3"></td>
				</tr>
				<tr>
					<th colspan="2">会社</th>
					<td><select id="select-company" name="companyId">
					<c:forEach items="companies" var="item">					    	
					    	<c:set var="selectedStr">${item.id == user.companyId ? 'selected="selected"' : ''}</c:set>
					        <option value="${item.id}" ${selectedStr}>${item.name }</option>
					    </c:forEach>
					</select></td>
				</tr>
				<tr>
					<th rowspan="3">所属</th>
					<th>部</th>
					<td><select id="select-division3">					
					</select></td>
				</tr>
				<tr>
					<th>課</th>
					<td><select id="select-division2"></select></td>
				</tr>
				<tr>
					<th>グループ</th>
					<td><input type="hidden" id="divisonId" value="${user.divisonId }" />
					 <select id="select-division" name="divisonId"></select></td>
				</tr>
				<tr class="blank-row">
					<td colspan="3"></td>
				</tr>
				<tr>
					<th colspan="2">インターネットメールアドレス</th>
					<td><input name="email" value="${user.email }"><label>@126.com</label>
					</td>
				</tr>
				<tr>
					<th colspan="2">サマリメール</th>
					<td>
						<div id="sumaryMailFlag" value="${user.sumaryMailFlag }">						    
							<input type="radio" name="sumaryMailFlag" id="sumaryMailFlagOn" value="0"> <label for="sumaryMailFlagOn">必要</label>
							<input type="radio" name="sumaryMailFlag" id="sumaryMailFlagOff" value="1" checked="checked"> <label
								for="sumaryMailFlagOff">不要</label>
						</div>
					</td>

				</tr>
				<tr>
					<th colspan="2">文書回覧メール</th>
					<td>
						<div id="articleMailFlag" value="${user.articleMailFlag }">
							<input type="radio" name="articleMailFlag" id="articleMailFlagOn" value="0"> <label
								for="articleMailFlagOn">必要</label> <input type="radio" name="articleMailFlag" id="articleMailFlagOff" value="1">
							<label for="articleMailFlagOff">不要</label>
						</div>
					</td>
				</tr>

				<tr class="blank-row">
					<td colspan="3"></td>
				</tr>
				<tr>
					<th colspan="2">代理権者</th>
					<td>
						<button>選択</button> <input type="hidden" name="agentId" value="${user.agentId}">
						 <input name="agentName" value="${user.agentName }">
					</td>
				</tr>
				<tr class="blank-row">
					<td colspan="3"></td>
				</tr>
				<tr>
					<th colspan="2">作成日<br>(YYYY-MM-DD HH:MM:SS)</th>
					<td><fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<tr>
					<th colspan="2">最終ログイン<br>(YYYY-MM-DD HH:MM:SS)</th>
					<td><fmt:formatDate value="${user.lastLogin}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<tr>
					<th colspan="2">ユーザ情報最終更新日<br>(YYYY-MM-DD HH:MM:SS)</th>
					<td><fmt:formatDate value="${user.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<tr class="blank-row">
					<td colspan="3"></td>
				</tr>
				<tr>
					<th colspan="2">ステータス</th>
					<td>
						<div id="status" value="${user.status }">
							<input type="radio" name="status" id="statusValid" value="0"> <label for="statusValid">Valid</label> <input
								type="radio" name="status" id="statusInvalidValid" value="1"> <label for="statusInvalidValid">Invalid</label>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		<div>
			<input type="submit" value="新規"></input>
		</div>
	</form>

</div>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/lib/jquery/jquery-ui/css/jquery-ui.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/common.js"></script>
<script type="text/javascript">	
    var divisonUrl = '<c:url value="<%=Urls.DIVISION_LIST  %>"></c:url>',
	$(function(){
	    //get divison info
	    divisions = util.getAjaxData()
	    $("#form").on("submit", function(evt){
		    evt.preventDefault();
		    $("#form").ajaxSubmit({
			url : urls.create,
			type : 'POST',
			dataType : 'json',
			success : function(data){
			    if(data && data.code == 'S00'){
				onSuccess(data.data);
					
			    }
			}
	    });
		});
	    
	    $("#btn-ret").click(function(){
		window.location.href = urls.list;
	    });
	});    
</script>