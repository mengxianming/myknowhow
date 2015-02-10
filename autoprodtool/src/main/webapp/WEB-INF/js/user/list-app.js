/*
 * 企業選択JS処理 
 */
$(function() {    
    // テーブル定義
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
//	    if (data.pageSize) {
//		gridParam.rowNum = data.pageSize;
//		gridParam.records = data.totalRecords;
//		gridParam.page = data.currentPage;
//	    }
//	    companyGrid.jqGrid('setGridParam', gridParam);
//
//	    if (data.pageSize) {
//		util.paginator($("#paginator"), gridParam.records, gridParam.rowNum,
//			gridParam.page, 9, function(newPage) {
//		    companyGrid.setGridParam({
//			page : newPage
//		    });
//		    reloadTable();
//		});
//	    }	    
	  
	}
	
    });

    //register button handlers
    $("#btn-new").click(function(){
	window.location.href=urls.create;
    });
    $("#btn-edit").click(function(){
	window.location.href=urls.update + "/" + jqGridWrapper.getSelectedRowData().id;
    });
    $("#btn-del").click(function(){
	window.location.href=urls.del + "/" + jqGridWrapper.getSelectedRowData().id;
    });
    
    //show data list
    jqGridWrapper.reloadTable();

});
