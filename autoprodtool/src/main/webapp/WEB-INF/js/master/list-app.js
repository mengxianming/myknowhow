/**
 * define division table
 */
$(function() {   
    // テーブル定義
    var divisionGrid = null;
    divisionGrid = new JqGridWrapper("division-list", false, {
	caption : '所属管理',
	url : urls.division.list,
	mtype : "POST",
	datatype : "json",
	height : 300,
	width : 280,
	// autowidth : true,
	// shrinkToFit : true,
	altRows : true,
	sortable : false,
	sortname : "id",
	pager : "#division-pager",
	viewrecords : false,
	pagerpos : 'right',
	colModel : [ {
	    label : 'ID',
	    name : 'id'
	}, {
	    label : '企業',
	    name : 'companyId',
	    editable : true,
	    edittype : 'custom',
	    editoptions : {
		custom_element : function(value, options) {
		    var companyList = util.getAjaxData(urls.company.list);
		    var selector = $("<select/>");
		    util.fillOptionList(selector, companyList, "id", "name", value);
		    return selector.get(0);
		},
		custom_value : function(elem, operation, value) {
		    if (operation === 'get') {
			return $(elem).val();
		    } else if (operation === 'set') {
			$(elem).val(value);
		    }
		}
	    }
	}, {
	    label : '部',
	    name : 'pparent',
	    editable : true
	}, {
	    label : '課',
	    name : 'parent',
	    editable : true
	}, {
	    label : 'グループ',
	    name : 'name',
	    editable : true
	} ]

    });
    // add default pager and in-line edit buttons
    divisionGrid.enableInlineEdit(urls.division.create, urls.division.update, urls.division.del);
    // show data list
    divisionGrid.reloadTable();
});

/**
 * define company table
 */
$(function() {
    // テーブル定義
    var companyGrid = null;
    companyGrid = new JqGridWrapper("company-list", false, {
	caption : '企業管理',
	url : urls.company.list,
	mtype : "POST",
	datatype : "json",
	height : 300,
	width : 280,
	// autowidth : true,
	// shrinkToFit : true,
	altRows : true,
	sortable : false,
	sortname : "id",
	pager : "#company-pager",
	viewrecords : false,
	pagerpos : 'right',
	colModel : [ {
	    label : 'ID',
	    name : 'id'
	}, {
	    label : '名前',
	    name : 'name',
	    editable : true
	} ]

    });
    // add default pager and in-line edit buttons
    companyGrid.enableInlineEdit(urls.company.create, urls.company.update, urls.company.del);
    // show data list
    companyGrid.reloadTable();
});

/**
 * define role table
 */
$(function() {
    // テーブル定義
    var roleGrid = null;
    roleGrid = new JqGridWrapper("role-list", false, {
	caption : '役割管理',
	url : urls.role.list,
	mtype : "POST",
	datatype : "json",
	height : 300,
	width : 280,
	// autowidth : true,
	// shrinkToFit : true,
	altRows : true,
	sortable : false,
	sortname : "id",
	pager : "#role-pager",
	viewrecords : false,
	pagerpos : 'right',
	colModel : [ {
	    label : 'ID',
	    name : 'id'
	}, {
	    label : '名前',
	    name : 'name',
	    editable : true
	} ]

    });
    // add default pager and in-line edit buttons
    roleGrid.enableInlineEdit(urls.role.create, urls.role.update, urls.role.del);
    // show data list
    roleGrid.reloadTable();
});

/**
 * define categorybig table
 */
$(function() {
    // テーブル定義
    var categorybigGrid = null;
    categorybigGrid = new JqGridWrapper("categorybig-list", false, {
	caption : '大分類管理',
	url : urls.categorybig.list,
	mtype : "POST",
	datatype : "json",
	height : 300,
	width : 280,
	// autowidth : true,
	// shrinkToFit : true,
	altRows : true,
	sortable : false,
	sortname : "id",
	pager : "#categorybig-pager",
	viewrecords : false,
	pagerpos : 'right',
	colModel : [ {
	    label : 'ID',
	    name : 'id'
	}, {
	    label : '名前',
	    name : 'name',
	    editable : true
	} ]

    });
    // add default pager and in-line edit buttons
    categorybigGrid.enableInlineEdit(urls.categorybig.create, urls.categorybig.update, urls.categorybig.del);
    // show data list
    categorybigGrid.reloadTable();
});

/**
 * define categorymid table
 */
$(function() {
    // テーブル定義
    var categorymidGrid = null;
    categorymidGrid = new JqGridWrapper("categorymid-list", false, {
	caption : '中分類管理',
	url : urls.categorymid.list,
	mtype : "POST",
	datatype : "json",
	height : 300,
	width : 280,
	// autowidth : true,
	// shrinkToFit : true,
	altRows : true,
	sortable : false,
	sortname : "id",
	pager : "#categorymid-pager",
	viewrecords : false,
	pagerpos : 'right',
	colModel : [ {
	    label : 'ID',
	    name : 'id'
	}, {
	    label : '名前',
	    name : 'name',
	    editable : true
	} ]

    });
    // add default pager and in-line edit buttons
    categorymidGrid.enableInlineEdit(urls.categorymid.create, urls.categorymid.update, urls.categorymid.del);
    // show data list
    categorymidGrid.reloadTable();
});

/**
 * define categorysmall table
 */
$(function() {
    // テーブル定義
    var categorysmallGrid = null;
    categorysmallGrid = new JqGridWrapper("categorysmall-list", false, {
	caption : '小分類管理',
	url : urls.categorysmall.list,
	mtype : "POST",
	datatype : "json",
	height : 300,
	width : 280,
	// autowidth : true,
	// shrinkToFit : true,
	altRows : true,
	sortable : false,
	sortname : "id",
	pager : "#categorysmall-pager",
	viewrecords : false,
	pagerpos : 'right',
	colModel : [ {
	    label : 'ID',
	    name : 'id'
	}, {
	    label : '名前',
	    name : 'name',
	    editable : true
	} ]

    });
    // add default pager and in-line edit buttons
    categorysmallGrid.enableInlineEdit(urls.categorysmall.create, urls.categorysmall.update, urls.categorysmall.del);
    // show data list
    categorysmallGrid.reloadTable();
});
