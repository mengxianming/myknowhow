<%@page import="com.study.autoprodtool.common.Urls"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=utf-8" %>
<html>
<body>
	<div id="header">
		<h3>モジュール展開表</h3>
		<div>
			<label>Ver:</label> <label>${version}</label>
		</div>
		<div>${curUser.name }</div>
		<div>
			<button>ログアウト</button>
		</div>
	</div>

	<div>
		<ul class="nav nav-list">
			<li class="nav-header">個人メニュー</li>
			<li class="" style=""><a href="#">ユーザー情報</a>
			</li>

			<li class="nav-header">表示／入力</li>
			<li class="" style=""><a href="#">機能リスト</a>
			</li>
			<li class="" style=""><a href="#">製品リスト</a>
			</li>
			<li class="" style=""><a href="#">モジュールリスト</a>
			</li>
			<li class="" style=""><a href="#">マトリクスリスト（製品vsﾓｼﾞｭｰﾙ）</a>
			</li>
			<li class="" style=""><a href="#">マトリクスリスト（ﾓｼﾞｭｰﾙvs製品）</a>
			</li>

			<li class="nav-header">リスト表示／権限表示</li>
			<li class="" style=""><a href="#">リスト表示</a>
			</li>
			<li class="" style=""><a href="#">権限表示</a>
			</li>

			<li class="nav-header">マスター管理</li>
			<li class="" style=""><a href="#">マスターデータ</a>
			</li>

			<li class="nav-header">システム管理</li>
			
			<li class="" style=""><a href="<c:url value='<%=Urls.USER_LIST%>' />" >ユーザ管理</a>
			
			</li>

		</ul>

	</div>


</body>
</html>
