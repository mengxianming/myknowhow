<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html">
<html>
<body>
	<h1>ユーザー情報</h1>
	<table>
		<tbody>
			<tr>
				<td>ユーザID</td>
				<td>${user.loginName}</td>
			</tr>
			<tr>
				<td>ユーザ識別</td>
				<td>${user.role}</td>
			</tr>
			<tr>
				<td>ムービーユーザ識別</td>
				<td>${user.movieFlag == true ? 'ムービー利用可' : 'ムービー利用不可'}</td>
			</tr>
			<tr>
				<td>Successユーザ識別</td>
				<td>${user.successRole}</td>
			</tr>
			<tr class="blank_row"/>
			<tr>
				<td rowspan="4">氏名</td>
				<td>日本語</td>
				<td>${user.name}</td>
			</tr>
			<tr>
				<td></td>
				<td>カナ半角</td>
				<td>${user.nameKana}</td>
			</tr>
			<tr>
				<td></td>
				<td>英語</td>
				<td>${user.nameEnglish}</td>
			</tr>
			<tr>
				<td></td>
				<td>ニックネーム</td>
				<td>${user.nickname}</td>
			</tr>
			<tr class="blank_row"/>
			<tr>
				<td>会社</td>
				<td>${user.company}</td>
			</tr>
			<tr>
				<td>所属</td>
				<td>部</td>
				<td>${user.division.level1}</td>
			</tr>
			<tr>
				<td>所属</td>
				<td>課</td>
				<td>${user.division.level2}</td>
			</tr>
			<tr>
				<td>所属</td>
				<td>グループ</td>
				<td>${user.division.level3}</td>
			</tr>
			<tr class="blank_row"/>
			<tr>
				<td>インターネットメールアドレス</td>
				<td>${user.email}</td>
			</tr>
			<tr>
				<td>サマリメール</td>
				<td>${user.sumaryMailFlag == true ? '必要' : '不要'}</td>
			</tr>
			<tr>
				<td>文書回覧メール</td>
				<td>${user.articleMailFlag == true ? '必要' : '不要'}</td>
			</tr>
			
			<tr class="blank_row"/>
			<tr>
				<td>代理権者</td>
				<td>${user.agent}</td>
			</tr>
			<tr class="blank_row"/>
			<tr>
				<td>作成日<br>(YYYY-MM-DD HH:MM:SS)</td>
				<td><fmt:formatDate value="${user.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr>
				<td>最終ログイン<br>(YYYY-MM-DD HH:MM:SS)</td>
				<td><fmt:formatDate value="${user.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr>
				<td>ユーザ情報最終更新日<br>(YYYY-MM-DD HH:MM:SS)</td>
				<td><fmt:formatDate value="${user.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr class="blank_row"/>
			<tr>
				<td>ステータス</td>
				<td>${user.status == 0 ? 'Valid' : 'Invalid'}</td>
			</tr>
		</tbody>
	</table>
</body>
</html>