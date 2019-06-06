<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>WebService接口</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="new">
				<a class="button icon_add">接口调用演示</a>
			</div>
			
			<div id="header">
				<span class="icon_flag">WebService接口</span>
			</div>
			
	  		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  			<tr id="thead">
					<td width="10%">调用方式</td>
					<td width="30%" class="left">接口地址</td>
					<td width="60%" class="left">返回示例</td>
  				</tr>
				<tr bgColor="F9F9F9">
					<td>GET</td>
					<td class="left"><a href="${scheme}://${serverName}:${port}${request.contextPath}/manage/interface/invoke" target="_blank">${scheme}://${serverName}:${port}${request.contextPath}/manage/interface/invoke</td>
					<td class="left" id="json"></td>
				</tr>
	  		</table>
		</div>
		
		<script language="javascript" src="${request.contextPath}/js/jquery.js"></script>
		<script language="javascript">
		<!--
		$(".icon_add").on("click", function() {
			$.get("${request.contextPath}/manage/interface/invoke", "", function(data) {
				$("#json").html(data);
			}, "text");
		});
		//-->
		</script>
	</body>
</html>
