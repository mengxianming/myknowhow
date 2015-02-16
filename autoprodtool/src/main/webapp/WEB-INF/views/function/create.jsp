<%@page import="com.study.autoprodtool.common.Urls"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div>
	<jsp:include page="newedit.jsp"/>	
</div>

<script type="text/javascript">
var urls = {
	    list : '<c:url value="<%=Urls.FUNCTION_LIST  %>"></c:url>',
	    update : '<c:url value="<%=Urls.FUNCTION_UPDATE  %>"></c:url>',
	    create : '<c:url value="<%=Urls.FUNCTION_CREATE  %>"></c:url>',
	    categoryBigList : '<c:url value="<%=Urls.CATEGORYBIG_LIST  %>"></c:url>',
	    categoryMidList : '<c:url value="<%=Urls.CATEGORYMID_LIST  %>"></c:url>',
	    categorySmallList : '<c:url value="<%=Urls.CATEGORYSMALL_LIST  %>"></c:url>',
};


/**
 * define company table
 */
$(function() {
    // テーブル定義
    var functionGrid = null;
    functionGrid = new JqGridWrapper("function-list", false, {
	url : urls.list,
	mtype : "POST",
	datatype : "json",
	height : 300,
	width : 280,
	// autowidth : true,
	// shrinkToFit : true,
	altRows : true,
	sortable : false,
	sortname : "id",
	colModel : [ {
	    label : '機能ID',
	    name : 'id'
	}, {
	    label : '並び',
	    name : 'order',
	    editable : true
	}, {
	    label : '大分類',
	    name : 'categoryBigId',
	    editable : true,
	    edittype : 'custom',
	    editoptions : util.createCustomSelectEditoptions(urls.categoryBigList, "id", "name"),
	    jsonmap : "categoryBigName"
	}, {
	    label : '中分類',
	    name : 'categoryMidId',
	    editable : true,
	    edittype : 'custom',	    
	    editoptions : util.createCustomSelectEditoptions(urls.categoryMidList, "id", "name"),
	    jsonmap : "categoryMidName"
	}, {
	    label : '小分類',
	    name : 'categorySmallId',
	    editable : true,
	    edittype : 'custom',
	    editoptions : util.createCustomSelectEditoptions(urls.categorySmallList, "id", "name"),
	    jsonmap : "categorySmallName"
	}, {
	    label : '説明',
	    name : 'description',
	    editable : true
	} ]

    });
      
  
    $("#btn-newedit").click(function(){
	    functionGrid.grid.saveRow("0", {
		url : urls.create,
		successfunc : function(response) {
		    if(response.responseJSON && response.responseJSON.code == 'S00'){
			 util.alertDialog("メッセージ", "機能を作成しました。");
			    functionGrid.reloadTable({
				   'filter.id' : response.responseJSON.data
			    });
		    }
		   
		}
	    });
	});

	// new record
	functionGrid.grid.addRow({
	    rowID : '0'
	});
    });
</script>