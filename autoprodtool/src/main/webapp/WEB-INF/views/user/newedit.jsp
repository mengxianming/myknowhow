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
		<input type="hidden" name="id" value="${user.id }"/>
		<table class="table">
			<tbody>
				<tr>
					<th colspan="2">ユーザID</th>
					<td><input name="loginName" value="${user.loginName}"></td>
				</tr>
				<tr>
					<th colspan="2">ユーザ識別</th>
					<td>
					    <input type="hidden" id="roleId" value="${user.roleId }"/>
					    <select id="select-role" name="roleId">					    
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
					<td>
					<input type="hidden" id="companyId" value="${user.companyId }"/>
					<select id="select-company" name="companyId">					
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
					<td><input type="hidden" id="divisionId" value="${user.divisionId }" />
					 <select id="select-division" name="divisionId"></select></td>
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
						<div id="sumaryMailFlag-radios">
						<input type="hidden" id="sumaryMailFlag" value="${user.sumaryMailFlag }" />						    
							<input type="radio" name="sumaryMailFlag" id="sumaryMailFlagOn" value="0"> <label for="sumaryMailFlagOn">必要</label>
							<input type="radio" name="sumaryMailFlag" id="sumaryMailFlagOff" value="1" > <label
								for="sumaryMailFlagOff">不要</label>
						</div>
					</td>

				</tr>
				<tr>
					<th colspan="2">文書回覧メール</th>
					<td>
						<div id="articleMailFlag-radios">
						<input type="hidden" id="articleMailFlag" value="${user.articleMailFlag }" />		
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
						<div id="status-radios">
						<input type="hidden" id="status" value="${user.status }" />	
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

$(function(){
    var roleList = util.getAjaxData('<c:url value="<%=Urls.ROLE_LIST%>"/>');
    util.fillOptionList("#select-role", roleList, "id", "name", $('#roleId').val());
    
    var companyList = util.getAjaxData('<c:url value="<%=Urls.COMPANY_LIST%>"/>');
    util.fillOptionList("#select-company", companyList, "id", "name");
    
    var divisionList = null;
    var userDivision = null;
    $("#select-company").change(function(){
	 	divisionList = $("#select-company").val() ?
		    util.getAjaxData('<c:url value="<%=Urls.DIVISION_LIST%>"/>', {'filter.companyId' : $("#select-company").val()})
		    : [];
		
		userDivision= util.filterListUnique(divisionList, function(elem){return elem.id == $("#divisionId").val();});
	    
		var pparentList = util.projectList(divisionList, 'pparent', true);
	 	util.fillOptionList("#select-division3", pparentList, null, null); 	
	 	//trigger event
		$("#select-division3").change();
    });
    $("#select-division3").change(function(){		
	   var filterdList = $("#select-division3").val() ? 
		   					util.filterList(divisionList, function(elem){return elem.pparent == $("#select-division3").val();})
		   					: [];
	    var parentList = util.projectList(filterdList, 'parent', true);
	 	util.fillOptionList("#select-division2", parentList, null, null);
	 	//trigger event
		$("#select-division2").change();
	});
    $("#select-division2").change(function(){
	   var filterdList = $("#select-division2").val() ?
		   util.filterList(divisionList, function(elem){
	       return elem.pparent == $("#select-division3").val() 
	 				&& elem.parent == $("#select-division2").val();
	    }) : [];   
	 	util.fillOptionList("#select-division", filterdList, "id", "name");
	});
    
    //trigger events to reflect model value to ui
    $("#select-company").val($('#companyId').val());
    $("#select-company").change();
    $("#select-division3").val(userDivision ? userDivision.pparent : '' );
    //$("#select-division3").change();
	$("#select-division2").val(userDivision ? userDivision.parent : '' );
	//$("#select-division2").change();
	$("#select-division").val(userDivision ? userDivision.id : '' );
	
	//bind radio group
	util.bindRadioButtons("#sumaryMailFlag-radios", $("#sumaryMailFlag").val());
	util.bindRadioButtons("#articleMailFlag-radios", $("#articleMailFlag").val());
	util.bindRadioButtons("#status-radios", $("#status").val());
   
});
</script>

