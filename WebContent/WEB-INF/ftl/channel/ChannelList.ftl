<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>频道管理</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="new">
				<a class="button icon_add" href="${request.contextPath}/manage/channel/add">新建频道</a>
				<span id="tips"><#if tips??>${tips}</#if></span>
			</div>
			
			<div id="header">
				<span class="icon_flag">频道信息列表</span>
			</div>
		
			<#--
			<input type="text" placeholder="请输入查询的频道名称" />
			<a class="button icon_query" href="<@s.url '${request.contextPath}/manage/channel/add' />">查  询</a>
			-->	
			
	  		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  			<tr id="thead">
					<td width="10%">频道ID</td>
					<td width="15%" class="left">所属站点</td>
					<td width="15%" class="left">频道标题</td>
					<td width="45%" class="left">频道LOGO</td>
					<td width="15%">操作</td>
  				</tr>
				<#if (channelList?? && 0 < channelList?size)>
					<#list channelList as r>
						<tr bgColor="#<#if (0 == r_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == r_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
							<td>
								${r.channelId}
							</td>
							<td class="left">
								${r.website.websiteTitle}
							</td>
							<td class="left">
								${r.channelTitle}
							</td>
							<td class="left">
								<a href="/upload/${r.website.websiteName}/${r.channelLOGO}" target="_blank"><img src="${request.contextPath}/upload/${r.website.websiteName}/${r.channelLOGO}" width="80%" height="60" /></a>
							</td>
							<td>
								<a href="${request.contextPath}/manage/channel/${r.channelId}">编辑</a>&nbsp;&nbsp;
								<a href="${request.contextPath}/manage/channel/del/${r.channelId}" onClick="return confirm('确定删除吗？');">删除</a>
							</td>
						</tr>
					</#list>
				<#else>
					<tr bgColor="F9F9F9">
						<td colspan="8">没有数据！</td>
					</tr>
				</#if>
	  		</table>
	  		<div id="pageNav">
	  			<#include "Pager.ftl" />
	  		</div>
		</div>
		
		<script language="javascript" src="${request.contextPath}/js/jquery.js"></script>
		<script language="javascript" src="${request.contextPath}/js/public.js"></script>
	</body>
</html>
