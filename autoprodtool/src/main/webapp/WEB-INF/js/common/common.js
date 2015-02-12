
/**
 *  Constructor for JqGridWrapper
 * @param containerId string - html container id. used for jquery selector.
 * @param loadAtOnce boolean - load table data once initiated
 * @param options object - jqgrid options
 * @returns
 */
function JqGridWrapper(containerId, loadAtOnce, options){
    var that = this;
    this.selectedRowId = null;
    this.datatype = options.datatype;
    this.grid = jQuery("#" + containerId);    
    if(!loadAtOnce){
	options.datatype = "local";
    }
    if(!options.jsonReader){
	options.jsonReader = {
	    root : "data",
	    page : "pager.page",
	    total : "pager.totalPages",
	    records : "pager.total",
	    cell : "",
	    id : "id"
	};
    }
    this.grid.bind("jqGridCellSelect", function(evt, rowid, index, content) {		   
	that.selectedRowId = rowid;
    });
    this.grid.bind("jqGridLoadComplete",function(event, data) {			    
	that.selectedRowId = null;

	var gridParam = {};
	if (data && data.pager) {
	    gridParam.rowNum = data.pager.limit;
	    gridParam.records = data.pager.total;
	    gridParam.page = data.pager.page;
	}
	that.setGridParams(gridParam);   
    });
    
    
    this.grid.jqGrid(options);
};

JqGridWrapper.prototype.reloadTable = function(postData, resetPager){
    var gridParam = {
	    datatype : this.datatype
    };
    if (postData) {
	gridParam.postData = postData;
    }
    if (resetPager) {
	gridParam.page = 1;
    }
    this.grid.jqGrid('setGridParam', gridParam);
    this.grid.trigger("reloadGrid");

};

JqGridWrapper.prototype.getGridParam = function(key){
    return this.grid.jqGrid('getGridParam', key);
};
JqGridWrapper.prototype.setGridParam = function(key, value){
    var params = { };
    if(key){
	params[key] = value;
    }
    this.grid.jqGrid('setGridParam', params);
};

JqGridWrapper.prototype.getGridParams = function(){
    return this.grid.jqGrid('getGridParam');
};

JqGridWrapper.prototype.setGridParams = function(params){
    params = params && typeof(params) == "object" ? params : {}; 
    this.grid.jqGrid('setGridParam', params);
};



JqGridWrapper.prototype.getWrapped = function(){
    return this.grid;
};

JqGridWrapper.prototype.getSelectedRowId = function(){
    return this.selectedRowId;
};

JqGridWrapper.prototype.getSelectedRowData = function(){
    if(this.selectedRowId){
	return this.grid.jqGrid('getRowData', this.selectedRowId);
    }
    return null;
};

JqGridWrapper.prototype.enableInlineEdit = function(addUrl, editUrl, delUrl, addSuccessfunc, editSuccessfunc, delSuccessfunc){
    var that = this;
    var pager = this.getGridParam("pager");
    this.grid
    .navGrid(pager,{edit:false,add:false,del:false,search:false})
    .inlineNav(pager, {
	addParams : {
	    rowID : "0",
	    addRowParams : {
		url : addUrl,
		successfunc : function(){
		    if(addSuccessfunc){
			addSuccessfunc();
		    }
		    that.reloadTable();
		}
	    }
	},
	editParams : {
	    url : editUrl,
	    successfunc : function(){
		if(editSuccessfunc){
		    editSuccessfunc();
		}
		that.reloadTable();
	    }
	}
    });
    
  //add delete button
    this.grid.navButtonAdd(pager,{
       caption:"", 
       buttonicon:"ui-icon-close", 
       onClickButton: function(){ 
	   if(!that.getSelectedRowData()){
	       util.alertDialog("メッセージ", "一行のデータを選択してください。");
	       return;
	   }

	   util.confirmDialog('削除確認', "データを削除してよろしいですか？", function(){
	       $.ajax({
		   url : delUrl,
		   type : 'POST',
		   dataType : 'json',
		   data : {ids : that.getSelectedRowData().id},
		   success : function(data){
		       if(data && data.code == 'S00'){
			   util.alertDialog("メッセージ", "選択したデータを削除しました。");
			   that.reloadTable();
			   return ;
		       }
		       util.alertDialog("メッセージ", "選択したデータの削除が失敗しました。");
		   },
		   error : function(status){
		       util.alertDialog("メッセージ", "選択したデータの削除が失敗しました。");
		   }
	       });
	   }); 
	  
       },
       position:"last"
    });
};


/**
 * define utility functions in name space 'util'
 * 
 */
var util = {

};

/**
 * Ajax呼び出す返却値を取得する
 */
