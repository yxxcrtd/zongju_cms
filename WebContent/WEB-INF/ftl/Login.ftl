<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>登录页面</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>
	
	<body>
		<div id="content">
			<div id="header">
				<span class="icon_flag">用户登录：</span>
			</div>
			
			<form action="${request.contextPath}/loginCheck" method="post">
				<div class="label">用户姓名：</div>
				<div>
					<@s.formInput "user.userName" "class='input'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div class="label">用户密码：</div>
				<div>
					<@s.formInput "user.password" "class='input'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div id="operation">
					<input type="submit" value="登录" class="button icon_save" />&nbsp;&nbsp;
					<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
				</div>
				
			</form>
		</div>
		
		<script language="javascript" src="${request.contextPath}/js/jquery.js"></script>
		<script language="javascript" src="${request.contextPath}/js/public.js"></script>
	</body>
</html>
