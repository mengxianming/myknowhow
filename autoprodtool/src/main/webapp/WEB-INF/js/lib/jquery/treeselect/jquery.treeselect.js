/*!
 * jQuery treeselect Plugin
 * version: 1.0.0-2014.04.10
 * @requires jQuery v1.4 or later
 * Copyright (c) 2014 Wonday
 * 
 * Licensed same as jquery - MIT License
 * http://www.opensource.org/licenses/mit-license.php
 * 
 * notice: this plugin use ztree(http://www.ztree.me).
 * 
 * usage:
 *  html:
 * 	<link rel="stylesheet" href="./zTreeStyle/zTreeStyle.css" type="text/css">
 * 	<script type="text/javascript" src="./jquery-1.4.4.min.js"></script>
 * 	<script type="text/javascript" src="./jquery.ztree.all-3.5.js"></script>
 * 	<script type="text/javascript" src="./jquery.treeselect.js"></script>
 * 	<link rel="stylesheet" href="./css/treeselect.css" type="text/css">
 * 
 *  <div id = "treeselect" style="width:120px;"></div>
 * 
 *  javascript:
 *		function tsChange(oldValue, newValue) {
 *			alert("treeselect changed! oldValue:" + oldValue + " newValue:" + newValue);
 *		}
 *
 *		var tsSettings = {
 *			fieldname : 'tsresult',
 *			callback : {
 *				onChange : tsChange
 *			}
 *		};
 *
 *		var nodes =[
 *			{id:1, pId:0, name:"node-1","disable":true},
 *			{id:11, pId:1, name:"node-1-1"},
 *			{id:111, pId:11, name:"node-1-1-1"},
 *			{id:2, pId:0, name:"node-2"},
 *			{id:3, pId:0, name:"node-3"}
 *		 ];
 *
 *		// instance a treeselect jQuery obj
 *		$("#treeselect").treeselect(nodes, tsSettings);
 *
 *		// get selected item value
 *		var result = $("#treeselect").treeselect("val");
 *		// get the selected item text
 *		var result = $("#treeselect").treeselect("text");
 *		// set node to be selected
 *		$("#treeselect").treeselect("sel", 1);	
 *
 */
