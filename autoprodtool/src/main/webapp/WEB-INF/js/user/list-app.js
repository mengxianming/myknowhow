/*
 * 企業選択JS処理 
 */
$(function() {    
    // テーブル定義
    var jqGridWrapper = null;
    jqGridWrapper = new JqGridWrapper("user-list", false, {
	url : urls.list,
	mtype : "POST",
	datatype: "json",
	jsonReader : {
	    root : "data",
	    page : "pager.page",
	    total : "pager.totalPages",
	    records : "pager.total",
	    cell : "",
	    id : "id"
	},
	height : "auto",
	width : $("#user-list").parent().width(),
//	autowidth : true,
	shrinkToFit : false,
	altRows : true,
	sortable : false,
	sortname : "id",
	colModel : [ {
	    label : 'ID',
	    name : 'id',
	    width : 60,
	    hidden : true,
	    sorttype : "int"
	}, {
	    label : 'ユーザーID',
	    name : 'loginName'
	}, {
	    label : 'ユーザー識別',
	    name : 'roleName'
	}, {
	    label : '氏名',
	    name : 'name'
	},  {
	    label : '会社',
	    name : 'companyName'
	},  {
	    label : '所属',
	    name : 'division',
	    jsonmap : function(obj) {
		return obj.divisionPParent + "-" + obj.divisionParent + "-" + obj.divisionName;
	    }
	},  {
	    label : 'インターネットメールアドレス',
	    name : 'email'
	},  {
	    label : 'サマリメール',
	    name : 'sumaryMailFlag',
	    formatter : function(cellVal){
		if(cellVal == undefined || cellVal==null || cellVal == ""){
		    return "";
		}
		return cellVal == 0 ? "必要" : "不要";
		
	    }
	},  {
	    label : '文書回覧メール',
	    name : 'articleMailFlag',
	    formatter : function(cellVal){
		if(cellVal == undefined || cellVal==null || cellVal == ""){
		    return "";
		}
		return cellVal == 0 ? "必要" : "不要";
		
	    }
	},  {
	    label : '代理権者',
	    name : 'agent'
	},  {
	    label : '最終ログイン',
	    name : 'lastLogin'
	}, {
	    label : 'ステータス',
	    name : 'status',
	    formatter : function(cellVal){
		if(cellVal == undefined || cellVal==null || cellVal == ""){
		    return "";
		}
		return cellVal == 0 ? "Valid" : "Invalid";
		
	    }
	}],
	beforeProcessing : function(data) {	   
	    var gridParam = {};
	    if (data.pager) {
		gridParam.rowNum = data.pager.limit;
		gridParam.records = data.pager.total;
		gridParam.page = data.pager.page;
	    }
	    jqGridWrapper.setGridParams(gridParam);

	    if (data.pager) {
		util.paginator($("#paginator"), gridParam.records, gridParam.rowNum,
			gridParam.page, 9, function(newPage) {
		    jqGridWrapper.setGridParam('page', newPage);
		    jqGridWrapper.reloadTable();
		});
	    }	    

	}
	
    });

    //register button handlers
    $("#btn-new").click(function(){
	window.location.href=urls.create;
    });
    $("#btn-detail").click(function(){
	if(!jqGridWrapper.getSelectedRowData()){
	    util.alertDialog("メッセージ", "ユーザーを選択してください。");
	    return;
	}
	window.location.href=urls.detail + "?id=" + jqGridWrapper.getSelectedRowData().id;
    });
    $("#btn-del").click(function(){
	if(!jqGridWrapper.getSelectedRowData()){
	    util.alertDialog("メッセージ", "ユーザーを選択してください。");
	    return;
	}
	
	util.confirmDialog('削除確認', "ユーザーを削除してよろしいですか？", function(){
	    $.ajax({
		url : urls.del,
		type : 'POST',
		dataType : 'json',
		data : {ids : jqGridWrapper.getSelectedRowData().id},
		success : function(data){
		    if(data && data.code == 'S00'){
			util.alertDialog("メッセージ", "ユーザーを削除しました。");
			jqGridWrapper.reloadTable();
			return ;
		    }
		    util.alertDialog("メッセージ", "ユーザー削除が失敗しました。");
		},
		error : function(status){
		    util.alertDialog("メッセージ", "ユーザー削除が失敗しました。");
		}
	    });
	});
	
    });
    
    //show data list
    jqGridWrapper.reloadTable();

});
