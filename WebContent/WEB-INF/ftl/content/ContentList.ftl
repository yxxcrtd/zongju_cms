<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>内容管理</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="new">
				<a class="button icon_add" href="${request.contextPath}/manage/content/add">新建内容</a>
				<span id="tips"><#if tips??>${tips}</#if></span>
			</div>
			
			<div id="header">
				<span class="icon_flag">内容信息列表</span>
			</div>
			
	  		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  			<tr id="thead">
					<td width="10%">内容ID</td>
					<td width="10%" class="left">所属站点</td>
					<td width="10%" class="left">所属频道</td>
					<td width="10%" class="left">所属栏目</td>
					<td width="35%" class="left">内容标题</td>
					<td width="15%">内容创建时间</td>
					<td width="10%">操作</td>
  				</tr>
				<#if (contentList?? && 0 < contentList?size)>
					<#list contentList as c>
						<tr bgColor="#<#if (0 == c_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == c_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
							<td>
								${c.contentId}
							</td>
							<td class="left">
								${c.website.websiteTitle}
							</td>
							<td class="left">
								${c.channel.channelTitle}
							</td>
							<td class="left">
								${c.column.columnTitle}
							</td>
							<td class="left"><#-- title="<#noescape>${c.contentContent}</#noescape>"-->
								${c.contentTitle}
							</td>
							<td>
								${c.contentCreateTime}
							</td>
							<td>
								<a href="${request.contextPath}/manage/content/${c.contentId}">编辑</a>&nbsp;&nbsp;
								<a href="${request.contextPath}/manage/content/del/${c.contentId}" onClick="return confirm('确定删除吗？');">删除</a>
							</td>
						</tr>
					</#list>
				<#else>
					<tr bgColor="F9F9F9">
						<td colspan="5">没有数据！</td>
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
