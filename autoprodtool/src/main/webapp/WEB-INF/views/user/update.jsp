<%@page import="com.study.autoprodtool.common.Urls"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div>
<jsp:include page="newedit.jsp"/>

<div id="ok-dialog" title="メッセージ">ユーザーを編集しました。</div>
</div>

<script type="text/javascript">
var urls = {
	    list : '<c:url value="<%=Urls.USER_LIST  %>"></c:url>',
	    update : '<c:url value="<%=Urls.USER_UPDATE  %>"></c:url>',
	    create : '<c:url value="<%=Urls.USER_CREATE  %>"></c:url>'
};

	function onSuccess(newId){
	    $("#ok-dialog").dialog({
		autoOpen : true,
		resizable : false,
		minWidth : 350,
		width : 400,
		modal : true,
		buttons : [{
		    text : "戻る" ,
		    click : function(){
			$(this).dialog("close"); 
		    }
		}]
	});
	};
	
	$(function(){
	    //init dialog
	    $("#ok-dialog").dialog({autoOpen : false});
	    
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