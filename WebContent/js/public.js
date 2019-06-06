// Copyright 2014, YangXinXin

$(function() {
	$("#tips").fadeOut(10000);
	
//	$("#search .input").on("onMouseOver", function() {
//		$(this).select();
//	});

	$("#search .icon_query").on("click", function() {
		var searchNode = $("#search .input");
		if ("" == $.trim(searchNode.val())) {
			alert("请输入查询关键字！");
			searchNode.focus();
			return false;
		}
	});
});

function changeBgColor(obj, color) {
	obj.style.backgroundColor = color;
}
