<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><#if (0 == category.categoryId)>新建<#else>修改</#if>分类</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="header">
				<span class="icon_flag"><#if (0 == category.categoryId)>新建<#else>修改</#if>分类：</span>
			</div>
			
			<form action="${request.contextPath}/manage/category/save" method="post">
				<div class="label">分类名称：</div>
				<div>
					<@s.formInput "category.categoryName" "class='input'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div class="label">分类排序：</div>
				<div>
					<@s.formInput "category.categoryOrderby" "class='input'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div id="operation">
					<input type="submit" value="<#if (0 == category.categoryId)>保 存<#else>修 改</#if>" class="button icon_save" />&nbsp;&nbsp;
					<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
				</div>
				
				<@s.formHiddenInput "category.categoryId" />
				<@s.formHiddenInput "category.categoryParentId" />
				<@s.formHiddenInput "category.categoryParentPath" />
			</form>
		</div>
	</body>
</html>