util.getAjaxData = function(url, postdata) {
    var list = null;
    postdata = postdata ? postdata : {ts : new Date().getTime()};

    $.ajax({
	url : url,
	type : "POST",
	data : postdata,
	dataType : 'json',
	async : false,
	success : function(data, status) {
	    if(data.data) {
		list = data.data;
	    }
	}
    });

    return list;
};

util.fillOptionList = function (selector, dataList, keyProp, labelProp, selected){
    $(selector).val("");
    $(selector).empty();

    for(var index in dataList){			
	var dataEle = dataList[index];
	var key, label;
	if(typeof(dataEle) == "string"){
	    key = dataEle;
	    label = dataEle;
	}else{
	    key = dataEle[keyProp];
	    label = dataEle[labelProp];
	}
	var option = $("<option/>").attr("value", key).text(label);
	if(key == selected){
	    option.attr("selected", "selected");
	}
	$(selector).append(option);
    }

};

util.listToMap = function(list, keyProp, valueProp){
    var map = {};
    for(var index = 0; index < list.length; index++){			
	var dataEle = list[index];
	var key, label;
	if(typeof(dataEle) == "string"){
	    key = dataEle;
	    label = dataEle;
	}else{
	    key = dataEle[keyProp];
	    label = dataEle[valueProp];
	}	
	map[key] = label;
    }
    return map;
};

util.getJqGridOptionsString = function(list, keyProp, valueProp){
    var str = "";
    for(var index = 0; index < list.length; index++){			
	var dataEle = list[index];
	var key, label;
	if(typeof(dataEle) == "string"){
	    key = dataEle;
	    label = dataEle;
	}else{
	    key = dataEle[keyProp];
	    label = dataEle[valueProp];
	}	
	str += index > 0 ? ";" : '';
	str += key + ":" + label;	
    }
    return str;
};

/*
 * paginator control
 * 
 * css: <link rel="stylesheet" type="text/css"
 * href="${pageContext.request.contextPath}/js/lib/jquery/paginator/css/paginator.css" />
 * js: <script type="text/javascript"
 * src="${pageContext.request.contextPath}/js/lib/jquery/paginator/jquery.paginator.js"></script>
 * container: <div id="paginator"></div>
 * 
 * @param container jQuery object 
 * @param totalRecords the count of all records
 * @param currentPage current page number 
 * @param pageBtnCount the count of page buttons 
 * @param pageChangedCallBack the callback when pagechanged eg. var callback = function(newPage) {};
 * 
 */
util.paginator = function(container, totalRecords, recordsPerpage, currentPage, pageBtnCount, pageChangedCallBack) {
    $(container).paginator({
	totalrecords : totalRecords,
	recordsperpage : recordsPerpage,
	pagebtncount : pageBtnCount,
	initval : currentPage,
	next : '次へ',
	prev : '前へ',
	first : '',
	last : '',
	theme : '',
	controlsalways : false,
	onchange : function(newPage) {
	    if (pageChangedCallBack)
		pageChangedCallBack(newPage);
	}
    });
};

util.alertDialog = function(title, content, closeCallback) {
    title = title || "メッセージ";
    var width = content.length>20 ? 'auto':350;

    var html = [ '<div id="dialog-alert" title="' + title + '">', '<p>',
                 content, '</p></div>' ].join(" ");

    var buttons = [ {
	text : "OK",
	click : function() {
	    $(this).dialog('close');
	}
    } ];

    $(html).dialog({
	resizable : false,
	minWidth : 350,
	width : width,
	modal : true,
	buttons : buttons,
	close : function() {
	    if (closeCallback) {
		closeCallback();
	    }
	}
    });

};

util.confirmDialog = function(title, content, okCallback, closeCallback, cancelCallback, okCaption) {
    title = title  || "確認";
    okCaption = okCaption || "OK";

    var width = content.length>20 ? 'auto':350;

    var buttons = [ {
	text : okCaption,
	click : function(eventObject) {
	    $(this).dialog('close');
	    if (okCallback) {
		okCallback(eventObject);
	    }
	}
    }, {
	text : "キャンセル",
	click : function(eventObject) {
	    $(this).dialog('close');
	    if (cancelCallback) {
		cancelCallback(eventObject);
	    }
	}
    } ];

    content = content ? content : "";
    var html = [ '<div id="dialog-confirm" title="' + title + '">', '<p>',
                 content, '</p></div>' ].join(" ");

    $(html).dialog({
	resizable : false,
	minWidth : 350,
	width : width,
	modal : true,
	buttons : buttons,
	close : function(event,ui) {
	    if (closeCallback) {
		closeCallback(event,ui);
	    }
	}
    });
};





