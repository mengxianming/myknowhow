/**
 * define JqGridWrapper class
 */
(function(){
    /**
     *  Constructor for JqGridWrapper
     * @param containerId string - html container id. used for jquery selector.
     * @param loadAtOnce boolean - load table data once initiated
     * @param options object - jqgrid options
     * @returns
     */
    JqGridWrapper = function (containerId, loadAtOnce, options){
	var that = this;
	this.selectedRowId = null;
	this.grid = jQuery("#" + containerId);    
	if(!loadAtOnce){
	    options = jQuery.extend({}, options, {
		datatype: "local"
	    });
	}
	this.grid.bind("jqGridCellSelect", function(evt, rowid, index, content) {		   
	    that.selectedRowId = rowid;
	});
	this.grid.bind("jqGridLoadComplete",function(event) {			    
	    that.selectedRowId = null;
	});
	this.grid.jqGrid(options);
    };

    JqGridWrapper.prototype.resetUrl = function(newUrl){
	var gridParam = {
		url : newUrl
	};
	this.grid.jqGrid('setGridParam', gridParam);
    };
    JqGridWrapper.prototype.reloadTable = function(postData, resetPager){
	var gridParam = {
		datatype : "json"
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
})();

/**
 * define utility functions in name space 'util'
 * 
 */
$(function(){    
    util = {

    };

    /**
     * Ajax呼び出す返却値を取得する
     */
    util.getAjaxData = function(url, postdata) {
	var list = null;
	postdata = postdata ? postdata : {};

	util.ajax({
	    url : url,
	    type : "POST",
	    data : postdata,
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

});




