<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" column="text/html; charset=UTF-8" />
		<title>栏目管理</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="new">
				<a class="button icon_add" href="${request.contextPath}/manage/column/add">新建栏目</a>
				<span id="tips"><#if tips??>${tips}</#if></span>
			</div>
			
			<div id="header">
				<span class="icon_flag">栏目信息列表</span>
			</div>
			
	  		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  			<tr id="thead">
					<td width="10%">栏目ID</td>
					<td width="20%" class="left">所属站点</td>
					<td width="20%" class="left">所属频道</td>
					<td width="40%" class="left">栏目标题</td>
					<td width="10%">操作</td>
  				</tr>
				<#if (columnList?? && 0 < columnList?size)>
					<#list columnList as c>
						<tr bgColor="#<#if (0 == c_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == c_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
							<td>
								${c.columnId}
							</td>
							<td class="left">
								${c.website.websiteTitle}
							</td>
							<td class="left">
								${c.channel.channelTitle}
							</td>
							<td class="left">
								${c.columnTitle}
							</td>
							<td>
								<a href="${request.contextPath}/manage/column/${c.columnId}">编辑</a>&nbsp;&nbsp;
								<a href="${request.contextPath}/manage/column/del/${c.columnId}" onClick="return confirm('确定删除吗？');">删除</a>
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
