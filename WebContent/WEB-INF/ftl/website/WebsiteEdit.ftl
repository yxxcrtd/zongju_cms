<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" website="text/html; charset=UTF-8" />
		<title><#if (0 == website.websiteId)>新建<#else>修改</#if>分类</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="header">
				<span class="icon_flag"><#if (0 == website.websiteId)>新建<#else>修改</#if>站点：</span>
			</div>
			
			<form action="${request.contextPath}/manage/website/save" method="post" enctype="multipart/form-data">
				<div class="label">站点标识（只能输入英文）：</div>
				<div>
					<@s.formInput "website.websiteName" "class='input'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div class="label">站点标题：</div>
				<div>
					<@s.formInput "website.websiteTitle" "class='input'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div class="label">站点LOGO：（尺寸要求：宽度：1000像素；高度：100像素 - 200像素之间；文件大小不超过200K；推荐图片格式：jpg和png）</div>
				<div>
					<input type="file" class="input" style="width: 650px;" name="file" />
				</div>
				
				<div id="operation">
					<input type="submit" value="<#if (0 == website.websiteId)>保 存<#else>修 改</#if>" class="button icon_save" />&nbsp;&nbsp;
					<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
				</div>
				
				<@s.formHiddenInput "website.websiteId" />
				<@s.formHiddenInput "website.websiteLOGO" />
			</form>
		</div>
		
		<script language="javascript" src="${request.contextPath}/js/kindeditor.js"></script>
		<script language="javascript">
		<!--
		var editor;          
		KindEditor.ready(function(K) {                 
			editor = K.create("#websitewebsite");
		});
		//-->
		</script>
	</body>
</html>
