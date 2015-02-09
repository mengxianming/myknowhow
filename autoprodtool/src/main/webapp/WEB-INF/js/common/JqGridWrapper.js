/**
 * Constructor for JqGridWrapper
 */
function JqGridWrapper(containerId, loadAtOnce, options){
    this.grid = jQuery("#" + containerId);    
    if(!loadAtOnce){
	options = jQuery.extend({}, options, {dataType: "local"});
    }
    this.grid.jqGrid(options);
}

JqGridWrapper.prototype.resetUrl = function(newUrl){
    var gridParam = {
	    url : newUrl
    };
    this.grid.jqGrid('setGridParam', gridParam);
    return this;
};
JqGridWrapper.prototype.reloadTable = function(postData, resetPage){
    var gridParam = {
	    datatype : "json"
    };
    if (postData) {
	gridParam.postData = postData;
    }
    if (resetPage) {
	gridParam.page = 1;
    }
    this.grid.jqGrid('setGridParam', gridParam);
    this.grid.trigger("reloadGrid");

};

JqGridWrapper.prototype.getWrapped = function(){
    return this.grid;
};

