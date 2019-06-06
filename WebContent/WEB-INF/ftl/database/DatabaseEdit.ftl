<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>新建文件包</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="header">
				<span class="icon_flag">新建文件包：</span>
			</div>
			
			<form action="${request.contextPath}/manage/database/save" method="post">
				<div class="label">包名：</div>
				<div>
					<@s.formInput "database.databaseName" "class='input'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div class="label">打包集是否推荐：</div>
				<div>
					<@s.formRadioButtons "database.databaseCommend" commendMap />
				</div>
				
				<div id="operation">
					<input type="submit" value="保 存" class="button icon_save" />&nbsp;&nbsp;
					<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
				</div>
			</form>
		</div>
	</body>
</html>