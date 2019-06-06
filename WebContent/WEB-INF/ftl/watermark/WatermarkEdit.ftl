<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><#if (0 == watermark.watermarkId)>新建<#else>修改</#if>水印</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="header">
				<span class="icon_flag"><#if (0 == watermark.watermarkId)>新建<#else>修改</#if>水印：</span>
			</div>
			
			<form action="${request.contextPath}/manage/watermark/save" method="post">
				<div class="label">水印名称：</div>
				<div>
					<@s.formInput "watermark.watermarkName" "class='input'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div class="label">水印字体大小：</div>
				<div>
					<@s.formInput "watermark.watermarkFont" "class='input'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div class="label">水印是否激活：</div>
				<div>
					<@s.formRadioButtons "watermark.watermarkActive" activeMap />
				</div>
				
				<div id="operation">
					<input type="submit" value="<#if (0 == watermark.watermarkId)>保 存<#else>修 改</#if>" class="button icon_save" />&nbsp;&nbsp;
					<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
				</div>
				
				<@s.formHiddenInput "watermark.watermarkId" />
				<@s.formHiddenInput "watermark.watermarkCreateTime" />
			</form>
		</div>
	</body>
</html>
