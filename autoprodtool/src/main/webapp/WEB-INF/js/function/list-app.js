
$(function() {  
    //filter selections
    $("#filter-form").find('select').change(function(){
	$("#filter-form").trigger('submit');
    });
    $("#filter-form").on("submit", function(evt){
	evt.preventDefault();
		
	//reload table contents
	jqGridWrapper.reloadTable(util.getFormData("#filter-form"));
	
	$("#filter-form").ajaxSubmit({
	    url : urls.filterList,
	    type : 'POST',
	    dataType : 'json',
	    data : {tn : new Date().getTime()},
	    success : function(data){
		if(data && data.code == 'S00'){
		    //refresh filters
		    var result = data.data || {};
		    var selections = result.selections || {};
		    var resultOptions = result.resultOptions || {};
		    for(var key in resultOptions){
			var selector = "#select-" + key;
			util.fillOptionList(selector, resultOptions[key], null, null, selections[key]);
		    }		    

		}
	    }
	});
    });
    
    
    
    // テーブル定義
    var jqGridWrapper = null;
    jqGridWrapper = new JqGridWrapper("function-list", false, {
	url : urls.list,
	mtype : "POST",
	datatype : "json",
	jsonReader : {
	    root : "data",
	    page : "pager.page",
	    total : "pager.totalPages",
	    records : "pager.total",
	    cell : "",
	    id : "id"
	},
	height : "auto",
	width : $("#function-list").parent().width(),
	// autowidth : true,
	shrinkToFit : false,
	altRows : true,
	sortable : false,
	sortname : "id",
	colModel : [ {
	    label : 'ID',
	    name : 'id',
	    width : 60
	}, {
	    label : '並び',
	    name : 'order'
	}, {
	    label : '大分類',
	    name : 'categoryBigName'
	}, {
	    label : '中分類',
	    name : 'categoryMidName'
	}, {
	    label : '小分類',
	    name : 'categorySmallName'
	}, {
	    label : '説明',
	    name : 'description'
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
		util.paginator($("#paginator"), gridParam.records, gridParam.rowNum, gridParam.page, 9, function(
			newPage) {
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
	    util.alertDialog("メッセージ", "機能を選択してください。");
	    return;
	}
	window.location.href=urls.detail + "?id=" + jqGridWrapper.getSelectedRowData().id;
    });
    $("#btn-update").click(function(){
	if(!jqGridWrapper.getSelectedRowData()){
	    util.alertDialog("メッセージ", "機能を選択してください。");
	    return;
	}
	window.location.href=urls.update + "?id=" + jqGridWrapper.getSelectedRowData().id;
    });
    $("#btn-del").click(function(){
	if(!jqGridWrapper.getSelectedRowData()){
	    util.alertDialog("メッセージ", "機能を選択してください。");
	    return;
	}
	
	util.confirmDialog('削除確認', "機能を削除してよろしいですか？", function(){
	    $.ajax({
		url : urls.del,
		type : 'POST',
		dataType : 'json',
		data : {ids : jqGridWrapper.getSelectedRowData().id},
		success : function(data){
		    if(data && data.code === 'S00'){
			util.alertDialog("メッセージ", "機能を削除しました。");
			jqGridWrapper.reloadTable();
			return ;
		    }
		    util.alertDialog("メッセージ", "機能削除が失敗しました。");
		},
		error : function(status){
		    util.alertDialog("メッセージ", "機能削除が失敗しました。");
		}
	    });
	});
	
    });
    
    //trigger filter and show data list
    $("#filter-form").submit();
    //jqGridWrapper.reloadTable();

});
