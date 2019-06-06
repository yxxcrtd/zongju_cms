<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><#if (0 == channel.channelId)>新建<#else>修改</#if>频道</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
		<script language="javascript" src="${request.contextPath}/js/jquery.js"></script>
		<script type="text/javascript">
		<!--
		$(function() {
			$("#websiteId option").each(function() {
				if($(this).val() == '${channel.websiteId}') {
					$(this).attr("selected", "selected");
				}
			});
		});
		//-->
		</script>
	</head>

	<body>
		<div id="content">
			<div id="header">
				<span class="icon_flag"><#if (0 == channel.channelId)>新建<#else>修改</#if>频道：</span>
			</div>
			
			<form action="${request.contextPath}/manage/channel/save" method="post" enctype="multipart/form-data">
				<div class="label">所属站点：</div>
				<div>
					<@s.formSingleSelect "channel.websiteId" websiteMap "class='selectStyle'" />
				</div>
				<div class="label">频道标题：</div>
				<div>
					<@s.formInput "channel.channelTitle" "class='input'" /><@s.showErrors classOrStyle="red" />
				</div>
				<div class="label">频道LOGO：（尺寸要求：宽度：700像素；高度：100像素 - 200像素之间；文件大小不超过200K；推荐图片格式：jpg和png）</div>
				<div>
					<input type="file" class="input" style="width: 652px;" name="file" />
				</div>
				<div id="operation">
					<input type="submit" value="<#if (0 == channel.channelId)>保 存<#else>修 改</#if>" class="button icon_save" />&nbsp;&nbsp;
					<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
				</div>
				
				<@s.formHiddenInput "channel.channelId" />
				<@s.formHiddenInput "channel.channelLOGO" />
			</form>
		</div>
	</body>
</html>