(function($) {

	// last opened dropdown 
	var gOpenedDropdown = null;
	var gLastMouseDownTimerId = null;

	// treeselect mouseDown event
	function _mouseDown(e) {

		var treeSelectId = e.data.treeSelectId;
		
		//clear last timer
		clearTimeout(gLastMouseDownTimerId);
		
		//disable internal select for hide select's dropdown
		$("#" + treeSelectId + "-result").attr('disabled', 'disabled');
		
		_showDropdown(e);
		gLastMouseDownTimerId = setTimeout(function(){
			$(gOpenedDropdown).removeAttr("disabled");
		}, 50);
	}
	
	// show dropdown
	function _showDropdown(e) {

		e.preventDefault();

		// close dropdown when click twice at the same treeselect
		if (gOpenedDropdown == e.target) {
			_hideDropdown();
			return;
		}
		
		// close all opened dropdown
		_hideDropdown();
		
		// save last opened dropdown
		gOpenedDropdown = e.target;
		
		// open dropdown below treeselect
		var treeSelectId = e.data.treeSelectId;
		var resultObj = $("#" + treeSelectId + "-result");
		var resultObjPosition = resultObj.position();
		$("#" + treeSelectId + "-dropdown").css({
			left : resultObjPosition.left + "px",
			top : resultObjPosition.top + resultObj.outerHeight() + "px"
		}).slideDown("fast");
	}

	// hide all dropdown
	function _hideDropdown() {
		$(".tsdropdown").fadeOut("fast");
		
		if(gOpenedDropdown!=null) {
			//enable internal select
			$(gOpenedDropdown).removeAttr("disabled");
		}
		gOpenedDropdown = null;
	}

	// return node's font (disable node's background default is silver)
	// can be change by set node's attribute of font
	// eg. {id:1,pId:0,font:{ 'background-color' : 'red'}}
	function _getFont(treeId, node) {
		return node.font ? node.font : (!node.disable ? {} : {
			'background-color' : 'silver'
		});
	}

	// tree click callback
	function _treeClick(e, treeId, treeNode) {
		var treeObj = $.fn.zTree.getZTreeObj(treeId);
		var nodes = treeObj.getSelectedNodes();
		
		// set selected node's id and name to internal input element
		var treeSelectId = treeId.substring(0, treeId.length - 5);
		var oldId = $("#" + treeSelectId + "-result").val();
		$("#" + treeSelectId + "-result").empty();
		if (nodes.length > 0) {
			$("#" + treeSelectId + "-result").prepend("<option value='" + nodes[0].id + "'>"+nodes[0].name+"</option>");
			
			if (oldId != nodes[0].id) {
				var tsSettings = $("#" + treeSelectId).data("treeselect");
				if(tsSettings.callback.onChange) {
					tsSettings.callback.onChange(oldId, nodes[0].id);
				}
			}
		}
		
		// close dropdown after click
		_hideDropdown();
	}

	// before tree click callback, can check disable node for not select
	function _treeBeforeClick(treeId, treeNode) {
		var disable = (treeNode && treeNode.disable);
		return !disable;
	}

	// close dropdown when click other element
	$(document).bind(
			'mousedown',
			function(e) {
				if (!(e.target == gOpenedDropdown || $(e.target).closest(
						".tsdropdown").length)) {
					_hideDropdown();
				}
			});

	// define treeselect method
	var methods = {

		// initialize method
		init : function(nodes, options) {

			var idIndex = 1;
			return this
					.each(function() {
						var $this = $(this);

						// try to get settings if not exist then return “undefined”
						var settings = $this.data("treeselect");

						// if got settings failed the create from default options
						if (typeof settings === "undefined") {

							var defaults = {
								fieldname: 'tsresult',
								callback : {
									onChange: null
								},
								zsetting : {

									view : {
										dblClickExpand : false,
										fontCss : _getFont
									},
									data : {
										simpleData : {
											enable : true
										}
									},
									callback : {}
								}
							};
							
							// merge settings, override beforeClick and onClick callback with default
							settings = $.extend(true, defaults, options || {},
									{
										zsetting : {
											callback : {
												beforeClick : _treeBeforeClick,
												onClick : _treeClick
											}
										}
									});

							// save settings
							$this.data("treeselect", settings);
						} else {

							// merge settings, override beforeClick and onClick callback with default
							settings = $.extend(true, settings, options || {},
									{
										zsetting : {
											callback : {
												beforeClick : _treeBeforeClick,
												onClick : _treeClick
											}
										}
									});
							$this.data("treeselect", settings);
						}

						// set id if not given
						if ($this.attr("id") == "") {
							$this.attr("id", "treeselect-" + idIndex);
							idIndex++;
						}

						var treeSelectId = $this.attr("id");
						$this.addClass("treeselect");
						$this.empty();
						
						// construct internal contents
						$this
								.html("<select id='"
										+ treeSelectId
										+ "-result' class='tsresult' name='" + settings.fieldname + "'></select><div id='"
										+ treeSelectId
										+ "-dropdown' class='tsdropdown' style='display:none; position: absolute;'><ul id='"
										+ treeSelectId
										+ "-tree' class='ztree'></ul></div>");
						
						// fix internal contents width
						var width = $this.width();
						$("#" + treeSelectId + "-result").css("width",width);
						$("#" + treeSelectId + "-dropdown").css("min-width",width);

						// bind mouseDown of treeselect
						$("#" + treeSelectId + "-result").mousedown({
							treeSelectId : treeSelectId
						}, _mouseDown);
						
						// initialize ztree
						$.fn.zTree.init($("#" + treeSelectId + "-tree"),
								settings.zsetting, nodes);

					});
		},
		
		// destroy method
		destroy : function() {
			return $(this).each(function() {
				var $this = $(this);
				// delete obj data
				$this.removeData("treeselect");
			});
		},
		
		// return the treeselect result, return "" if not select
		val : function() {
			
			var treeId = this.eq(0).attr("id") + "-tree";
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
			if (treeObj) {
				var nodes = treeObj.getSelectedNodes();
				if (nodes.length > 0) {
					return nodes[0].id;
				} 
			}

			return "";
			
		},
		
		// return the treeselect result's text, return "" if not select
		text : function() {
			
			var treeId = this.eq(0).attr("id") + "-tree";
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
			if (treeObj) {
				var nodes = treeObj.getSelectedNodes();
				if (nodes.length > 0) {
					return nodes[0].name;
				} 
			}

			return "";
			
		},
		
		// set the node to selected
		sel : function(nodeId) {
			
			var treeId = this.eq(0).attr("id") + "-tree";
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
			if (treeObj) {
				var node = treeObj.getNodeByParam("id", nodeId, null);
				if (node) {
					treeObj.selectNode(node, false);
					
					// set selected node's id and name to internal input element
					var treeSelectId = treeId.substring(0, treeId.length - 5);
					var oldId = $("#" + treeSelectId + "-result").val();

					$("#" + treeSelectId + "-result").empty();
					$("#" + treeSelectId + "-result").prepend("<option value='"+ node.id + "'>"+node.name+"</option>");
					
					if (oldId != node.id) {
						var tsSettings = $("#" + treeSelectId).data("treeselect");
						if(tsSettings.callback.onChange) {
							tsSettings.callback.onChange(oldId, node.id);
						}
					}
					
				} 
			}

			return "";
			
		},
		
		// return the root node
		root : function() {
			
			var treeId = this.eq(0).attr("id") + "-tree";
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
			if (treeObj) {
				var nodes = treeObj.getNodes();
				if (nodes.length>0) {
					return nodes[0];
				} 
			}

			return null;
			
		}
	};
	
	// define treeselect plugin
	$.fn.treeselect = function() {

		var method = arguments[0];

		if (methods[method]) {
			method = methods[method];
			arguments = Array.prototype.slice.call(arguments, 1);
		} else if (typeof method === "object" || !method) {
			method = methods.init;
		} else {
			$.error("Method" + method + "does not exist on jQuery.treeselect");
			return this;
		}

		return method.apply(this, arguments);
	};

})(jQuery);