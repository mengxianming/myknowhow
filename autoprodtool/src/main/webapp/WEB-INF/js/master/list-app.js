/*
 * 企業選択JS処理 
 */
$(function() {    
    // テーブル定義
    var divisionGrid = null;
    divisionGrid = new JqGridWrapper("division-list", false, {
	url : urls.division.list,
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
	height : 400,
	width : 300,
//	autowidth : true,
	shrinkToFit : false,
	altRows : true,
	sortable : false,
	sortname : "id",
	pager : "#division-pager",
	editurl : urls.division.update,
	viewrecords: true,
	colModel : [ {
	    label : 'ID',
	    name : 'id',
	    sorttype : 'int',
	    hidden : true
	}, {
	    label : '部',
	    name : 'pparent',
	    editable:true
	}, {
	    label : '課',
	    name : 'parent',
	    editable:true
	}, {
	    label : 'グループ',
	    name : 'name',
	    editable:true
	}],
	beforeProcessing : function(data) {	   
	    var gridParam = {};
	    if (data.pager) {
		gridParam.rowNum = data.pager.limit;
		gridParam.records = data.pager.total;
		gridParam.page = data.pager.page;
	    }
	    divisionGrid.setGridParams(gridParam);   

	}
	
    });
    divisionGrid.grid
    .navGrid('#division-pager',{edit:false,add:false,del:false,search:false})
    .inlineNav('#division-pager');
//    .navButtonAdd('#division-pager',{
//       caption:"新規", 
//       buttonicon:"ui-icon-add", 
//       onClickButton: function(){ 
//	   divisionGrid.grid.addRow("new-row", {
//	       position : 'last'
//	   });
//       }, 
//       position:"last"
//    })
//    .navButtonAdd('#pager',{
//       caption:"Del", 
//       buttonicon:"ui-icon-del", 
//       onClickButton: function(){ 
//          alert("Deleting Row");
//       }, 
//       position:"last"
//    });

    //register button handlers
//    $("#btn-new").click(function(){
//	window.location.href=urls.create;
//    });
//    $("#btn-detail").click(function(){
//	if(!jqGridWrapper.getSelectedRowData()){
//	    util.alertDialog("メッセージ", "ユーザーを選択してください。");
//	    return;
//	}
//	window.location.href=urls.detail + "/" + jqGridWrapper.getSelectedRowData().id;
//    });
//    $("#btn-del").click(function(){
//	if(!jqGridWrapper.getSelectedRowData()){
//	    util.alertDialog("メッセージ", "ユーザーを選択してください。");
//	    return;
//	}
//	
//	util.confirmDialog('削除確認', "ユーザーを削除してよろしいですか？", function(){
//	    $.ajax({
//		url : urls.del,
//		type : 'POST',
//		dataType : 'json',
//		data : {ids : jqGridWrapper.getSelectedRowData().id},
//		success : function(data){
//		    if(data && data.code == 'S00'){
//			util.alertDialog("メッセージ", "ユーザーを削除しました。");
//			jqGridWrapper.reloadTable();
//			return ;
//		    }
//		    util.alertDialog("メッセージ", "ユーザー削除が失敗しました。");
//		},
//		error : function(status){
//		    util.alertDialog("メッセージ", "ユーザー削除が失敗しました。");
//		}
//	    });
//	});
//	
//    });
    
    //show data list
    divisionGrid.reloadTable();

});
