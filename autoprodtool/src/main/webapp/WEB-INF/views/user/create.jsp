<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html">
<html>
<body>
	<div style="float: right; padding: 10px 20px;">
		<button>ヘルプ</button>
		<br> <br>
		<button>戻る</button>
	</div>
	<h1>ユーザー情報</h1>
	<table class="table">
		<tbody>
			<tr>
				<th colspan="2">ユーザID</th>
				<td><input name="loginName" /></td>
			</tr>
			<tr>
				<th colspan="2">ユーザ識別</th>
				<td><select id="select-role" name="roleId"></select>
				</td>
			</tr>
			<tr class="blank-row">
				<td colspan="3"></td>
			</tr>
			<tr>
				<th rowspan="4">氏名</th>
				<th>日本語</th>
				<td><input name="name" /></td>
			</tr>
			<tr>
				<th>カナ半角</th>
				<td><input name="nameKana" /></td>
			</tr>
			<tr>
				<th>英語</th>
				<td>${user.nameEnglish}</td>
			</tr>
			<tr>
				<th>ニックネーム</th>
				<td><input name="nickname" /></td>
			</tr>
			<tr class="blank-row">
				<td colspan="3"></td>
			</tr>
			<tr>
				<th colspan="2">会社</th>
				<td><select id="select-company" name="companyId"></select></td>
			</tr>
			<tr>
				<th rowspan="3">所属</th>
				<th>部</th>
				<td><select id="select-division3"></select></td>
			</tr>
			<tr>
				<th>課</th>
				<td><select id="select-division2"></select></td>
			</tr>
			<tr>
				<th>グループ</th>
				<td><select id="select-division" name="divisonId"></select></td>
			</tr>
			<tr class="blank-row">
				<td colspan="3"></td>
			</tr>
			<tr>
				<th colspan="2">インターネットメールアドレス</th>
				<td><input name="email" /><label>@126.com</label>
				</td>
			</tr>
			<tr>
				<th colspan="2">サマリメール</th>
				<td>
					<div id="radio-group">
						<input type="radio" name="sumaryMailFlag" id="sumaryMailFlagOn" value="0" /> <label for="sumaryMailFlagOn">必要</label>
						<input type="radio" name="sumaryMailFlag" id="sumaryMailFlagOff" value="1" /> <label for="sumaryMailFlagOff">不要</label>
					</div>
				</td>

			</tr>
			<tr>
				<th colspan="2">文書回覧メール</th>
				<td>
					<div id="radio-group">
						<input type="radio" name="articleMailFlag" id="articleMailFlagOn" value="0" /> <label for="articleMailFlagOn">必要</label>
						<input type="radio" name="articleMailFlag" id="articleMailFlagOff" value="1" /> <label for="articleMailFlagOff">不要</label>
					</div>
				</td>
			</tr>

			<tr class="blank-row">
				<td colspan="3"></td>
			</tr>
			<tr>
				<th colspan="2">代理権者</th>
				<td>
					<button>選択</button> <input type="hidden" name="agentId" /> <input name="agentName" />
				</td>
			</tr>
			<tr class="blank-row">
				<td colspan="3"></td>
			</tr>
			<tr>
				<th colspan="2">作成日<br>(YYYY-MM-DD HH:MM:SS)</th>
				<td></td>
			</tr>
			<tr>
				<th colspan="2">最終ログイン<br>(YYYY-MM-DD HH:MM:SS)</th>
				<td></td>
			</tr>
			<tr>
				<th colspan="2">ユーザ情報最終更新日<br>(YYYY-MM-DD HH:MM:SS)</th>
				<td></td>
			</tr>
			<tr class="blank-row">
				<td colspan="3"></td>
			</tr>
			<tr>
				<th colspan="2">ステータス</th>
				<td>
					<div id="radio-group">
						<input type="radio" name="status" id="statusValid" value="0" /> <label for="statusValid">Valid</label> <input
							type="radio" name="status" id="statusInvalidValid" value="1" /> <label for="statusInvalidValid">Invalid</label>
					</div>
				</td>
			</tr>
		</tbody>
	</table>

	<div>
		<button>編集</button>
	</div>
</body>

</html>