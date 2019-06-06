<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>产品管理</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="header">
				<span class="icon_flag">产品信息列表</span>
			</div>
	  		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  			<tr id="thead">
					<td width="10%">产品ID</td>
					<td width="20%">产品名称</td>
					<td width="20%">产品作者</td>
					<td width="25%">产品文件名</td>
					<td width="25%">操作</td>
  				</tr>
				<#if (resourceList?? && 0 < resourceList?size)>
					<#list resourceList as r>
						<tr bgColor="#<#if (0 == r_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == r_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
							<td>${r.resourceId}</td>
							<td>${r.resourceName}</td>
							<td>${r.resourceAuthor}</td>
							<td>${r.resourceFile}</td>
							<td>
								<a href="${request.contextPath}/manage/database/move/${r.resourceId}&mid=${id}" onClick="return confirm('确定添加该产品文件吗？');" title="添加">添加</a>
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
