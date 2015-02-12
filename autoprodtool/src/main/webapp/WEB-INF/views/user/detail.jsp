<%@page import="com.study.autoprodtool.common.Urls"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html">
<html>
<body>
    <div style="float:right; padding: 10px 20px;">
    <button>ヘルプ</button><br><br>
    <button id="btn-ret">戻る</button>
    </div>
	<h1>ユーザー情報</h1>	
	<table class="table">
		<tbody>			
			<tr>
				<th colspan="2">ユーザID</th>
				<td>${user.loginName}</td>
			</tr>
			<tr>
				<th colspan="2">ユーザ識別</th>
				<td>${user.roleName}</td>
			</tr>
			
			<tr class="blank-row"><td colspan="3"></td></tr>
			<tr>
				<th rowspan="4">氏名</th>
				<th>日本語</th>
				<td>${user.name}</td>
			</tr>
			<tr>				
				<th>カナ半角</th>
				<td>${user.nameKana}</td>
			</tr>
			<tr>
				<th>英語</th>
				<td>${user.nameEnglish}</td>
			</tr>
			<tr>
				<th>ニックネーム</th>
				<td>${user.nickname}</td>
			</tr>
			<tr class="blank-row"><td colspan="3"></td></tr>
			<tr>
				<th colspan="2">会社</th>
				<td>${user.companyName}</td>
			</tr>
			<tr>
				<th rowspan="3">所属</th>
				<th>部</th>
				<td>${user.divisionPparent}</td>
			</tr>
			<tr>
				<th>課</th>
				<td>${user.divisionParent}</td>
			</tr>
			<tr>
				<th>グループ</th>
				<td>${user.divisionName}</td>
			</tr>
			<tr class="blank-row"><td colspan="3"></td></tr>
			<tr>
				<th colspan="2">インターネットメールアドレス</th>
				<td>${user.email}</td>
			</tr>
			<tr>
				<th colspan="2">サマリメール</th>
				<td>${user.sumaryMailFlag == 0 ? '必要' : '不要'}</td>
			</tr>
			<tr>
				<th colspan="2">文書回覧メール</th>
				<td>${user.articleMailFlag == 0 ? '必要' : '不要'}</td>
			</tr>
			
			<tr class="blank-row"><td colspan="3"></td></tr>
			<tr>
				<th colspan="2">代理権者</th>
				<td>${user.agentName}</td>
			</tr>
			<tr class="blank-row"><td colspan="3"></td></tr>
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
			<tr class="blank-row"><td colspan="3"></td></tr>
			<tr>
				<th colspan="2">ステータス</th>
				<td>${user.status == 0 ? 'Valid' : 'Invalid'}</td>
			</tr>
		</tbody>
	</table>
	
	<div><button id="btn-edit">編集</button> </div>
</body>

<script type="text/javascript"> 
$("#btn-ret").click(function(){
    window.location.href = '<c:url value="<%=Urls.USER_LIST %>"></c:url>';
});

$("#btn-edit").click(function(){
    window.location.href = '<c:url value="<%=Urls.USER_UPDATE %>"></c:url>' + '?id=' + '${user.id}';
});
</script>
</html>